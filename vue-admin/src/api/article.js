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

export function changeArticleStatus(id,status){
  return request({
    url:'articles/changestatus',
    method:'post',
    data:{
      id:id,
      articlestatus:status
    }
  })
}

export function dialogGetInfo(id){
  return request({
    url:'articles/dialoggetinfo',
    method:'post',
    data:{
      id:id
    }
  })
}

export function updateArticle(param){
  return request({
    url:'articles/updatearticle',
    method:'post',
    data:{
      id:param.id,
      title:param.title,
      articlestatus:param.articlestatus,
      summary:param.summary,
      content:param.content,
      tags:param.tags,
      category:param.category.id
    }
  })
}