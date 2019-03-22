import request from '@/utils/request'

export function getAllArticles(params) {
  return request({
    url: '/articles/getall',
    method: 'post',
    data:{
      pageNumber:params.pageNumber,
      pageSize:params.pageSize
    }
  })
}

export function deleteArticle(params){
  return request({
    url:'articles/delete',
    method:'post',
    data:{
      articleid:params
    }
  })
}