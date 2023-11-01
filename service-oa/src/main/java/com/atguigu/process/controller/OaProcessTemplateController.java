package com.atguigu.process.controller;


import com.atguigu.common.result.Result;
import com.atguigu.model.process.ProcessTemplate;
import com.atguigu.process.service.OaProcessTemplateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation(value = "上传流程定义")
    @PostMapping("/uploadProcessDefinition")
    public Result uploadProcessDefinition(MultipartFile file) throws FileNotFoundException {
        //获取classes目录位置
        String path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();

        // 设置上传目录
        //当 Java 进行文件操作时，它会对路径中的特殊字符（比如空格）做URL编码
        //由于文件目录中有空格，使用URLDecoder.decode进行解码
        File tempFile = new File(URLDecoder.decode(path + "/processes/"));
        // 判断目录是否存着
        if (!tempFile.exists()) {
            tempFile.mkdirs();//创建目录
        }

        // 创建空文件用于写入文件
        String fileName = file.getOriginalFilename();
        File zipFile = new File(URLDecoder.decode(path + "/processes/" + fileName));

        // 保存文件流到本地
        try {
            file.transferTo(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败");
        }

        Map<String, Object> map = new HashMap<>();
        //根据上传地址后续部署流程定义，文件名称为流程定义的默认key
        map.put("processDefinitionPath", "processes/" + fileName);
        map.put("processDefinitionKey", fileName.substring(0, fileName.lastIndexOf(".")));
        return Result.ok(map);
    }

    //部署流程定义(发布)
    @ApiOperation(value = "发布")
    @GetMapping("/publish/{id}")
    public Result publish(@PathVariable Long id) {
        //修改模板发布状态 1 已经发布
        //流程定义部署
        oaProcessTemplateService.publish(id);
        return Result.ok();
    }

}

