import request from '@/utils/request'

export function searchFavorDealingHistorys(data) {
  return request({
    url: 'api/vital/favorDealingHistory/searchFavorDealingHistorys',
    method: 'post',
    data
  })
}

export function createFavorDealingHistory(data) {
  return request({
    url: 'api/vital/favorDealingHistory/createFavorDealingHistory',
    method: 'post',
    data
  })
}

export function updateFavorDealingHistory(data) {
  return request({
    url: 'api/vital/favorDealingHistory/updateFavorDealingHistory',
    method: 'post',
    data
  })
}

export function deleteFavorDealingHistorys(data) {
  return request({
    url: 'api/vital/favorDealingHistory/deleteFavorDealingHistorys',
    method: 'post',
    data
  })
}
export default {
  searchFavorDealingHistorys,
  createFavorDealingHistory,
  updateFavorDealingHistory,
  deleteFavorDealingHistorys
}
