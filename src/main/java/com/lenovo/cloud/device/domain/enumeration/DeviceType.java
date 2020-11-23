package com.lenovo.cloud.device.domain.enumeration;

/**
 * The DeviceType enumeration.
 */
public enum DeviceType {

    POWER(1),
    ROBOT(2),
    INFRARED(3),
    VISIBLE_LIGHT(4);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    DeviceType(Integer value) {
        this.value = value;
    }

}
