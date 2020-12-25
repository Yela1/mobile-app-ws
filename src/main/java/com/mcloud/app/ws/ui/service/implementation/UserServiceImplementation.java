package com.mcloud.app.ws.ui.service.implementation;

import com.mcloud.app.ws.ui.model.request.UserDetailsRequestModel;
import com.mcloud.app.ws.ui.model.response.UserRest;
import com.mcloud.app.ws.ui.service.UserService;
import com.mcloud.app.ws.ui.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    Utils util;


    public UserServiceImplementation(){

    }
    @Autowired
    public UserServiceImplementation(Utils util) {
        this.util = util;
    }
    Map<String, UserRest> users;
    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
        UserRest returnValue  = new UserRest();
        returnValue.setEmail(userDetailsRequestModel.getEmail());
        returnValue.setFirstName(userDetailsRequestModel.getFirstName());
        returnValue.setLastName(userDetailsRequestModel.getLastName());

        String userId = util.generateUserId();
        returnValue.setUserId(userId);
        if (users == null) users = new HashMap<>();
        users.put(userId, returnValue);
        return returnValue;
    }
}
