package com.atguigu.auth.service;

import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ruiyang Wang
 * @since 2023-10-21
 */
public interface SysUserService extends IService<SysUser> {

    //用户状态更新
    void updateStatus(Long id, Integer status);

    //根据用户名进行查询
    SysUser getByUsername(String username);
}
