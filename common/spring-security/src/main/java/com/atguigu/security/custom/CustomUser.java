package com.atguigu.security.custom;

import com.atguigu.model.system.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Description 用户对象
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/10/30 11:05
 */
public class CustomUser extends User {
    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。
     */
    private SysUser sysUser;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

}
