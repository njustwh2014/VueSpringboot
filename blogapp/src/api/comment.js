import request from '@/request'


export function getCommentsByArticle(id) {
  return request({
    url: `/comments/article/${id}`,
    method: 'get'
  })
}

export function publishComment(comment) {
  return request({
    url: '/comments/publish',
    method: 'post',
    data: comment
  })
}

