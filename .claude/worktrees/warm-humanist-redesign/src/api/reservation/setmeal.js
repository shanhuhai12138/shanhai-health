import request from '@/utils/request'

// 查询套餐组列表
export function listSetmeal(query) {
  return request({
    url: '/reservation/setmeal/list',
    method: 'get',
    params: query
  })
}

// 查询套餐组详细
export function getSetmeal(id) {
  return request({
    url: '/reservation/setmeal/' + id,
    method: 'get'
  })
}

// 新增套餐组
export function addSetmeal(data) {
  return request({
    url: '/reservation/setmeal',
    method: 'post',
    data: data
  })
}

// 修改套餐组
export function updateSetmeal(data) {
  return request({
    url: '/reservation/setmeal',
    method: 'put',
    data: data
  })
}

// 删除套餐组
export function delSetmeal(id) {
  return request({
    url: '/reservation/setmeal/' + id,
    method: 'delete'
  })
}
