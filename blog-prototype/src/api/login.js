import request from '@/util/request'

export function login(form) {
  return request({
    url: '/login',
    method: 'post',
    data: form,
    headers: {
      'Authorization': 'Basic cGlhbnhpYW46cGlhbnhpYW4='
    }
  })
}

export function getInfo() {
  return request({
    url: '/me',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
