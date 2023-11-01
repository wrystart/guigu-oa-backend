package com.atguigu.auth.activiti;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/11/01 9:41
 */
@Component
public class UserBean {

    public String getUsername(int id){
        if (id == 1){
            return "lilei";
        }
        if (id ==2){
            return "hanmeimei";
        }
        return "admin";

    }
}
