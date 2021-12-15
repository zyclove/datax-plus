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

export function deleteWorkRecord(id) {
  return request({
    url: `/deleteWorkRecord/${id}`,
    method: 'delete'
  })
}
