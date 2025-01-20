package cn.odboy.modules.vital.service;

import cn.hutool.core.date.ChineseDate;
import cn.odboy.model.MetaOption;
import cn.odboy.modules.vital.domain.BirthDate;
import cn.odboy.modules.vital.domain.CalendarDict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日历字典 服务类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
public interface CalendarDictService extends IService<CalendarDict> {
    /**
     * 公历年获取字典
     */
    CalendarDict describeByGregorian(ChineseDate rollingDate);
    List<MetaOption> listMetaMonthsByLunarYear(BirthDate.ListMetaMonthByLunarYear args);
    List<MetaOption> listMetaDaysByLunarYearMonth(BirthDate.ListMetaDaysByLunarYearMonth args);
    Date describeLunarDateByLunarYmd(BirthDate.DescribeLunarDateByLunarYmd args);
}
