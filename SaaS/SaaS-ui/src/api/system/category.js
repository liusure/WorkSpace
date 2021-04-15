import request from '@/utils/request'

// 查询系统目录列表
export function listCategory(query) {
  return request({
    url: '/system/category/list',
    method: 'get',
    params: query
  })
}

// 查询系统目录详细
export function getCategory(id) {
  return request({
    url: '/system/category/' + id,
    method: 'get'
  })
}

// 新增系统目录
export function addCategory(data) {
  return request({
    url: '/system/category',
    method: 'post',
    data: data
  })
}

// 修改系统目录
export function updateCategory(data) {
  return request({
    url: '/system/category',
    method: 'put',
    data: data
  })
}

// 删除系统目录
export function delCategory(id) {
  return request({
    url: '/system/category/' + id,
    method: 'delete'
  })
}

// 导出系统目录
export function exportCategory(query) {
  return request({
    url: '/system/category/export',
    method: 'get',
    params: query
  })
}