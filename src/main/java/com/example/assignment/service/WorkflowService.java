package com.example.assignment.service;

import com.example.assignment.dto.WorkflowContentDTO;
import com.example.assignment.dto.WorkflowDTO;
import com.example.assignment.dto.WorkflowTriggerDTO;
import com.example.assignment.entity.Node;
import com.example.assignment.entity.NodeStatus;
import com.example.assignment.entity.NodeType;
import com.example.assignment.entity.Workflow;
import com.example.assignment.mapper.WorkflowContentMapper;
import com.example.assignment.mapper.WorkflowMapper;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.repository.WorkflowRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkflowService {

    private static final String ADMIN_ROLE = "Admin";
    private static final String ANALYTICS_ROLE = "Analytics";

    private final WorkflowRepository workflowRepository;
    private final WorkflowMapper workflowMapper;
    private final WorkflowContentMapper workflowContentMapper;
    private final Map<NodeType, NodeExecutor> nodeExecutors;

    public WorkflowService(WorkflowRepository workflowRepository, WorkflowMapper workflowMapper, WorkflowContentMapper workflowContentMapper, List<NodeExecutor> nodeExecutors) {
        this.workflowRepository = workflowRepository;
        this.workflowMapper = workflowMapper;
        this.workflowContentMapper = workflowContentMapper;
        this.nodeExecutors = nodeExecutors.stream()
                .collect(Collectors.toMap(NodeExecutor::getType, e -> e));
    }

    public List<WorkflowDTO> findAll() {
        Set<String> roles = getRolesFromSecurityContext();

        if (isAdminOrAnalytics(roles)) {
            return workflowMapper.toDtoList(workflowRepository.findAll());
        } else {
            return workflowMapper.toDtoList(workflowRepository.findDistinctByReadRoleNames(roles));
        }
    }

    public List<WorkflowContentDTO> getContentsByWorkflowId(String workflowId) {
        Set<String> roles = getRolesFromSecurityContext();

        if (isAdminOrAnalytics(roles)) {
            return workflowRepository.findById(workflowId)
                    .map(workflow -> workflowContentMapper.toDtoList(workflow.getContents()))
                    .orElseThrow(() -> new EntityNotFoundException("Workflow not found: " + workflowId));
        } else {
            return workflowRepository.findByIdAndReadRoleNamesWithContents(workflowId, roles)
                    .map(workflow -> workflowContentMapper.toDtoList(workflow.getContents()))
                    .orElseThrow(() -> new AccessDeniedException("Access denied or workflow not found"));
        }
    }

    public WorkflowDTO executeWorkflow(String workflowId, WorkflowTriggerDTO trigger) {
        if (isAdminOrAnalytics(getRolesFromSecurityContext())) {
            Workflow workflow = workflowRepository.findById(workflowId)
                    .orElseThrow(() -> new EntityNotFoundException("Workflow not found: " + workflowId));

            Workflow saved = workflowRepository.save(handleNodes(workflow, trigger));
            return workflowMapper.toDto(saved);
        } else {
            throw new AccessDeniedException("Access denied");
        }

    }

    private Workflow handleNodes(Workflow workflow, WorkflowTriggerDTO trigger) {
        List<Node> nodes = workflow.getNodes();

        //this validation should happen when inserting nodes instead of here
        long initCount = nodes.stream().filter(node -> node.getType() == NodeType.INIT).count();
        if (initCount != 1) {
            throw new IllegalStateException("Workflow must contain exactly one INIT node");
        }

        for (Node node : nodes) {
            if (node.getStatus() == NodeStatus.COMPLETED) continue;
            node.setStatus(NodeStatus.IN_PROGRESS);
            log.info("Node with id {} is in progress", node.getId());

            NodeExecutor executor = nodeExecutors.get(node.getType());
            executor.execute(node, trigger, workflow);

            if (node.getStatus() != NodeStatus.COMPLETED) break;

            log.info("Node with id {} has been completed", node.getId());
        }

        return workflow;
    }

    private Set<String> getRolesFromSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    private Boolean isAdminOrAnalytics(Set<String> roles) {
        return roles.stream()
                .anyMatch(role -> role.equalsIgnoreCase(ADMIN_ROLE) || role.equalsIgnoreCase(ANALYTICS_ROLE));
    }
}
