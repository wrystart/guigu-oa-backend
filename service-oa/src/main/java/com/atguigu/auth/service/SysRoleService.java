package com.atguigu.auth.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.AssignRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description
 * @Author wrystart
 * @Version
 * @Date 2023/10/18 14:25
 */
public interface SysRoleService extends IService<SysRole> {

    //1 查询所有角色 和 当前用户所属角色
    Map<String, Object> findRoleDataByUserId(String userId);

    //2 为用户分配角色
    void doAssign(AssignRoleVo assignRoleVo);
}
