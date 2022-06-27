package com.example.soapservice.endpoints;

import com.example.soapservice.services.UserService;
import com.example.soapservice.soap.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {
    private static final String NAMESPACE_URL = "http://www.example.com/soapservice/users";
    private final UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getUserByLoginRequest")
    @ResponsePayload
    public GetUserByLoginResponse getUserByLogin(@RequestPayload GetUserByLoginRequest request) {
        GetUserByLoginResponse response = new GetUserByLoginResponse();
        response.setUser(userService.getUserByLogin(request.getLogin()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();
        userService.getAllUsers().forEach(response.getUserswr()::add);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "deleteByLoginRequest")
    @ResponsePayload
    public void deleteByLogin(@RequestPayload DeleteByLoginRequest request) {
        userService.deleteUserByLogin(request.getLogin());
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "addNewUserRequest")
    @ResponsePayload
    public void addNewUser(@RequestPayload AddNewUserRequest request) {
        userService.saveUser(request.getUser());
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "updateRequest")
    @ResponsePayload
    public void updateUser(@RequestPayload UpdateRequest request) {
        userService.saveUser(request.getUser());
    }
}
