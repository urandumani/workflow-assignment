package com.example.assignment.service;

import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.entity.Node;
import com.example.assignment.entity.NodeType;
import com.example.assignment.entity.Workflow;

public interface NodeExecutor {

    NodeType getType();
    void execute(Node node, WorkflowTriggerDTO trigger, Workflow workflow);
}
