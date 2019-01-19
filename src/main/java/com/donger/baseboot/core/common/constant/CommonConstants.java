package com.donger.baseboot.core.common.constant;

/**
 * 公共常量
 *
 * @author xyx
 */
public interface CommonConstants {


    Long SUPER_ADMIN = 1L;

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

}
