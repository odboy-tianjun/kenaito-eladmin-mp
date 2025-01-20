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
 * 联系人管理
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("vital_contact_info")
@ApiModel(value = "ContactInfo对象", description = "联系人管理")
public class ContactInfo extends MyEntity {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("称谓")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("所属标签")
    @TableField("tag_name")
    private String tagName;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class CreateArgs extends MyObject {
        @NotBlank(message = "姓名不能为空")
        private String realName;
        private String nickName;
        private String tagName;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class UpdateArgs extends MyObject {
        @NotNull(message = "id不能为空")
        private Long id;
        @NotBlank(message = "姓名不能为空")
        private String realName;
        private String nickName;
        private String tagName;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class DeleteArgs extends MyObject {
        @NotNull(message = "ids不能为空")
        @Size(min = 1, message = "ids最小长度为1")
        private List<Long> ids;
    }
}
