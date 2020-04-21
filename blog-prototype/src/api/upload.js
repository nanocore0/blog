import request from '@/util/request'

export function upload(file) {
  return request({
    url: '/upload/file',
    method: 'post',
    data: file
  })
}

