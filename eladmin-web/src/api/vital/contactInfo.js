import request from '@/utils/request'

export function searchContactInfos(data) {
  return request({
    url: 'api/vital/contactInfo/searchContactInfos',
    method: 'post',
    data
  })
}

export function createContactInfo(data) {
  return request({
    url: 'api/vital/contactInfo/createContactInfo',
    method: 'post',
    data
  })
}

export function updateContactInfo(data) {
  return request({
    url: 'api/vital/contactInfo/updateContactInfo',
    method: 'post',
    data
  })
}

export function deleteContactInfos(data) {
  return request({
    url: 'api/vital/contactInfo/deleteContactInfos',
    method: 'post',
    data
  })
}

export function listMetaContactInfos(data) {
  return request({
    url: 'api/vital/contactInfo/listMetaContactInfos',
    method: 'post',
    data
  })
}

export default {
  searchContactInfos,
  createContactInfo,
  updateContactInfo,
  deleteContactInfos,
  listMetaContactInfos
}
