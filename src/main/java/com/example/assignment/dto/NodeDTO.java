package com.example.assignment.dto;

import com.example.assignment.entity.NodeStatus;
import com.example.assignment.entity.NodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeDTO {
    private String id;
    private NodeType type;
    private NodeStatus status;
}
