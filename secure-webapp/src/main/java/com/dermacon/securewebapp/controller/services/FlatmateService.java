package com.dermacon.securewebapp.controller.services;

import com.dermacon.securewebapp.data.Flatmate;
import com.dermacon.securewebapp.data.FlatmateRepository;
import com.dermacon.securewebapp.data.User;
import com.dermacon.securewebapp.data.UserRepository;
import com.dermacon.securewebapp.logger.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FlatmateService {

    @Autowired
    private FlatmateRepository flatmateRepository;

    @Autowired
    private UserRepository userRepository;

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
}
