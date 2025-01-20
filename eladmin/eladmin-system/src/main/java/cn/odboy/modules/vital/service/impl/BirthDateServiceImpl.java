package cn.odboy.modules.vital.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.odboy.infra.exception.BadRequestException;
import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.constant.BirthDateComputeModeEnum;
import cn.odboy.modules.vital.domain.BirthDate;
import cn.odboy.modules.vital.mapper.BirthDateMapper;
import cn.odboy.modules.vital.service.BirthDateService;
import cn.odboy.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 生日管理 服务实现类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Service
public class BirthDateServiceImpl extends ServiceImpl<BirthDateMapper, BirthDate> implements BirthDateService {
    @Override
    public PageResult<BirthDate.SearchBirthDates> searchBirthDates(BirthDate.SearchBirthDates args, Page<BirthDate> page) {
        return PageUtil.toPage(baseMapper.selectBirthDates(page, args));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBirthDate(BirthDate.CreateArgs args) {
        try {
            if (BirthDateComputeModeEnum.LUNAR.getCode().equals(args.getComputeMode()) && args.getRegexStr().contains("null")) {
                throw new BadRequestException("表达式不完整, 请选择年月日～");
            }
            save(BeanUtil.copyProperties(args, BirthDate.class));
        } catch (Exception e) {
            log.error("添加生日失败", e);
            throw new BadRequestException("添加生日失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBirthDates(BirthDate.DeleteArgs args) {
        try {
            removeByIds(args.getIds());
        } catch (Exception e) {
            log.error("删除生日失败", e);
            throw new BadRequestException("删除生日失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyBirthDateNotifyStatus(BirthDate.ModifyBirthDateNotifyStatusArgs args) {
        try {
            updateById(BeanUtil.copyProperties(args, BirthDate.class));
        } catch (Exception e) {
            log.error("更新生日通知开关失败", e);
            throw new BadRequestException("更新生日通知开关失败");
        }
    }
}
