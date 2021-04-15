import request from '@/utils/request'

// 查询公告列表
export function listPrivilege(query) {
  return request({
    url: '/system/privilege/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getPrivilege(privilegeId) {
  return request({
    url: '/system/privilege/' + privilegeId,
    method: 'get'
  })
}

// 新增公告
export function addPrivilege(data) {
  return request({
    url: '/system/privilege',
    method: 'post',
    data: data
  })
}

// 修改公告
export function updatePrivilege(data) {
  return request({
    url: '/system/privilege',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delPrivilege(privilegeId) {
  return request({
    url: '/system/privilege/' + privilegeId,
    method: 'delete'
  })
}
