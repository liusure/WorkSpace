import request from '@/utils/request'

// 查询SAAS账号列表
export function listAccount(query) {
  return request({
    url: '/system/account/list',
    method: 'get',
    params: query
  })
}

// 查询SAAS账号详细
export function getAccount(id) {
  return request({
    url: '/system/account/' + id,
    method: 'get'
  })
}

// 新增SAAS账号
export function addAccount(data) {
  return request({
    url: '/system/account',
    method: 'post',
    data: data
  })
}

// 修改SAAS账号
export function updateAccount(data) {
  return request({
    url: '/system/account',
    method: 'put',
    data: data
  })
}

// 删除SAAS账号
export function delAccount(id) {
  return request({
    url: '/system/account/' + id,
    method: 'delete'
  })
}

// 导出SAAS账号
export function exportAccount(query) {
  return request({
    url: '/system/account/export',
    method: 'get',
    params: query
  })
}