package com.atguigu.auth.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @Description
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/11/01 10:08
 */
public class MyTaskListener implements TaskListener {


    @Override
    public void notify(DelegateTask task) {
        if (task.getName().equals("经理审批")){
            //分配任务
            task.setAssignee("jack");
        }else if (task.getName().equals("人事审批")){
            task.setAssignee("tom");
        }




    }
}
