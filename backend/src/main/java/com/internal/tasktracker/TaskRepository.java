package com.internal.tasktracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Search tasks by term and optional status filter
    @Query(value = """
                SELECT * FROM tasks 
                WHERE archived = FALSE
                AND (
                :term IS NULL
                OR LOWER(title) LIKE :term 
                OR LOWER(description) LIKE :term 
                )
                AND (:status IS NULL OR status = :status)
                ORDER BY created_at DESC
                LIMIT :limit OFFSET :offset
                """,nativeQuery = true)
    List<Task> searchTasks(
     @Param("term") String term,
     @Param("status") String status,
     @Param("limit") int limit,
     @Param("offset") int offset);

@Query(value = """
    SELECT COUNT(*) FROM tasks
    WHERE archived = FALSE
      AND (
          :term IS NULL
          OR LOWER(title) LIKE :term
          OR LOWER(description) LIKE :term
      )
      AND (:status IS NULL OR status = :status)
    """, nativeQuery = true)
long countSearchTasks(@Param("term") String term,
                      @Param("status") String status);

}
        
