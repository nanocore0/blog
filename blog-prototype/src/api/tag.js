import request from '@/util/request'

export function getTagsList(blogId) {
  return request({
    url: '/tags/blog/' + blogId,
    method: 'get'
  })
}

export function listTopTags() {
  return request({
    url: '/tags/top/',
    method: 'get'
  })
}
