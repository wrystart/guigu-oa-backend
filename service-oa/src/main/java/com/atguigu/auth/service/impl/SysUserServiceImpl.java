package com.atguigu.auth.service.impl;

import com.atguigu.model.system.SysUser;
import com.atguigu.auth.mapper.SysUserMapper;
import com.atguigu.auth.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ruiyang Wang
 * @since 2023-10-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    //用户状态更新
    @Override
    public void updateStatus(Long id, Integer status) {
        //根据userid查询用户对象
        SysUser sysUser = baseMapper.selectById(id);

        //设置修改状态值
        sysUser.setStatus(status);

        //调用方法进行修改
        baseMapper.updateById(sysUser);


    }
}
