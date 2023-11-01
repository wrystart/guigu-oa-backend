package com.atguigu.auth.activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description
 * @Author wrystart
 * @Version 1.0
 * @Date 2023/10/31 16:53
 */
@SpringBootTest
public class ProcessTest {

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

    //单个流程实例挂起
    @Test
    public void SingleSuspendProcessInstance() {
        String processInstanceId = "a4cb8e5c-77df-11ee-86bb-00be4397d06e";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        boolean isSuspended = processInstance.isSuspended();
        if (isSuspended) {
            //激活
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println(processInstanceId + "激活了");
        } else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println(processInstanceId + "挂起");
        }

    }

    //全部流程实例挂起
    @Test
    public void suspendProcessInstanceAll() {
        //1 获取流程定义的对象
        ProcessDefinition qingjia = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("qingjia").singleResult();

        //2 调用流程定义对象的方法判断当前状态：挂起 激活
        boolean isSuspended = qingjia.isSuspended();

        //3 判断如果挂起，实现激活
        if (isSuspended) {
            //第一个参数 流程定义id
            //第二个参数 是否激活 true
            //第三个参数 时间点
            repositoryService.activateProcessDefinitionById(qingjia.getId(), true, null);
            System.out.println(qingjia.getId() + "激活了");
        } else {
            //如果激活，实现挂起
            repositoryService.suspendProcessDefinitionById(qingjia.getId(), true, null);
            System.out.println(qingjia.getId() + "挂起");
        }


    }

    //创建流程实例，指定BusinessKey
    @Test
    public void startUpProcessAddBusinessKey() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia", "1001");

        System.out.println(processInstance.getBusinessKey());
        System.out.println(processInstance.getId());

    }

    //查询已经处理的任务
    @Test
    public void findCompleteTaskList() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee("zhangsan")
                .finished().list();

        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("流程实例id：" + historicTaskInstance.getProcessInstanceId());
            System.out.println("任务id：" + historicTaskInstance.getId());
            System.out.println("任务负责人：" + historicTaskInstance.getAssignee());
            System.out.println("任务名称：" + historicTaskInstance.getName());
        }

    }

    //处理当前任务
    @Test
    public void completeTask() {
        //查询负责人需要处理的任务，返回一条
        Task task = taskService.createTaskQuery()
                .taskAssignee("zhangsan")
                .singleResult();

        //完成任务，参数是任务id
        taskService.complete(task.getId());

    }

    //查询个人的代办任务--zhangsan
    @Test
    public void findTaskList() {

        String assign = "zhangsan";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assign).list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

    }


    //启动流程实例
    @Test
    public void startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia");

        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getProcessInstanceId());
        System.out.println("当前活动id：" + processInstance.getActivityId());

    }

    //单个文件部署
    @Test
    public void deployProcess() {
        //流程部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/qingjia.bpmn20.xml")
                .addClasspathResource("process/qingjia.png")
                .name("请假申请流程")
                .deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());


    }

}
