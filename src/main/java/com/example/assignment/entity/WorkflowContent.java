package com.example.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "workflow_contents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Instead of using inheritance and splitting content entities based on type, I've opted for this simpler solution
public class WorkflowContent {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id", nullable = false)
    private Workflow workflow;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkflowContentType type;

    // task
    @Enumerated(EnumType.STRING)
    private WorkflowTaskPriority priority;

    private String title;
    private String assigneeId;
    private String reporterId;
    private Integer contentOrder;
    private Instant time;

    // attachment
    private String url;

    // note
    private String text;
}
