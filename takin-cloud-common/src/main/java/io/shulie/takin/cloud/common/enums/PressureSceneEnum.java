package io.shulie.takin.cloud.common.enums;

import java.util.HashMap;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * @author zhaoyong
 */
@Getter
@AllArgsConstructor
public enum PressureSceneEnum {
    /**
     * 常规模式
     */
    DEFAULT(0, "常规模式"),
    /**
     * 流量调试
     */
    FLOW_DEBUG(3, "流量调试"),
    /**
     * 巡检模式
     */
    INSPECTION_MODE(4, "巡检模式"),
    /**
     * 试跑模式
     */
    TRY_RUN(5, "试跑模式");

    private final int code;
    private final String description;

    private static final HashMap<Integer, PressureSceneEnum> INSTANCES = new HashMap<>(4);

    static {
        for (PressureSceneEnum e : PressureSceneEnum.values()) {
            INSTANCES.put(e.getCode(), e);
        }
        //为了兼容老版本数据，将1，2转化为常规模式
        INSTANCES.put(1, DEFAULT);
        INSTANCES.put(2, DEFAULT);
    }

    public static PressureSceneEnum of(Integer code) {
        if (null == code) {
            return null;
        }
        return INSTANCES.get(code);
    }

    /**
     * PressureTypeEnums.equels(code)
     */
    public boolean equals(Integer code) {
        PressureSceneEnum pressureType = of(code);
        return this == pressureType;
    }
}
