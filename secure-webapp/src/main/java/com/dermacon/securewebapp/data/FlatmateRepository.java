package com.dermacon.securewebapp.data;

import org.springframework.data.repository.CrudRepository;

public interface FlatmateRepository extends CrudRepository<Flatmate, Long> {
    Flatmate findByLivingSpace(LivingSpace livingSpace);
    Flatmate findByFirstname(String firstname);
    Flatmate findByFirstnameAndSurname(String firstname, String surname);
    Flatmate findByUser(long id);
    void deleteByFlatmateId(Long id);
}
