package com.atguigu.process.controller.api;

import com.atguigu.common.result.Result;
import com.atguigu.model.process.ProcessTemplate;
import com.atguigu.model.process.ProcessType;
import com.atguigu.process.service.OaProcessTemplateService;
import com.atguigu.process.service.OaProcessTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 员工端审批管理
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/11/02 10:21
 */
@Api(tags = "员工端审批管理")
@RestController
@RequestMapping(value="/admin/process")
@CrossOrigin //跨域
public class ProcessApiController {

    @Autowired
    private OaProcessTypeService oaProcessTypeService;

    @Autowired
    private OaProcessTemplateService oaProcessTemplateService;

    @ApiOperation(value = "获取审批模板")
    @GetMapping("getProcessTemplate/{processTemplateId}")
    public Result get(@PathVariable Long processTemplateId) {
        ProcessTemplate processTemplate = oaProcessTemplateService.getById(processTemplateId);
        return Result.ok(processTemplate);
    }

    @ApiOperation(value = "获取所有审批类型及对应审批模板")
    @GetMapping("findProcessType")
    public Result findProcessType() {
        List<ProcessType> list = oaProcessTypeService.findProcessType();
        return Result.ok(list);
    }

}
