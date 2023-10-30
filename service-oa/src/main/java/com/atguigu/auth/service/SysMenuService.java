package com.atguigu.auth.service;

import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.system.AssignMenuVo;
import com.atguigu.vo.system.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Ruiyang Wang
 * @since 2023-10-22
 */
public interface SysMenuService extends IService<SysMenu> {

    //菜单列表
    List<SysMenu> findNodes();

    //删除菜单
    void removeMenuById(Long id);

    //查询所有菜单和角色分配的菜单
    List<SysMenu> findMenuByRoleId(Long roleId);

    //角色分配菜单
    void doAssign(AssignMenuVo assignMenuVo);

    //根据用户id获取用户可以操作菜单列表
    List<RouterVo> findUserMenuListByUserid(Long userId);

    //根据用户id获取用户可以操作按钮列表
    List<String> findUserPermsByUserid(Long userId);
}
