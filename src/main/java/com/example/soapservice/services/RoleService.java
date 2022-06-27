package com.example.soapservice.services;

import com.example.soapservice.entities.RoleEntity;
import com.example.soapservice.exceptions.ResourceNotFoundException;
import com.example.soapservice.repositories.RoleRepository;
import com.example.soapservice.soap.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public final static Function<RoleEntity, Role> functionEntityToSoap = re -> {
        Role r = new Role();
        r.setId(re.getId());
        r.setName(re.getName());
        return r;
    };

    public final static Function<Role, RoleEntity> functionSoapToEntity = r -> {
        RoleEntity re = new RoleEntity();
        re.setId(r.getId());
        re.setName(r.getName());
        return re;
    };

    public RoleEntity getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found, name: " + name));
    }
}
