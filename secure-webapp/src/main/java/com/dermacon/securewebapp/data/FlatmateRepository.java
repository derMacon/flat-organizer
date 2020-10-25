package com.dermacon.securewebapp.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FlatmateRepository extends CrudRepository<Flatmate, Long> {
    Flatmate findByUser(long id);
    void deleteByFlatmateId(Long id);
//    Set<Task> findByTasks_taskId(Long id);
}