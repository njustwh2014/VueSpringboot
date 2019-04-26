import request from '@/request'

//获取当前用户是否有未读消息
export function getMessageStatus(userid) {
  return request({
    url: `/message/unreadstatus/${userid}`,
    method: 'get'
  })
}