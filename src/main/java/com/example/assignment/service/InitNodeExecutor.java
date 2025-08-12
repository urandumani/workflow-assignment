package com.example.assignment.service;

import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.entity.Node;
import com.example.assignment.entity.NodeStatus;
import com.example.assignment.entity.NodeType;
import com.example.assignment.entity.Workflow;
import org.springframework.stereotype.Component;

@Component
public class InitNodeExecutor implements NodeExecutor {

    @Override
    public NodeType getType() {
        return NodeType.INIT;
    }

    @Override
    public void execute(Node node, WorkflowTriggerDTO trigger, Workflow workflow) {
        node.setStatus(NodeStatus.COMPLETED);
    }
}