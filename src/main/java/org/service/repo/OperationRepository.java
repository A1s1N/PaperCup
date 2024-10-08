package org.service.repo;

import org.service.models.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    Iterable <Operation> findByOrderliness(String orderliness);
    Iterable <Operation> findByProductId(long productId);
}
