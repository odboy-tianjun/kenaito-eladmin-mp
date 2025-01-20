import request from '@/utils/request'

export function searchBirthDates(data) {
  return request({
    url: 'api/vital/birthDate/searchBirthDates',
    method: 'post',
    data
  })
}

export function createBirthDate(data) {
  return request({
    url: 'api/vital/birthDate/createBirthDate',
    method: 'post',
    data
  })
}

export function modifyBirthDateNotifyStatus(data) {
  return request({
    url: 'api/vital/birthDate/modifyBirthDateNotifyStatus',
    method: 'post',
    data
  })
}

export function deleteBirthDates(data) {
  return request({
    url: 'api/vital/birthDate/deleteBirthDates',
    method: 'post',
    data
  })
}

export default {
  searchBirthDates,
  createBirthDate,
  modifyBirthDateNotifyStatus,
  deleteBirthDates
}
