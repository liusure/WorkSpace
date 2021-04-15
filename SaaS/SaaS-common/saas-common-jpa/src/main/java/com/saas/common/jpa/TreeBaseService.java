package com.saas.common.jpa;

import java.util.ArrayList;
import java.util.List;

import com.saas.common.core.domain.TreeEntity;
import com.saas.common.core.entity.KeyValue;
import com.saas.common.exception.CustomException;
import com.saas.common.util.StringUtils;

public abstract class TreeBaseService<T extends TreeEntity> extends BaseService<T> {

	@Override
	public T save(T model) {
		if (model.getParentId() != null || model.getParentId() != 0l) {
			List<Long> ancestorIds = new ArrayList<>();
			if (model.getId() != null) {
				ancestorIds.add(model.getId());
			}
			isParentIdValid(model.getParentId(), ancestorIds);
		}
		return super.save(model);
	}

	@Override
	public T getById(Long id) {
		T model = super.getById(id);
		if (model.getParentId() != null || model.getParentId() != 0l) {
			T parent = getById(model.getParentId());
			model.setParentName(parent.getName());
			model.setAncestors(
					StringUtils.isNotEmpty(parent.getAncestors()) ? parent.getAncestors() + "->" + parent.getName()
							: parent.getName());
		}
		return model;
	}

	/**
	 * 构建数结构，把子孙节点构建完整
	 * 
	 * @param id
	 * @return
	 */
	public T buildTreeStructure(T model) {
		List<T> childs = getModels("asc_orderNum", new KeyValue("parentId", model.getParentId()));
		model.setChildren(childs);
		childs.forEach(child -> {
			buildTreeStructure(child);
		});
		return model;
	}

	private boolean isParentIdValid(Long parentId, List<Long> ancestorIds) {
		if (parentId == null || parentId == 0l) {
			return true;
		}
		if (ancestorIds.contains(parentId)) {
			throw new CustomException("ID:" + parentId + " 的节点会导致死循环，请重新选择父节点");
		}
		ancestorIds.add(parentId);
		T parent = getById(parentId);
		if (parent == null) {
			throw new CustomException("ID:" + parentId + " 的节点不存在，请重新选择父节点");
		}
		return isParentIdValid(parent.getParentId(), ancestorIds);
	}
}
