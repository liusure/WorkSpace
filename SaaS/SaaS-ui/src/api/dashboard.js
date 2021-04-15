import request from '@/utils/request'

// 后台首页-首页统计
export function dashboardData (params) {
  return request({
    url: '/system/dashboard/index',
    method: 'get',
    params: params
  })
}

// 后台首页-折线图
export function lineChat (params) {
  return request({
    url: '/system/dashboard/lineChat',
    method: 'get',
    params: params
  })
}

//
export function tenantIndex (params) {
  return request({
    url: '/system/dashboard/tenantIndex',
    method: 'get',
    params: params
  })
}
