package com.donger.baseboot.modules.sys.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donger.baseboot.modules.sys.entity.SysDept;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.entity.SysUser;
import com.donger.baseboot.modules.sys.mapper.SysDeptMapper;
import com.donger.baseboot.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:51
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    public List<SysDept> getUserDeptList() {
        //查询根部门列表
        List<SysDept> deptList = baseMapper.selectList(Wrappers.query(new SysDept()).eq("parent_id", "0"));
        //递归获取子部门
        getDeptTreeList(deptList, null);

        return deptList;
    }

    @Override
    public List<SysDept> queryListParentId(Long parentId) {
        return baseMapper.selectList(new QueryWrapper<SysDept>().eq("parent_id",parentId));
    }

    /**
     * 递归
     */
    private List<SysDept> getDeptTreeList(List<SysDept> menuList, List<Long> deptIdList) {
        List<SysDept> subDeptList = new ArrayList<SysDept>();
        for (SysDept entity : menuList) {
            entity.setList(getDeptTreeList(queryListParentId(entity.getId()), deptIdList));
            subDeptList.add(entity);
        }

        return subDeptList;
    }
}
