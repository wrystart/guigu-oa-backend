package com.atguigu.process.controller;


import com.atguigu.common.result.Result;
import com.atguigu.model.process.ProcessTemplate;
import com.atguigu.process.service.OaProcessTemplateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 审批模板管理
 * @Author Ruiyang Wang
 * @Version 1.0
 * @Date 2023/11/1 18:06
 */
@RestController
@RequestMapping(value = "/admin/process/processTemplate")
public class OaProcessTemplateController {

    @Autowired
    private OaProcessTemplateService oaProcessTemplateService;

    //分页查询审批模板
    @ApiOperation("获取分页审批模板数据")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page, @PathVariable Long limit){

        Page<ProcessTemplate> pageParam  = new Page<>(page,limit);
        //分页查询审批模板，把审批类型对应名称查询
        IPage<ProcessTemplate> pageModel = oaProcessTemplateService.selectPageProcessTemplate(pageParam);

        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取审批模板")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        ProcessTemplate processTemplate = oaProcessTemplateService.getById(id);
        return Result.ok(processTemplate);
    }

    @ApiOperation(value = "新增审批模板")
    @PostMapping("save")
    public Result save(@RequestBody ProcessTemplate processTemplate) {
        oaProcessTemplateService.save(processTemplate);
        return Result.ok();
    }

    @ApiOperation(value = "修改审批模板")
    @PutMapping("update")
    public Result updateById(@RequestBody ProcessTemplate processTemplate) {
        oaProcessTemplateService.updateById(processTemplate);
        return Result.ok();
    }

    @ApiOperation(value = "删除审批模板")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        oaProcessTemplateService.removeById(id);
        return Result.ok();
    }

}

