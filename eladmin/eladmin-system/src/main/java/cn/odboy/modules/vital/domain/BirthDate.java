package cn.odboy.modules.vital.domain;

import cn.odboy.base.MyEntity;
import cn.odboy.model.MyObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * <p>
 * 生日管理
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("vital_birth_date")
@ApiModel(value = "BirthDate对象", description = "生日管理")
public class BirthDate extends MyEntity {

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("联系人ID")
    @TableField("contact_id")
    private Long contactId;

    @ApiModelProperty("计算方式：公历、农历")
    @TableField("compute_mode")
    private String computeMode;

    @ApiModelProperty("生日表达式")
    @TableField("regex_str")
    private String regexStr;

    @ApiModelProperty("公历表达式")
    @TableField("gregorian_date")
    private String gregorianDate;

    @ApiModelProperty("农历年表达式")
    @TableField("lunar_year")
    private String lunarYear;

    @ApiModelProperty("农历月表达式")
    @TableField("lunar_month")
    private String lunarMonth;

    @ApiModelProperty("农历日表达式")
    @TableField("lunar_day")
    private String lunarDay;

    @ApiModelProperty("是否通知")
    @TableField("notify_status")
    private Boolean notifyStatus;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("周岁(定时任务统计)")
    @TableField("one_year_old")
    private Integer oneYearOld;

    @ApiModelProperty("虚岁(定时任务统计)")
    @TableField("virtual_year_old")
    private Integer virtualYearOld;

    @ApiModelProperty("出生已经几天(定时任务统计)")
    @TableField("born_days")
    private Long bornDays;

    @ApiModelProperty("距离下一次生日还有几天(定时任务统计)")
    @TableField("next_birthday_days")
    private Long nextBirthdayDays;

    @ApiModelProperty("星座")
    @TableField("zodiac")
    private String zodiac;

    @ApiModelProperty("属相")
    @TableField("chinese_zodiac")
    private String chineseZodiac;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class CreateArgs extends MyObject {
        @NotNull(message = "姓名不能为空")
        private Long contactId;
        @NotBlank(message = "计算方式不能为空")
        private String computeMode;
        private String regexStr;
        private String gregorianDate;
        private String lunarYear;
        private String lunarMonth;
        private String lunarDay;
        private String remark;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ModifyBirthDateNotifyStatusArgs extends MyObject {
        @NotNull(message = "参数id不能为空")
        private Long id;
        @NotNull(message = "参数是否通知不能为空")
        private Boolean notifyStatus;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DeleteArgs extends MyObject {
        @NotNull(message = "ids不能为空")
        @Size(min = 1, message = "ids最小长度为1")
        private List<Long> ids;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class SearchBirthDates extends MyEntity {
        private Long id;
        private Long contactId;
        private String contactRealName;
        private String contactNickName;
        private String computeMode;
        private String regexStr;
        private String gregorianDate;
        private String lunarYear;
        private String lunarMonth;
        private String lunarDay;
        private Boolean notifyStatus;
        private String remark;
        private Integer oneYearOld;
        private Integer virtualYearOld;
        private Long bornDays;
        private Long nextBirthdayDays;
        private String zodiac;
        private String chineseZodiac;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ListMetaMonthByLunarYear extends MyObject {
        @NotBlank(message = "参数lunarYear不能为空")
        private String lunarYear;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ListMetaDaysByLunarYearMonth extends MyObject {
        @NotBlank(message = "参数lunarYear不能为空")
        private String lunarYear;
        @NotBlank(message = "参数lunarMonth不能为空")
        private String lunarMonth;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DescribeLunarDateByLunarYmd extends MyObject {
        @NotBlank(message = "参数lunarYear不能为空")
        private String lunarYear;
        @NotBlank(message = "参数lunarMonth不能为空")
        private String lunarMonth;
        @NotBlank(message = "参数lunarDay不能为空")
        private String lunarDay;
    }
}
