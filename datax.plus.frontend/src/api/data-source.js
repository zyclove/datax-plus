import request from '@/utils/request'

export function listDataSource(pageNum, limit) {
  return request({
    url: `/dataSource/list/${pageNum}/${limit}/0`,
    method: 'get'
  })
}

export function getJob(dataJobId) {
  return request({
    url: `/dataSource/list/1/1/${dataJobId}`,
    method: 'get'
  })
}

export function addOrUpdate(data) {
  return request({
    url: '/dataSource/addOrUpdate',
    method: 'post',
    data: data
  })
}

export function checkConnection(data) {
  return request({
    url: '/dataSource/checkConnection',
    method: 'post',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/dataSource/delete/${id}`,
    method: 'delete'
  })
}
