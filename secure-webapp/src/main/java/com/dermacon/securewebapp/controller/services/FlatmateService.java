package com.dermacon.securewebapp.controller.services;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.LivingSpace;
import com.dermacon.securewebapp.data.LivingSpaceRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import com.dermacon.securewebapp.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class FlatmateService {

    @Autowired
    private FlatmateRepository flatmateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LivingSpaceRepository livingSpaceRepository;

    /**
     * first remove user and then the entity itself
     * @param flatmate flatmate entity that will be removed
     */
    public void saveDeleteFlatmate(Flatmate flatmate) {
        User user = flatmate.getUser();
        LoggerSingleton.getInstance().info("delete user: " + user);
        userRepository.delete(user);
        flatmate.setUser(null);
        flatmate.setLivingSpace(null);
        flatmateRepository.delete(flatmate);
    }

    public Iterable<Flatmate> getAllFlatmates() {
        return flatmateRepository.findAll();
    }

    public Set<LivingSpace> findEmptyLivingSpaces() {
        return StreamSupport.stream(livingSpaceRepository.findAll().spliterator(), false)
                .filter(e -> flatmateRepository.findByLivingSpace(e) == null)
                .collect(Collectors.toSet());
    }

}
