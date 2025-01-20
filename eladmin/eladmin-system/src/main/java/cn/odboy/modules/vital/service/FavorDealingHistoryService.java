package cn.odboy.modules.vital.service;

import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.domain.FavorDealingHistory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人情往来 服务类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
public interface FavorDealingHistoryService extends IService<FavorDealingHistory> {

    PageResult<FavorDealingHistory.SearchFavorDealingHistorys> searchFavorDealingHistorys(FavorDealingHistory args, Page<FavorDealingHistory> page);

    void createFavorDealingHistory(FavorDealingHistory.CreateArgs args);

    void updateFavorDealingHistory(FavorDealingHistory.UpdateArgs args);

    void deleteFavorDealingHistorys(FavorDealingHistory.DeleteArgs args);
}
