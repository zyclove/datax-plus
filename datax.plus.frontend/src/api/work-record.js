import request from '@/utils/request'

export function getInfo() {
  return request({
    url: '/userInfo',
    method: 'get'
  })
}

export function listWorkRecordData(pageNum, limit, dataJobId) {
  return request({
    url: `/job/list/${pageNum}/${limit}/${dataJobId}`,
    method: 'get'
  })
}

export function getJob(dataJobId) {
  return request({
    url: `/job/list/1/1/${dataJobId}`,
    method: 'get'
  })
}

export function addOrUpdate(data) {
  return request({
    url: '/job/addOrUpdate',
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
