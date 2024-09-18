package org.service.repo;

import org.service.models.Day;
import org.springframework.data.repository.CrudRepository;

public interface DayRepository extends CrudRepository<Day, Long> {
    Day findByMonth(String month);
}
