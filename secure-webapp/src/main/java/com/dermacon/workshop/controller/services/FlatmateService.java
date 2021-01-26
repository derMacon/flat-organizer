package com.dermacon.workshop.controller.services;

import com.dermacon.workshop.data.AppUser;
import com.dermacon.workshop.data.AppUserRepository;
import com.dermacon.workshop.data.Flatmate;
import com.dermacon.workshop.data.FlatmateRepository;
import com.dermacon.workshop.data.LivingSpace;
import com.dermacon.workshop.data.LivingSpaceRepository;
import com.dermacon.workshop.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for all actions regarding the flatmate data
 */
@Service
@Transactional
public class FlatmateService {

    @Autowired
    private FlatmateRepository flatmateRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private LivingSpaceRepository livingSpaceRepository;

    /**
     * First remove user and then the entity itself
     * @param flatmate flatmate entity that will be removed
     */
    public void saveDeleteFlatmate(Flatmate flatmate) {
        AppUser user = flatmate.getUser();
        LoggerSingleton.getInstance().info("delete user: " + user);
        userRepository.delete(user);
        flatmate.setUser(null);
        flatmate.setLivingSpace(null);
        flatmateRepository.delete(flatmate);
    }

    /**
     * Returns all persisted flatmate entities
     * @return all persisted flatmate entities
     */
    public Iterable<Flatmate> getAllFlatmates() {
        return flatmateRepository.findAll();
    }

    /**
     * Get all living space entities in which no flatmate is currenty living
     * @return all living space entities in which no flatmate is currenty living
     */
    public Set<LivingSpace> findEmptyLivingSpaces() {
        return StreamSupport.stream(livingSpaceRepository.findAll().spliterator(), false)
                .filter(e -> flatmateRepository.findByLivingSpace(e) == null)
                .collect(Collectors.toSet());
    }

}
