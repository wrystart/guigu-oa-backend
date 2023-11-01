package com.atguigu.process.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.process.ProcessType;
import com.atguigu.process.service.OaProcessTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 审批类型管理
 * @Author Ruiyang Wang
 * @Version 1.0
 * @Date 2023/11/1 16:05
 */
@Api(value = "审批类型管理", tags = "审批类型管理")
@RestController
@RequestMapping(value = "/admin/process/processType")
public class OaProcessTypeController {

    @Autowired
    private OaProcessTypeService oaProcessTypeService;

    @ApiOperation(value = "获取全部审批类型")
    @GetMapping("findAll")
    public Result findAll() {
        List<ProcessType> list = oaProcessTypeService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit) {
        Page<ProcessType> pageParam = new Page<>(page,limit);
        IPage<ProcessType> pageModel = oaProcessTypeService.page(pageParam);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取审批类型")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        ProcessType processType = oaProcessTypeService.getById(id);
        return Result.ok(processType);
    }

    @ApiOperation(value = "添加审批类型")
    @PostMapping("save")
    public Result save(@RequestBody ProcessType processType) {
        oaProcessTypeService.save(processType);
        return Result.ok();
    }

    @ApiOperation(value = "修改审批类型")
    @PutMapping("update")
    public Result updateById(@RequestBody ProcessType processType) {
        oaProcessTypeService.updateById(processType);
        return Result.ok();
    }

    @ApiOperation(value = "删除审批类型")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        oaProcessTypeService.removeById(id);
        return Result.ok();
    }
}

