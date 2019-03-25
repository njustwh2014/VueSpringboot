import request from '@/utils/request'

export function getAllTag(params) {
  return request({
    url: '/tag/getall',
    method: 'post',
    data:{
      pageNumber:params.pageNumber,
      pageSize:params.pageSize
    }
  })
}
export function changeTagStatus(id,status){
  return request({
    url:'tag/changestatus',
    method:'post',
    data:{
      id:id,
      tagstatus:status
    }
  })
}
export function addTag(newtag,categoryid){
  return request({
    url:'tag/add',
    method:'post',
    data:{
      newtag:newtag,
      categoryid:categoryid
    }
  })
}

export function deleteTag(params){
  return request({
    url:'tag/delete',
    method:'post',
    data:{
      id:params
    }
  })
}