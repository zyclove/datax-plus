import request from '@/utils/request'

export function listDataSource(pageNum, limit) {
  return request({
    url: `/dataSource/list/${pageNum}/${limit}/0`,
    method: 'get'
  })
}

export function listTablesByDataSourceId(dataSourceId) {
  return request({
    url: `/dataSource/tables/${dataSourceId}`,
    method: 'get'
  })
}

export function listColumnsByDataSourceIdAndTableName(dataSourceId, tableName) {
  return request({
    url: `/dataSource/columns/${dataSourceId}/${tableName}`,
    method: 'get'
  })
}


export function applySql(data) {
  return request({
    url: '/dataSource/applySql',
    method: 'post',
    data: data
  })
}

export function listDataSourceType(pageNum, limit) {
  return request({
    url: `/dataSource/typeList/${pageNum}/${limit}/0`,
    method: 'get'
  })
}

export function getDataSourceById(dataSourceId) {
  return request({
    url: `/dataSource/list/1/1/${dataSourceId}`,
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
