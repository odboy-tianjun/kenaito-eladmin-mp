package cn.odboy.modules.vital.mapper;

import cn.odboy.modules.vital.domain.BirthDate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 生日管理 Mapper 接口
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Mapper
public interface BirthDateMapper extends BaseMapper<BirthDate> {
    List<BirthDate.SearchBirthDates> selectBirthDates(Page<?> page, @Param("args") BirthDate.SearchBirthDates args);
}
