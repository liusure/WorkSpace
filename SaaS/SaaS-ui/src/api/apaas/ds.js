import request from '@/utils/request'

// 查询列表
export function listDataSource(query) {
  return request({
    url: '/apaas/ds/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getDataSource(id) {
  return request({
    url: '/apaas/ds/' + id,
    method: 'get'
  })
}

// 新增
export function addDataSource(data) {
  return request({
    url: '/apaas/ds/',
    method: 'post',
    data: data
  })
}

// 修改
export function updateDataSource(data) {
  return request({
    url: '/apaas/ds/',
    method: 'put',
    data: data
  })
}

// 删除
export function delDataSource(id) {
  return request({
    url: '/apaas/ds/' + id,
    method: 'delete'
  })
}

// 导出
export function exportDataSource(query) {
  return request({
    url: '/apaas/ds/export',
    method: 'get',
    params: query
  })
}
