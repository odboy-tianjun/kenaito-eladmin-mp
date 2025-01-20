package cn.odboy.modules.vital.domain;

import cn.odboy.model.MyObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 日历字典
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("vital_calendar_dict")
@ApiModel(value = "CalendarDict对象", description = "日历字典")
public class CalendarDict extends MyObject {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("公历年")
    @TableField("gregorian_year")
    private Integer gregorianYear;

    @ApiModelProperty("公历月")
    @TableField("gregorian_month")
    private Integer gregorianMonth;

    @ApiModelProperty("公历日")
    @TableField("gregorian_day")
    private Integer gregorianDay;

    @ApiModelProperty("公历日期")
    @TableField("gregorian_date")
    private String gregorianDate;

    @ApiModelProperty("农历年")
    @TableField("lunar_year")
    private Integer lunarYear;

    @ApiModelProperty("农历月")
    @TableField("lunar_month")
    private String lunarMonth;

    @ApiModelProperty("农历月别名（比如一月又叫正月）")
    @TableField("lunar_month_name")
    private String lunarMonthName;

    @ApiModelProperty("农历日")
    @TableField("lunar_day")
    private String lunarDay;

    @ApiModelProperty("干支周期")
    @TableField("cyclical")
    private String cyclical;

    @ApiModelProperty("干支日期别名")
    @TableField("cyclical_name")
    private String cyclicalName;

    @ApiModelProperty("星座")
    @TableField("zodiac")
    private String zodiac;

    @ApiModelProperty("生肖")
    @TableField("chinese_zodiac")
    private String chineseZodiac;

    @ApiModelProperty("节日列表")
    @TableField("festivals")
    private String festivals;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
