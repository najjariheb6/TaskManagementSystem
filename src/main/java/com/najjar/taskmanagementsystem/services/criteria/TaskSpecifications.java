package com.najjar.taskmanagementsystem.services.criteria;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.model.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    public static Specification<Task> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), TaskStatus.valueOf(status));
    }

    public static Specification<Task> isAssignedTo(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("assignedUserId"), userId);
    }

    public static Specification<Task> hasPriority(Integer priority) {
        return (root, query, criteriaBuilder) ->
                priority != null ? criteriaBuilder.equal(root.get("priority"), priority) : null;
    }

    // Add more static methods for other filtering conditions

}

