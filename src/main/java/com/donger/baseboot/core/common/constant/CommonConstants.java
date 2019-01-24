package com.donger.baseboot.core.common.constant;

/**
 * 公共常量
 *
 * @author xyx
 */
public interface CommonConstants {


    int SUPER_ADMIN = 1;

    /**
     * 通用用户角色
     */
    String BASE_ROLE = "ROLE_USER";

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /**
     * 实体类删除标记
     */
    String DELETE_FLAG = "1";

    /**
     * 实体类正常标记
     */
    String NORMAL_FLAG = "0";

    /**
     * 响应成功码
     */
    int SUCCESS_CODE = 0;

    /**
     * 响应失败码
     */
    int FAIL_CODE = 1;

    String CREATE_BY = "createBy";

    String CREATE_DATA = "createData";

    String UPDATE_DATA = "updateData";

    /**
     * 菜单类型,0:目录，1:菜单，2:按钮
     */
    String MENU_TYPE_CATALOG = "0";

    String MENU_TYPE_MENU = "1";

    String MENU_TYPE_BUTTON = "2";



    /**
     * redis token  前传
     */
    String DEFAULT_TOKEN_KEY = "DEFAULT_TOKEN_KEY_";

    String DEFATULT_PHONE_KEY = "DEFAULT_PHONE_KEY_";


}
