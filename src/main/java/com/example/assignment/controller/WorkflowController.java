package com.example.assignment.controller;

import com.example.assignment.dto.WorkflowContentDTO;
import com.example.assignment.dto.WorkflowDTO;
import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.service.WorkflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping
    public ResponseEntity<List<WorkflowDTO>> getAllWorkflows() {
        List<WorkflowDTO> workflows = workflowService.findAll();
        return ResponseEntity.ok(workflows);
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<List<WorkflowContentDTO>> getContentsByWorkflowId(@PathVariable String id) {
        List<WorkflowContentDTO> contents = workflowService.getContentsByWorkflowId(id);
        return ResponseEntity.ok(contents);
    }

    @PostMapping("/{id}/execute")
    public ResponseEntity<WorkflowDTO> executeWorkflow(
            @PathVariable String id,
            @RequestBody WorkflowTriggerDTO trigger) {

        WorkflowDTO updatedWorkflow = workflowService.executeWorkflow(id, trigger);
        return ResponseEntity.ok(updatedWorkflow);
    }
}
