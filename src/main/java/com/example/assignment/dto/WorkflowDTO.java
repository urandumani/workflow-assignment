package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowDTO {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private List<NodeDTO> nodes;
    private List<WorkflowContentDTO> contents;
}
