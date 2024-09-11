package com.upc.aventurape.platform.users.aplication.internal.commandservices;

import com.upc.aventurape.platform.users.aplication.internal.outboundservices.hashing.HashingService;
import com.upc.aventurape.platform.users.domain.model.aggregates.User;
import com.upc.aventurape.platform.users.domain.model.commands.SignInCommand;
import com.upc.aventurape.platform.users.domain.model.commands.SignUpCommand;
import com.upc.aventurape.platform.users.domain.model.valueobjects.Roles;
import com.upc.aventurape.platform.users.domain.services.UserCommandService;
import com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository.RoleRepository;
import com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command){
        if(userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username is already in use");
        var roles = command.roles();
        if(roles.isEmpty()){
            var role = roleRepository.findByName(Roles.ROLE_ADVENTUROUS);
            roles.add(role.get());
        }
        roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName()).
                        orElseThrow(()->new RuntimeException("Role not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()));
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command){
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty()) throw new RuntimeException("User not found");
        if(!hashingService.matches(command.password(),user.get().getPassword())){
            throw new RuntimeException("Invalid password");
        }
        var currentUser = user.get();
        var token = "token";
        return Optional.of(new ImmutablePair<>(currentUser, token));
    }
}
