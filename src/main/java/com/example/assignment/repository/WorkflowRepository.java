package com.example.assignment.repository;

import com.example.assignment.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WorkflowRepository extends JpaRepository<Workflow, String> {

    @Query("""
                SELECT DISTINCT w FROM Workflow w
                JOIN w.readRoles r
                WHERE r.name IN :roleNames
            """)
    List<Workflow> findDistinctByReadRoleNames(@Param("roleNames") Set<String> roleNames);

    @Query("""
                SELECT DISTINCT w FROM Workflow w
                LEFT JOIN FETCH w.contents c
                JOIN w.readRoles r
                WHERE w.id = :workflowId AND r.name IN :roleNames
            """)
    Optional<Workflow> findByIdAndReadRoleNamesWithContents(@Param("workflowId") String workflowId,
                                                            @Param("roleNames") Set<String> roleNames);
}
