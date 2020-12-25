package com.mcloud.app.ws.ui.service;

import com.mcloud.app.ws.ui.model.request.UserDetailsRequestModel;
import com.mcloud.app.ws.ui.model.response.UserRest;
import org.springframework.stereotype.Service;


public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
