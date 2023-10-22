package com.atguigu.auth.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 菜单工具类
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/10/22 15:20
 */
public class MenuHelper {


   /**
    * 使用递归方法建菜单
    * @param sysMenuList
    * @return List<SysMenu>
    */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建list集合，用于最终数据
        List<SysMenu> treeList = new ArrayList<>();
        //把所有菜单数据进行遍历
        for (SysMenu sysMenu : sysMenuList) {
            //递归入口进入
            //parentId == 0 是入口
            if (sysMenu.getParentId().longValue() == 0) {
                treeList.add(getChildren(sysMenu,sysMenuList));
            }
        }
        return treeList;

    }


    /**
     * 递归查找子节点
     * @param sysMenu
     * @param sysMenuList
     * @return SysMenu
     */
    private static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断 id 和 parentId的对应关系
        for (SysMenu menu : sysMenuList){
           if (sysMenu.getId().longValue() == menu.getParentId().longValue()){
               if (sysMenu.getChildren() == null) {
                   sysMenu.setChildren(new ArrayList<>());
               }
               sysMenu.getChildren().add(getChildren(menu,sysMenuList));
           }
        }
        return sysMenu;
    }

}
