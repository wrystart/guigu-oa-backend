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
public class ProcessTestDemo3 {

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

    //启动流程实例
    @Test
    public void startProcessInstance(){

        Map<String,Object> map = new HashMap<>();
        //设置任务人
//        map.put("assignee1","lucy03");
//        map.put("assignee2","mary02");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("jiaban04");

        System.out.println(processInstance.getProcessDefinitionId());
        System.out.println(processInstance.getId());

    }

    //1 部署流程定义和启动流程实例
    @Test
    public void deployProcess(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("process/jiaban04.bpmn20.xml")
                .name("加班申请流程04")
                .deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    //2 查询组任务
    @Test
    public void findGroupTaskList(){
        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser("tom01")
                .list();

        for (Task task : list){
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    //3 拾取组任务
    @Test
    public void claimTask(){
        //拾取任务,即使该用户不是候选人也能拾取(建议拾取时校验是否有资格)
        //校验该用户有没有拾取任务的资格
        Task task = taskService.createTaskQuery()
                .taskCandidateUser("tom02")//根据候选人查询
                .singleResult();
        if(task!=null){
            //拾取任务
            taskService.claim(task.getId(), "tom02");
            System.out.println("任务拾取成功");
        }
    }

    //4 查询个人的代办任务--tom02
    @Test
    public void findTaskList() {

        String assign = "tom02";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assign).list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    //5 办理个人任务
    @Test
    public void completeTask() {
        Task task = taskService.createTaskQuery()
                .taskAssignee("tom02")  //要查询的负责人
                .singleResult();//返回一条

        //完成任务,参数：任务id
        taskService.complete(task.getId());
    }




}
