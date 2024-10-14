package com.netflix.conductor.gettingstarted;

import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.ConductorClient;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.List;

public class HelloWorker implements Worker {

    @Override
    public TaskResult execute(Task task) {
        var taskResult = new TaskResult(task);
        taskResult.setStatus(TaskResult.Status.COMPLETED);
        taskResult.getOutputData().put("message", "Hello World!");
        return taskResult;
    }

    @Override
    public String getTaskDefName() {
        return "hello_task";
    }

    public static void main(String[] args) {
        var client = new ConductorClient("http://localhost:8080/api");
        var taskClient = new TaskClient(client);
        var runnerConfigurer = new TaskRunnerConfigurer
                .Builder(taskClient, List.of(new HelloWorker()))
                .withThreadCount(10)
                .build();
        runnerConfigurer.init();
    }
}
