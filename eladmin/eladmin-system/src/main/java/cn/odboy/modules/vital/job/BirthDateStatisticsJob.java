package cn.odboy.modules.vital.job;

import cn.hutool.core.date.*;
import cn.odboy.modules.vital.constant.BirthDateComputeModeEnum;
import cn.odboy.modules.vital.domain.BirthDate;
import cn.odboy.modules.vital.service.BirthDateService;
import cn.odboy.modules.vital.service.CalendarDictService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生日相关统计 任务
 * </p>
 *
 * @author odboy
 * @date 2021-03-26
 */
@Slf4j
@Service
public class BirthDateStatisticsJob {
    @Autowired
    private BirthDateService birthDateService;
    @Autowired
    private CalendarDictService calendarDictService;

    public void run() {
        long currentPage = 1L;
        while (true) {
            IPage<BirthDate> birthdayPage = new Page<>(currentPage, 500L);
            birthdayPage = birthDateService.page(birthdayPage);
            List<BirthDate> birthdays = birthdayPage.getRecords();
            if (birthdays.isEmpty()) {
                break;
            }
            for (BirthDate datum : birthdays) {
                if (BirthDateComputeModeEnum.GREGORIAN.getCode().equals(datum.getComputeMode())) {
                    handleGregorianBirthDate(datum);
                } else if (BirthDateComputeModeEnum.LUNAR.getCode().equals(datum.getComputeMode())) {
                    try {
                        handleLunarBirthDate(datum);
                    } catch (Exception e) {
                        log.error("处理农历日期失败", e);
                    }
                }
                birthDateService.updateById(datum);
            }
            currentPage = currentPage + 1;
        }
    }

    /**
     * 公历生日
     *
     * @param datum /
     */
    private void handleGregorianBirthDate(BirthDate datum) {
        // 出生日
        Date initBirthDate = DateUtil.parseDate(datum.getRegexStr());
        // 现行生日
        String[] initBirthDateArrays = datum.getRegexStr().split("-");
        Date nowDate = DateUtil.date();
        int nowYear = DateUtil.year(nowDate);
        String nowBirthDateStr = String.format("%s-%s-%s", nowYear, initBirthDateArrays[1], initBirthDateArrays[2]);
        Date nowBirthDate = DateUtil.parseDate(nowBirthDateStr);
        // 计算年份差
        // 出生的那年
        int birthYear = DateUtil.year(initBirthDate);
        // 今天是哪一年
        int todayYear = DateUtil.year(nowDate);
        int oneYearOld;
        if (nowDate.getTime() > nowBirthDate.getTime()) {
            // 过完生日
            oneYearOld = todayYear - birthYear;
        } else {
            // 未过生日
            oneYearOld = todayYear - birthYear - 1;
        }
        // 设置周岁
        datum.setOneYearOld(oneYearOld);
        // 设置虚岁
        datum.setVirtualYearOld(oneYearOld + 1);
        // 出生已经几天
        long bornDays = DateUtil.between(initBirthDate, nowDate, DateUnit.DAY);
        datum.setBornDays(bornDays);
        // 距离下一次生日还剩下几天
        if (nowDate.getTime() > nowBirthDate.getTime()) {
            // 过完生日, 计算明年的生日
            Date nextBirth = DateUtil.offset(nowBirthDate, DateField.YEAR, 1);
            datum.setNextBirthdayDays(DateUtil.between(nowDate, nextBirth, DateUnit.DAY));
        } else {
            // 未过生日
            datum.setNextBirthdayDays(DateUtil.between(nowDate, nowBirthDate, DateUnit.DAY) + 1);
        }
        // 设置星座
        datum.setZodiac(Zodiac.getZodiac(initBirthDate));
        // 设置属相
        datum.setChineseZodiac(new ChineseDate(initBirthDate).getChineseZodiac());
    }

    private void handleLunarBirthDate(BirthDate datum) {
        // 出生日
        // 比如: 1995年的公历生日
        BirthDate.DescribeLunarDateByLunarYmd args = new BirthDate.DescribeLunarDateByLunarYmd();
        args.setLunarYear(datum.getLunarYear());
        args.setLunarMonth(datum.getLunarMonth());
        args.setLunarDay(datum.getLunarDay());
        Date initBirthDate = calendarDictService.describeLunarDateByLunarYmd(args);
        // 今天
        Date nowDate = DateUtil.date();
        // 今年生日
        Date thisYearBirthDate;
        try {
            args.setLunarYear(String.valueOf(DateUtil.year(nowDate)));
            thisYearBirthDate = calendarDictService.describeLunarDateByLunarYmd(args);
        } catch (Exception e) {
            log.warn("这位仁兄可能不是闰年的生日", e);
//            args.setLunarYear(String.valueOf(DateUtil.year(nowDate)));
            args.setLunarMonth(args.getLunarMonth().replace("闰", ""));
            thisYearBirthDate = calendarDictService.describeLunarDateByLunarYmd(args);
        }
        // 计算年份差
        // 出生的那年
        int birthYear = DateUtil.year(initBirthDate);
        // 今天是哪一年
        int todayYear = DateUtil.year(nowDate);
        int oneYearOld;
        if (nowDate.getTime() > thisYearBirthDate.getTime()) {
            // 过完生日
            oneYearOld = todayYear - birthYear;
        } else {
            // 未过生日
            oneYearOld = todayYear - birthYear - 1;
        }
        // 设置周岁
        datum.setOneYearOld(oneYearOld);
        // 设置虚岁
        datum.setVirtualYearOld(oneYearOld + 1);
        // 出生已经几天
        long bornDays = DateUtil.between(initBirthDate, nowDate, DateUnit.DAY);
        datum.setBornDays(bornDays);
        // 距离下一次生日还剩下几天
        if (nowDate.getTime() > thisYearBirthDate.getTime()) {
            // 过完生日, 计算明年的生日
            Date nextBirth;
            try {
                args.setLunarYear(String.valueOf(DateUtil.year(DateTime.now()) + 1));
                nextBirth = calendarDictService.describeLunarDateByLunarYmd(args);
            } catch (Exception e) {
                log.warn("这位仁兄可能不是闰年的生日", e);
//                args.setLunarYear(String.valueOf(DateUtil.year(DateTime.now()) + 1));
                nextBirth = calendarDictService.describeLunarDateByLunarYmd(args);
            }
            datum.setNextBirthdayDays(DateUtil.between(nowDate, nextBirth, DateUnit.DAY));
        } else {
            // 未过生日
            datum.setNextBirthdayDays(DateUtil.between(nowDate, thisYearBirthDate, DateUnit.DAY) + 1);
        }
        // 设置星座
        datum.setZodiac(Zodiac.getZodiac(initBirthDate));
        // 设置属相
        datum.setChineseZodiac(new ChineseDate(initBirthDate).getChineseZodiac());
    }
}
