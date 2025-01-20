package cn.odboy.modules.vital.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.odboy.infra.exception.BadRequestException;
import cn.odboy.model.MetaOption;
import cn.odboy.modules.vital.domain.BirthDate;
import cn.odboy.modules.vital.domain.CalendarDict;
import cn.odboy.modules.vital.mapper.CalendarDictMapper;
import cn.odboy.modules.vital.service.CalendarDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 日历字典 服务实现类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Service
public class CalendarDictServiceImpl extends ServiceImpl<CalendarDictMapper, CalendarDict> implements CalendarDictService {

    @Override
    public CalendarDict describeByGregorian(ChineseDate rollingDate) {
        return lambdaQuery()
                .eq(CalendarDict::getGregorianYear, rollingDate.getGregorianYear())
                .eq(CalendarDict::getGregorianMonth, rollingDate.getGregorianMonth())
                .eq(CalendarDict::getGregorianDay, rollingDate.getGregorianDay())
                .last("limit 1")
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetaOption> listMetaMonthsByLunarYear(BirthDate.ListMetaMonthByLunarYear args) {
        List<CalendarDict> calendarDicts = lambdaQuery()
                .eq(CalendarDict::getLunarYear, args.getLunarYear())
                .groupBy(CalendarDict::getLunarMonth)
                .orderByAsc(CalendarDict::getId)
                .list();
        if (CollUtil.isEmpty(calendarDicts)) {
            return new ArrayList<>();
        }
        return calendarDicts.stream().map(m -> MetaOption.builder()
                .label(m.getLunarMonth())
                .value(m.getLunarMonth())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<MetaOption> listMetaDaysByLunarYearMonth(BirthDate.ListMetaDaysByLunarYearMonth args) {
        List<CalendarDict> calendarDicts = lambdaQuery()
                .eq(CalendarDict::getLunarYear, args.getLunarYear())
                .eq(CalendarDict::getLunarMonth, args.getLunarMonth())
                .groupBy(CalendarDict::getLunarDay)
                .orderByAsc(CalendarDict::getId)
                .list();
        if (CollUtil.isEmpty(calendarDicts)) {
            return new ArrayList<>();
        }
        return calendarDicts.stream().map(m -> MetaOption.builder()
                .label(m.getLunarDay())
                .value(m.getLunarDay())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Date describeLunarDateByLunarYmd(BirthDate.DescribeLunarDateByLunarYmd args) {
        CalendarDict calendarDict = lambdaQuery()
                .eq(CalendarDict::getLunarYear, args.getLunarYear())
                .eq(CalendarDict::getLunarMonth, args.getLunarMonth())
                .eq(CalendarDict::getLunarDay, args.getLunarDay())
                .one();
        if (calendarDict == null) {
            throw new BadRequestException("农历转公历年失败");
        }
        return DateUtil.parseDate(calendarDict.getGregorianDate());
    }
}
