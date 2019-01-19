package com.donger.baseboot.core.common.enums;

/**
 * 菜单类型枚举
 *
 * @author xyx
 */
public enum MenuTypeEnum {

    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
