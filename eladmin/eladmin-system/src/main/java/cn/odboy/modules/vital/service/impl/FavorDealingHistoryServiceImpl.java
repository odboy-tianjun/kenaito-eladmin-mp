package cn.odboy.modules.vital.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.odboy.infra.exception.BadRequestException;
import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.domain.FavorDealingHistory;
import cn.odboy.modules.vital.mapper.FavorDealingHistoryMapper;
import cn.odboy.modules.vital.service.FavorDealingHistoryService;
import cn.odboy.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 人情往来 服务实现类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Service
public class FavorDealingHistoryServiceImpl extends ServiceImpl<FavorDealingHistoryMapper, FavorDealingHistory> implements FavorDealingHistoryService {

    @Override
    public PageResult<FavorDealingHistory.SearchFavorDealingHistorys> searchFavorDealingHistorys(FavorDealingHistory args, Page<FavorDealingHistory> page) {
        return PageUtil.toPage(baseMapper.selectFavorDealingHistorys(page, args));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFavorDealingHistory(FavorDealingHistory.CreateArgs args) {
        try {
            save(BeanUtil.copyProperties(args, FavorDealingHistory.class));
        } catch (Exception e) {
            log.error("添加人情来往历史记录失败", e);
            throw new BadRequestException("添加人情来往历史记录失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFavorDealingHistory(FavorDealingHistory.UpdateArgs args) {
        try {
            updateById(BeanUtil.copyProperties(args, FavorDealingHistory.class));
        } catch (Exception e) {
            log.error("更新人情来往历史记录失败", e);
            throw new BadRequestException("更新人情来往历史记录失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFavorDealingHistorys(FavorDealingHistory.DeleteArgs args) {
        try {
            removeByIds(args.getIds());
        } catch (Exception e) {
            log.error("删除人情来往历史记录失败", e);
            throw new BadRequestException("删除人情来往历史记录失败");
        }
    }
}
