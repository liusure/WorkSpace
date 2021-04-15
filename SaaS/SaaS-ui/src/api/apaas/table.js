import request from '@/utils/request'

// 查询列表
export function listTable(query) {
  return request({
    url: '/apaas/table/list',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getTable(id) {
  return request({
    url: '/apaas/table/' + id,
    method: 'get'
  })
}

// 新增
export function addTable(data) {
  return request({
    url: '/apaas/table/',
    method: 'post',
    data: data
  })
}

// 修改
export function updateTable(data) {
  return request({
    url: '/apaas/table/',
    method: 'put',
    data: data
  })
}

// 删除
export function delTable(id) {
  return request({
    url: '/apaas/table/' + id,
    method: 'delete'
  })
}

// 导出
export function exportTable(query) {
  return request({
    url: '/apaas/table/export',
    method: 'get',
    params: query
  })
}
// 同步数据库
export function synchDb(id) {
  return request({
    url: '/apaas/table/synchDb/' + id,
    method: 'get'
  })
}
