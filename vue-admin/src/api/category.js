import request from '@/utils/request'

export function getAllCategory(params) {
  return request({
    url: '/category/getall',
    method: 'post',
    data:{
      pageNumber:params.pageNumber,
      pageSize:params.pageSize
    }
  })
}
export function changeCategoryStatus(id,status){
  return request({
    url:'category/changestatus',
    method:'post',
    data:{
      id:id,
      categorystatus:status
    }
  })
}
export function addCategory(newcategory){
  return request({
    url:'category/add',
    method:'post',
    data:{
      newcategory:newcategory
    }
  })
}

export function deleteCategory(params){
  return request({
    url:'category/delete',
    method:'post',
    data:{
      id:params
    }
  })
}