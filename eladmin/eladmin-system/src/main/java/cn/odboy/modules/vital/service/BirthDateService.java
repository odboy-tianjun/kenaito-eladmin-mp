package cn.odboy.modules.vital.service;

import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.domain.BirthDate;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 生日管理 服务类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
public interface BirthDateService extends IService<BirthDate> {
    PageResult<BirthDate.SearchBirthDates> searchBirthDates(BirthDate.SearchBirthDates args, Page<BirthDate> page);
    void createBirthDate(BirthDate.CreateArgs args);
    void deleteBirthDates(BirthDate.DeleteArgs args);
    void modifyBirthDateNotifyStatus(BirthDate.ModifyBirthDateNotifyStatusArgs args);
}
