import request from '@/util/request'

export function addVote(vote) {
  return request({
    url: '/vote/add',
    method: 'post',
    data: vote
  })
}

export function cancelVote(vote) {
  return request({
    url: '/vote/cancel',
    method: 'put',
    data: vote
  })
}

export function existVote(params) {
  return request({
    url: '/vote/exists',
    method: 'get',
    params
  })
}
