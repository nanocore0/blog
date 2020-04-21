import request from '@/util/request'

export function getUser(userId) {
  return request({
    url: '/user/' + userId,
    method: 'get'
  })
}

export function resetPassword(userId, form) {
  return request({
    url: '/user/reset/' + userId,
    method: 'put',
    data: form
  })
}

export function updateUser(userId, user) {
  return request({
    url: '/user/' + userId,
    method: 'put',
    data: user
  })
}

export function getAccountInfo(userId) {
  return request({
    url: '/user/info/' + userId,
    method: 'get'
  })
}

export function listAllUser() {
  return request({
    url: '/users',
    method: 'get'
  })
}
