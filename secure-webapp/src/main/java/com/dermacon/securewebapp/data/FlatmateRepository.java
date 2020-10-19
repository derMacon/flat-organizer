package com.dermacon.securewebapp.data;

import org.springframework.data.repository.CrudRepository;

public interface FlatmateRepository extends CrudRepository<Flatmate, Long> {
//    Flatmate findByUser(long id);
    Flatmate findByUser_UserId(long id);
    void deleteByFlatmateId(Long id);
}
