package com.donger.baseboot.core.common.entity;

import com.donger.baseboot.modules.sys.entity.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class UserDetail {

    private SysUser user;
    private List<String> permissions;

}
