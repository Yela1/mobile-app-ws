package com.mcloud.app.ws.ui.controller;

import com.mcloud.app.ws.ui.exception.UserServiceException;
import com.mcloud.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.mcloud.app.ws.ui.model.request.UserDetailsRequestModel;
import com.mcloud.app.ws.ui.model.response.UserRest;
import com.mcloud.app.ws.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    Map<String, UserRest> users;
    @GetMapping()
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort){
        return "get user" + page + limit + sort ;
    }

    @GetMapping(path = "/{userId}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        if(true) throw new UserServiceException("My Exception");
        if (users.containsKey(userId)){
            return new ResponseEntity< >(users.get(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel){


        UserRest returnValue = userService.createUser(userDetailsRequestModel);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK );
    }

    @PutMapping(
            path = "/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId,
                                               @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel){

            UserRest userRest = users.get(userId);
            userRest.setLastName(updateUserDetailsRequestModel.getLastName());
            userRest.setFirstName(updateUserDetailsRequestModel.getFirstName());
            users.put(userId,userRest);
            return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);


    }

    @DeleteMapping(path = ("/{userId}"))
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        users.remove(userId);
        return ResponseEntity.noContent().build();

    }
}
