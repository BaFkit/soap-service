package com.example.soapservice.endpoints;

import com.example.soapservice.services.UserService;
import com.example.soapservice.soap.users.*;
import com.example.soapservice.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.com/soapservice/users";
    private final UserService userService;
    private final UserValidator userValidator;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByLoginRequest")
    @ResponsePayload
    public GetUserByLoginResponse getUserByLogin(@RequestPayload GetUserByLoginRequest request) {
        GetUserByLoginResponse response = new GetUserByLoginResponse();
        response.setUser(userService.getUserByLogin(request.getLogin()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();
        userService.getAllUsers().forEach(response.getUserswr()::add);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteByLoginRequest")
    @ResponsePayload
    public void deleteByLogin(@RequestPayload DeleteByLoginRequest request) {
        userService.deleteUserByLogin(request.getLogin());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addNewUserRequest")
    @ResponsePayload
    public AddNewUserResponse addNewUser(@RequestPayload AddNewUserRequest request) {
        AddNewUserResponse response = new AddNewUserResponse();
        userValidator.validate(request.getUser());
        userService.saveUser(request.getUser());
        response.setSuccess(true);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRequest")
    @ResponsePayload
    public UpdateResponse updateUser(@RequestPayload UpdateRequest request) {
        UpdateResponse response = new UpdateResponse();
        userValidator.validate(request.getUser());
        userService.updateUser(request.getUser());
        response.setSuccess(true);
        return response;
    }
}
