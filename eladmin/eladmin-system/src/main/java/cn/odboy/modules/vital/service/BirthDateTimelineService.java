package cn.odboy.modules.vital.service;

import cn.odboy.modules.vital.domain.BirthDateTimeline;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 生日时间线 服务类
 * </p>
 *
 * @author odboy
 * @since 2021-12-15
 */
public interface BirthDateTimelineService extends IService<BirthDateTimeline> {
    void deleteAll();

    /**
     * 查询当年时间线列表
     *
     * @return List<BirthdayTimeline>
     */
    List<BirthDateTimeline> describeBirthdayTimelines();

    BirthDateTimeline findOneByDate(Integer nowYear, String gregorianDate, Long contactId);

    List<BirthDateTimeline> findCurrentYearNewestList();
}
