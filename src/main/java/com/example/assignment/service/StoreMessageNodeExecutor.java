package com.example.assignment.service;

import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.entity.Node;
import com.example.assignment.entity.NodeStatus;
import com.example.assignment.entity.NodeType;
import com.example.assignment.entity.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoreMessageNodeExecutor implements NodeExecutor {

    @Override
    public NodeType getType() {
        return NodeType.STORE_MESSAGE;
    }

    @Override
    public void execute(Node node, WorkflowTriggerDTO trigger, Workflow workflow) {
        log.info("Storing message for {}: {}", trigger.getUsername(), trigger.getMessage());
        node.setStatus(NodeStatus.COMPLETED);
    }
}
