package com.atguigu.process.controller;


import com.atguigu.common.result.Result;
import com.atguigu.process.service.OaProcessService;
import com.atguigu.process.service.OaProcessTypeService;
import com.atguigu.vo.process.ProcessQueryVo;
import com.atguigu.vo.process.ProcessVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 审批管理
 * @Author Ruiyang Wang
 * @Version 1.0
 * @Date 2023/11/1 20:15
 */
@Api(tags = "审批管理")
@RestController
@RequestMapping(value = "/admin/process")
public class OaProcessController {

    @Autowired
    private OaProcessService oaProcessService;

    //审批列表-条件分页查询
    @ApiOperation(value = "条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page, @PathVariable Long limit,ProcessQueryVo processQueryVo) {
        Page<ProcessVo> pageParam = new Page<>(page, limit);
        IPage<ProcessVo> pageModel = oaProcessService.selectPage(pageParam, processQueryVo);
        return Result.ok(pageModel);
    }

}

