package com.donger.baseboot.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donger.baseboot.core.utils.Res;
import com.donger.baseboot.core.utils.Result;
import com.donger.baseboot.modules.sys.entity.SysDept;
import com.donger.baseboot.modules.sys.entity.SysMenu;
import com.donger.baseboot.modules.sys.service.SysDeptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Author: szwei
 * @date : 2019/1/19 20:52
 */
@RestController
@RequestMapping("/sys/dept")
@AllArgsConstructor
@Slf4j
public class SysDeptController {
    
    private final SysDeptService sysDeptService;

    /**
     * 所有部门列表
     */
    @GetMapping("/page")
    public Result<IPage<SysDept>> page(Page<SysDept> page, SysDept entity){
        IPage<SysDept> list = sysDeptService.page(page, Wrappers.query(entity));
        return Res.ok(list);
    }

    /**
     * 添加部门
     * @param entity
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SysDept entity){
        sysDeptService.save(entity);
        return Res.ok();
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(Long id){
        sysDeptService.removeById(id);
        return Res.ok();
    }

    /**
     * 批量删除部门
     * @param ids
     * @return
     */
    @GetMapping("/beath/delete")
    public Result beathDelete(List ids){
        sysDeptService.removeByIds(ids);
        return Res.ok();
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysDept entity){
        sysDeptService.updateById(entity);
        return Res.ok();
    }

    /**
     * 根据id查询部门信息
     */
    @PostMapping("/info/{deptId}")
    public Result userInfo(@PathParam("deptId") Long deptId){
        SysDept dept = sysDeptService.getById(deptId);
        return Res.ok(dept);
    }
}
