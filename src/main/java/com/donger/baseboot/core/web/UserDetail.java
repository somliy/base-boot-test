package com.donger.baseboot.core.web;

import com.donger.baseboot.modules.sys.entity.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class UserDetail {

    private SysUser user;
    private List<String> permissions;

}
