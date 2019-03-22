import request from '@/utils/request'

//用户管理api
export function getAllUser(params) {
  return request({
    url: '/user/getall',
    method: 'post',
    data:{
      pageNumber:params.pageNumber,
      pageSize:params.pageSize
    }
  })
}

export function deleteUser(params){
  return request({
    url:'user/delete',
    method:'post',
    data:{
      id:params
    }
  })
}