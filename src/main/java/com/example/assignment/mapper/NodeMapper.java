package com.example.assignment.mapper;

import com.example.assignment.dto.NodeDTO;
import com.example.assignment.entity.Node;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NodeMapper {

    public NodeDTO toDto(Node node) {
        return NodeDTO.builder()
                .id(node.getId())
                .type(node.getType())
                .status(node.getStatus())
                .build();
    }

    public List<NodeDTO> toDtoList(List<Node> nodeList) {
        return nodeList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}