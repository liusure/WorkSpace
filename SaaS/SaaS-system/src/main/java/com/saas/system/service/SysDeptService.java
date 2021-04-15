package com.saas.system.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.common.core.domain.SysDept;
import com.saas.common.core.entity.TreeSelect;
import com.saas.common.jpa.BaseDao;
import com.saas.common.jpa.BaseService;
import com.saas.system.repository.SysDeptDao;

@Service
public class SysDeptService extends BaseService<SysDept> {

	@Autowired
	private SysDeptDao modelDao;

	@Override
	protected BaseDao<SysDept> getModelDao() {
		return modelDao;
	}

	@Override
	protected boolean isUseLocalCache() {
		return true;
	}

	public List<SysDept> buildDeptTree(List<SysDept> depts) {
		List<SysDept> returnList = new ArrayList<SysDept>();
		List<Long> tempList = new ArrayList<Long>();
		for (SysDept dept : depts) {
			tempList.add(dept.getId());
		}
		for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext();) {
			SysDept dept = (SysDept) iterator.next();
			// 如果是顶级节点, 遍历该父节点的所有子节点
			if (!tempList.contains(dept.getParentId())) {
				recursionFn(depts, dept);
				returnList.add(dept);
			}
		}
		if (returnList.isEmpty()) {
			returnList = depts;
		}
		return returnList;
	}

	/**
	 * 构建前端所需要下拉树结构
	 * 
	 * @param depts 部门列表
	 * @return 下拉树结构列表
	 */
	public List<TreeSelect> buildDeptTreeSelect() {
		List<SysDept> deptTrees = buildDeptTree(this.getAllModels());
		return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
	}

	/**
	 * 递归列表
	 */
	private void recursionFn(List<SysDept> list, SysDept t) {
		// 得到子节点列表
		List<SysDept> childList = getChildList(list, t);
		t.setChildren(childList);
		for (SysDept tChild : childList) {
			if (hasChild(list, tChild)) {
				recursionFn(list, tChild);
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
		List<SysDept> tlist = new ArrayList<SysDept>();
		Iterator<SysDept> it = list.iterator();
		while (it.hasNext()) {
			SysDept n = (SysDept) it.next();
			if (null != n.getParentId() && n.getParentId().longValue() == t.getId().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysDept> list, SysDept t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}

}
