import request from '@/util/request'

export function getCommentsList(blogId) {
  return request({
    url: '/comments/blog/' + blogId,
    method: 'get'
  })
}

export function saveComment(comment) {
  return request({
    url: '/comment',
    method: 'post',
    data: comment
  })
}

export function updateComment(id, comment) {
  return request({
    url: '/comment/' + id,
    method: 'put',
    data: comment
  })
}

export function getComment(id) {
  return request({
    url: '/comment/' + id,
    method: 'get'
  })
}

export function deleteComment(id) {
  return request({
    url: '/comment/' + id,
    method: 'delete'
  })
}


