package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NodeType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NodeStatus status;

    //defined here to maintain node order. could also use previousNode, nextNode like a linked list with pointers, but this is simpler
    @Column(name = "node_order")
    private Integer nodeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id", nullable = false)
    private Workflow workflow;
}


