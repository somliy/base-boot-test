package com.donger.baseboot.core.common.web;

import com.donger.baseboot.core.common.entity.UserDetail;
import com.donger.baseboot.modules.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;


public class BaseController {

    protected UserDetail getUserDetail() {
        return (UserDetail) SecurityUtils.getSubject().getPrincipal();
    }

    protected SysUser getUser() {
        return getUserDetail().getUser();
    }


}
