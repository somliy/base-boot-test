package com.donger.baseboot.core.web;

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
