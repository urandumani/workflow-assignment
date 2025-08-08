package com.example.assignment.mapper;

import com.example.assignment.dto.WorkflowDTO;
import com.example.assignment.entity.Workflow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowMapper {

    private final NodeMapper nodeMapper;
    private final WorkflowContentMapper contentMapper;

    public WorkflowMapper(NodeMapper nodeMapper, WorkflowContentMapper contentMapper) {
        this.nodeMapper = nodeMapper;
        this.contentMapper = contentMapper;
    }

    public WorkflowDTO toDto(Workflow workflow) {
        return WorkflowDTO.builder()
                .id(workflow.getId())
                .name(workflow.getName())
                .description(workflow.getDescription())
                .createdAt(workflow.getCreatedAt())
                .nodes(nodeMapper.toDtoList(workflow.getNodes()))
                .contents(contentMapper.toDtoList(workflow.getContents()))
                .build();
    }

    public List<WorkflowDTO> toDtoList(List<Workflow> workflows) {
        return workflows.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
