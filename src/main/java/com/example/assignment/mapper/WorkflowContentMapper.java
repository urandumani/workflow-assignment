package com.example.assignment.mapper;

import com.example.assignment.dto.WorkflowContentDTO;
import com.example.assignment.entity.WorkflowContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowContentMapper {

    public WorkflowContentDTO toDto(WorkflowContent content) {
        return WorkflowContentDTO.builder()
                .id(content.getId())
                .type(content.getType())
                .priority(content.getPriority())
                .title(content.getTitle())
                .assigneeId(content.getAssigneeId())
                .reporterId(content.getReporterId())
                .contentOrder(content.getContentOrder())
                .time(content.getTime())
                .url(content.getUrl())
                .text(content.getText())
                .build();
    }

    public List<WorkflowContentDTO> toDtoList(List<WorkflowContent> workflowContentList) {
        return workflowContentList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
