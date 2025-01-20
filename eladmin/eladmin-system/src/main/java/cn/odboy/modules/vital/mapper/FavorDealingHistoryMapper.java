package cn.odboy.modules.vital.mapper;

import cn.odboy.infra.mybatisplus.EasyMapper;
import cn.odboy.modules.vital.domain.FavorDealingHistory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 人情往来 Mapper 接口
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Mapper
public interface FavorDealingHistoryMapper extends EasyMapper<FavorDealingHistory> {
    List<FavorDealingHistory.SearchFavorDealingHistorys> selectFavorDealingHistorys(Page<?> page, @Param("args") FavorDealingHistory args);
}
