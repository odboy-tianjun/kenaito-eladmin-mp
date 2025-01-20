import request from '@/utils/request'

export function listMetaMonthsByLunarYear(data) {
  return request({
    url: 'api/vital/calendarDict/listMetaMonthsByLunarYear',
    method: 'post',
    data
  })
}

export function listMetaDaysByLunarYearMonth(data) {
  return request({
    url: 'api/vital/calendarDict/listMetaDaysByLunarYearMonth',
    method: 'post',
    data
  })
}

export function describeLunarDateByLunarYmd(data) {
  return request({
    url: 'api/vital/calendarDict/describeLunarDateByLunarYmd',
    method: 'post',
    data
  })
}

export default {
  listMetaMonthsByLunarYear,
  listMetaDaysByLunarYearMonth,
  describeLunarDateByLunarYmd
}
