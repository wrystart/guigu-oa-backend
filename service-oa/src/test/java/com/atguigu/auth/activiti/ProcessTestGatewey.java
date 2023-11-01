package com.atguigu.auth.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/11/01 8:53
 */
@SpringBootTest
public class ProcessTestGatewey {

    //注入 RepositoryService 流程定义
    @Autowired
    private RepositoryService repositoryService;

    //注入 RuntimeService 流程实例
    @Autowired
    private RuntimeService runtimeService;

    //注入 TaskService 任务
    @Autowired
    private TaskService taskService;

    //注入 HistoryService 历史
    @Autowired
    private HistoryService historyService;


    //1 部署流程定义
    @Test
    public void deployProcess(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("process/qingjia003.bpmn20.xml")
                .name("请假申请流程003")
                .deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    //2 启动流程实例
    @Test
    public void startProcessInstance(){

        Map<String,Object> map = new HashMap<>();
        //设置请假天数
        map.put("day","3");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia003");

        System.out.println(processInstance.getProcessDefinitionId());
        System.out.println(processInstance.getId());

    }

    //3 查询个人的代办任务--zhao6
    @Test
    public void findTaskList() {

//        String assign = "wang5";
//        String assign = "gouwa";
        String assign = "xiaoli";

        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assign).list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

    }

    //4 完成任务
    @Test
    public void completeTask() {
        Task task = taskService.createTaskQuery()
                .taskAssignee("gouwa")  //要查询的负责人
//                .taskAssignee("gouwa")
                .singleResult();//返回一条

        //完成任务,参数：任务id
        taskService.complete(task.getId());
    }



}
