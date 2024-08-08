package org.service.repo;

import org.service.models.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepository extends CrudRepository<Timetable, Long> {
}
