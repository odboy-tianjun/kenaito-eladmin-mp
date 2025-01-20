package cn.odboy.modules.vital.mapper;

import cn.odboy.infra.mybatisplus.EasyMapper;
import cn.odboy.modules.vital.domain.BirthDateTimeline;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * LIFE生日: 时间线 Mapper 接口
 * </p>
 *
 * @author odboy
 * @since 2021-12-15
 */
@Mapper
public interface BirthDateTimelineMapper extends EasyMapper<BirthDateTimeline> {
    void deleteBirthDateTimelines();
}
