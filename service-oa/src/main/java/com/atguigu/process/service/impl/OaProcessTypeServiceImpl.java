package com.atguigu.process.service.impl;

import com.atguigu.model.process.ProcessTemplate;
import com.atguigu.model.process.ProcessType;
import com.atguigu.process.mapper.OaProcessTypeMapper;
import com.atguigu.process.service.OaProcessTemplateService;
import com.atguigu.process.service.OaProcessTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 审批类型 服务实现类
 * </p>
 *
 * @author Ruiyang Wang
 * @since 2023-11-01
 */
@Service
public class OaProcessTypeServiceImpl extends ServiceImpl<OaProcessTypeMapper, ProcessType> implements OaProcessTypeService {

    @Autowired
    private OaProcessTemplateService oaProcessTemplateService;

    //获取所有审批类型及模板
    @Override
    public List<ProcessType> findProcessType() {
        //1 查询所有的审批类型，返回list集合
        List<ProcessType> processTypeList = baseMapper.selectList(null);

        //2 遍历返回所有审批类型list集合
        for (ProcessType processType : processTypeList) {
            //3 得到每个审批类型
            Long processTypeId = processType.getId();
            //根据审批类型id查询对应审批模板
            LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessTemplate::getProcessTypeId, processTypeId);
            List<ProcessTemplate> processTemplateList = oaProcessTemplateService.list(wrapper);

            //4 根据审批分类id查询对应审批模板数据（List）封装到每个审批类型对象里面
            processType.setProcessTemplateList(processTemplateList);
        }
        return processTypeList;
    }
}
