package cn.odboy.modules.vital.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author odboy
 * @date 2021/3/17
 */
@Getter
@AllArgsConstructor
public enum BirthDateComputeModeEnum {
    /**
     * 生日计算方式
     */
    GREGORIAN("公历", "gregorian"),
    LUNAR("农历", "lunar");
    private final String title;
    private final String code;
}