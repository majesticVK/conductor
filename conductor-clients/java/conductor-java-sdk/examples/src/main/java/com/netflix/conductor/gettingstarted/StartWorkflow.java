package com.netflix.conductor.gettingstarted;

import com.netflix.conductor.client.http.ConductorClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;

public class StartWorkflow {

    public static void main(String[] args) {
        var client = new ConductorClient("http://localhost:8080/api");
        var workflowClient = new WorkflowClient(client);
        var workflowId = workflowClient.startWorkflow(new StartWorkflowRequest()
                .withName("hello_workflow")
                .withVersion(1));

        System.out.println("Started workflow " + workflowId);
    }
}
