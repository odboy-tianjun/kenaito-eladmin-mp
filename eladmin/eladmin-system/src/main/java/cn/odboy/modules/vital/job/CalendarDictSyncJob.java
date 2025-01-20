package cn.odboy.modules.vital.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Zodiac;
import cn.odboy.modules.vital.domain.CalendarDict;
import cn.odboy.modules.vital.service.CalendarDictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日历字典 同步任务
 *
 * @author odboy
 * @date 2025-01-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarDictSyncJob {
    private final CalendarDictService calendarDictService;

    public void run() {
        List<CalendarDict> batchSaveList = new ArrayList<>();
        List<CalendarDict> batchUpdateList = new ArrayList<>();
        int startYear = 1910;
        int endYear = 2090;
        Calendar calendar = Calendar.getInstance();
        calendar.set(startYear, Calendar.JANUARY, 1);
        Date startTime = calendar.getTime();
        while (DateUtil.year(startTime) < endYear) {
            ChineseDate rollingDate = new ChineseDate(startTime);
            CalendarDict record = calendarDictService.describeByGregorian(rollingDate);
            if (record == null) {
                record = new CalendarDict();
                copyProperties(record, rollingDate, startTime);
                batchSaveList.add(record);
            } else {
                copyProperties(record, rollingDate, startTime);
                batchUpdateList.add(record);
            }
            startTime = DateUtil.offsetDay(startTime, 1);
        }
        if (CollUtil.isNotEmpty(batchSaveList)) {
            try {
                calendarDictService.saveBatch(batchSaveList);
                log.info("批量保存日历字典成功");
            } catch (Exception e) {
                log.info("批量保存日历字典失败", e);
            }
        }
        if (CollUtil.isNotEmpty(batchUpdateList)) {
            try {
                calendarDictService.updateBatchById(batchUpdateList);
                log.info("批量更新日历字典成功");
            } catch (Exception e) {
                log.info("批量更新日历字典失败", e);
            }
        }
    }

    private void copyProperties(CalendarDict record, ChineseDate rollingDate, Date startTime) {
        record.setGregorianYear(rollingDate.getGregorianYear());
        record.setGregorianMonth(rollingDate.getGregorianMonthBase1());
        record.setGregorianDay(rollingDate.getGregorianDay());
        record.setGregorianDate(DateUtil.formatDate(startTime));
        record.setLunarYear(rollingDate.getChineseYear());
        record.setLunarMonth(rollingDate.getChineseMonth());
        record.setLunarMonthName(rollingDate.getChineseMonthName());
        record.setLunarDay(rollingDate.getChineseDay());
        record.setCyclical(rollingDate.getCyclical());
        record.setCyclicalName(rollingDate.getCyclicalYMD());
        record.setChineseZodiac(rollingDate.getChineseZodiac());
        record.setZodiac(Zodiac.getZodiac(startTime));
        record.setFestivals(rollingDate.getFestivals());
        record.setRemark(rollingDate.toString());
    }
}
