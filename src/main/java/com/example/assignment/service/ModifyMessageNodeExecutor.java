package com.example.assignment.service;

import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.entity.Node;
import com.example.assignment.entity.NodeStatus;
import com.example.assignment.entity.NodeType;
import com.example.assignment.entity.Workflow;
import org.springframework.stereotype.Component;

@Component
public class ModifyMessageNodeExecutor implements NodeExecutor {

    @Override
    public NodeType getType() {
        return NodeType.MODIFY_MESSAGE;
    }

    @Override
    public void execute(Node node, WorkflowTriggerDTO trigger, Workflow workflow) {
        String updatedMessage = "Hello " + (trigger.getMessage() != null ? trigger.getMessage() : "");
        trigger.setMessage(updatedMessage);
        node.setStatus(NodeStatus.COMPLETED);
    }
}
