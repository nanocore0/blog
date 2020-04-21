import request from '@/util/request'

export function getCatalogsList(userId) {
  return request({
    url: '/catalogs/' + userId,
    method: 'get'
  })
}

export function addCatalog(catalog) {
  return request({
    url: '/catalog',
    method: 'post',
    data: catalog
  })
}

export function getCatalog(id) {
  return request({
    url: '/catalog/' + id,
    method: 'get'
  })
}

export function updateCatalog(id, catalog) {
  return request({
    url: '/catalog/' + id,
    method: 'put',
    data: catalog
  })
}

export function deleteCatalog(id) {
  return request({
    url: '/catalog/' + id,
    method: 'delete'
  })
}
