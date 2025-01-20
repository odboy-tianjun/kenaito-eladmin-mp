package cn.odboy.modules.vital.service;

import cn.odboy.model.MetaOption;
import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.domain.ContactInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 联系人管理 服务类
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
public interface ContactInfoService extends IService<ContactInfo> {
    PageResult<ContactInfo> searchContactInfos(ContactInfo args, Page<ContactInfo> page);

    void createContactInfo(ContactInfo.CreateArgs args);

    void updateContactInfo(ContactInfo.UpdateArgs args);

    void deleteContactInfos(ContactInfo.DeleteArgs args);

    List<MetaOption> listMetaContactInfos(ContactInfo args, Page<ContactInfo> page);
}
