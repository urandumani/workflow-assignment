package com.example.assignment.dto;

import com.example.assignment.entity.WorkflowContentType;
import com.example.assignment.entity.WorkflowTaskPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowContentDTO {
    private String id;
    private WorkflowContentType type;
    private String title;
    private String assigneeId;
    private String reporterId;
    private Integer contentOrder;
    private Instant time;
    private WorkflowTaskPriority priority;
    private String url;
    private String text;
}
