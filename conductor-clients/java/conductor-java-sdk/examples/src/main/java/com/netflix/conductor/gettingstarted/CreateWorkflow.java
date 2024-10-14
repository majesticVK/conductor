package com.netflix.conductor.gettingstarted;

import com.netflix.conductor.sdk.workflow.def.WorkflowBuilder;
import com.netflix.conductor.sdk.workflow.def.tasks.SimpleTask;
import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;

/**
 * Very Simple Workflow As Code Example.
 *
 * You can use the following code to register this "hello_workflow" workflow.
 * 
 * {
 *   "name": "hello_workflow",
 *   "description": "Hello Workflow!",
 *   "version": 1,
 *   "tasks": [
 *     {
 *       "name": "hello_task",
 *       "taskReferenceName": "hello_task_ref",
 *       "type": "SIMPLE",
 *       "inputParameters": {}
 *     }
 *   ],
 *   "inputParameters": [],
 *   "outputParameters": {
 *
 *   },
 *   "schemaVersion": 2,
 *   "restartable": true,
 *   "workflowStatusListenerEnabled": false,
 *   "ownerEmail": "example@orkes.io",
 *   "timeoutPolicy": "ALERT_ONLY",
 *   "timeoutSeconds": 0
 * }
 */
public class CreateWorkflow {

    public static void main(String[] args) {
        var executor = new WorkflowExecutor("http://localhost:8080/api");
        var workflow = new WorkflowBuilder<Void>(executor)
                .name("hello_workflow")
                .version(1)
                .description("Hello Workflow!")
                .ownerEmail("examples@orkes.io")
                .add(new SimpleTask("hello_task", "hello_task_ref"))
                .build();
        workflow.registerWorkflow(true, true);
        executor.shutdown();
    }
}
