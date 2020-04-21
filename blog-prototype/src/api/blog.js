import request from '@/util/request'

export function getBlogsList(params) {
  return request({
    url: '/blogs',
    method: 'get',
    params
  })
}

export function getBlogsListByUser(userId, params) {
  return request({
    url: '/blogs/user/' + userId,
    method: 'get',
    params
  })
}


export function saveBlog(blog) {
  return request({
    url: '/blog',
    method: 'post',
    data: blog,
  })
}

export function updateBlog(id, blog) {
  return request({
    url: '/blog/' + id,
    method: 'put',
    data: blog,
  })
}

export function getBlogDetail(id) {
  return request({
    url: '/blog/' + id,
    method: 'get',
  })
}

export function  deleteBlog(id) {
  return request({
    url: '/blog/' + id,
    method: 'delete',
  })
}

export function listHotestBlogs() {
  return request({
    url: '/blogs/hotest',
    method: 'get'
  })
}

export function listNewestBlogs() {
  return request({
    url: '/blogs/newest',
    method: 'get'
  })
}

export function search(params) {
  return request({
    url: '/search',
    method: 'get',
    params
  })
}
