package com.atguigu.process.service;

import com.atguigu.model.process.ProcessType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 审批类型 服务类
 * </p>
 *
 * @author Ruiyang Wang
 * @since 2023-11-01
 */
public interface OaProcessTypeService extends IService<ProcessType> {

    //获取所有审批类型及模板
    List<ProcessType> findProcessType();
}
