package cn.odboy.modules.vital.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.odboy.infra.mybatisplus.impl.EasyServiceImpl;
import cn.odboy.modules.vital.domain.BirthDateTimeline;
import cn.odboy.modules.vital.mapper.BirthDateTimelineMapper;
import cn.odboy.modules.vital.service.BirthDateTimelineService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生日时间线 服务实现类
 * </p>
 *
 * @author odboy
 * @since 2021-12-15
 */
@Service
public class BirthDateTimelineServiceImpl extends EasyServiceImpl<BirthDateTimelineMapper, BirthDateTimeline> implements BirthDateTimelineService {
    @Override
    public void deleteAll() {
        baseMapper.deleteBirthDateTimelines();
    }

    @Override
    public List<BirthDateTimeline> describeBirthdayTimelines() {
        int year = DateUtil.year(new Date());
        List<BirthDateTimeline> birthdayTimelines = this.quickList(lambdaQuery().eq(BirthDateTimeline::getCurrentYear, year)
                .orderByAsc(BirthDateTimeline::getCurrentBirthday)
        );
        Date nowDate = new Date();
        int nm = DateUtil.month(nowDate);
        for (BirthDateTimeline birthdayTimeline : birthdayTimelines) {
            int om = DateUtil.month(birthdayTimeline.getCurrentBirthday());
            if (nm == om) {
                birthdayTimeline.setInProgress(true);
                break;
            }
        }
        return birthdayTimelines;
    }

    @Override
    public BirthDateTimeline findOneByDate(Integer nowYear, String gregorianDate, Long contactId) {
        return lambdaQuery()
                .eq(BirthDateTimeline::getCurrentYear, nowYear)
                .eq(BirthDateTimeline::getCurrentBirthday, gregorianDate)
                .eq(BirthDateTimeline::getContactId, contactId)
                .one();
    }

    @Override
    public List<BirthDateTimeline> findCurrentYearNewestList() {
        DateTime now = DateTime.now();
        return lambdaQuery()
                .eq(BirthDateTimeline::getCurrentYear, DateUtil.year(now))
                .gt(BirthDateTimeline::getCurrentBirthday, DateUtil.formatDate(now))
                .orderByAsc(BirthDateTimeline::getCurrentBirthday)
                .list();
    }
}
