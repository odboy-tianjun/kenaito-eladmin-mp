package cn.odboy.modules.vital.domain;

import cn.odboy.base.MyEntity;
import cn.odboy.model.MyObject;
import com.alibaba.fastjson.annotation.JSONField;
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
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 人情往来 历史记录
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("vital_favor_dealing_history")
@ApiModel(value = "FavorDealingHistory对象", description = "人情往来")
public class FavorDealingHistory extends MyEntity {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("日期")
    @TableField("event_date")
    private Date eventDate;

    @ApiModelProperty("联系人id")
    @TableField("contact_id")
    private Long contactId;

    @ApiModelProperty("事件名称")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty("礼金")
    @TableField("cash_gift")
    private BigDecimal cashGift;

    @ApiModelProperty("回礼日期")
    @TableField("return_gift_date")
    private Date returnGiftDate;

    @ApiModelProperty("回礼金额")
    @TableField("return_cash_gift")
    private BigDecimal returnCashGift;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class SearchFavorDealingHistorys extends MyObject {
        private Long id;
        @JSONField(format = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date eventDate;
        private Long contactId;
        private String contactRealName;
        private String contactNickName;
        private String eventName;
        private BigDecimal cashGift;
        @JSONField(format = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date returnGiftDate;
        private BigDecimal returnCashGift;
        private String remark;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class CreateArgs extends MyObject {
        @NotNull(message = "事件日期不能为空")
        private Date eventDate;
        @NotNull(message = "姓名不能为空")
        private Long contactId;
        @NotBlank(message = "事件不能为空")
        private String eventName;
        @NotNull(message = "礼金不能为空")
        private BigDecimal cashGift;
        private Date returnGiftDate;
        private BigDecimal returnCashGift;
        private String remark;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class UpdateArgs extends MyObject {
        @NotNull(message = "id不能为空")
        private Long id;
        @NotNull(message = "姓名不能为空")
        private Long contactId;
        private String remark;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ModifyReturnGiftArgs extends MyObject {
        @NotNull(message = "id不能为空")
        private Long id;
        @NotNull(message = "回礼日期不能为空")
        private Date returnGiftDate;
        @NotNull(message = "回礼金额不能为空")
        private BigDecimal returnCashGift;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DeleteArgs extends MyObject {
        @NotNull(message = "ids不能为空")
        @Size(min = 1, message = "ids最小长度为1")
        private List<Long> ids;
    }
}
