package com.donger.baseboot.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donger.baseboot.modules.sys.entity.SysDept;
import com.donger.baseboot.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:51
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * 获取树形部门列表
     * @return
     */
    List<SysDept> getUserDeptList();

    /**
     * 根据父部门，查询子部门
     * @param parentId 父部门ID
     */
    List<SysDept> queryListParentId(Long parentId);
}
