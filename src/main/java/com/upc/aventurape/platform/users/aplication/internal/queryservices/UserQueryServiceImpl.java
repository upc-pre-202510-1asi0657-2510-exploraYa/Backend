package com.upc.aventurape.platform.users.aplication.internal.queryservices;

import com.upc.aventurape.platform.users.domain.model.aggregates.User;
import com.upc.aventurape.platform.users.domain.model.queries.GetAllUsersQuery;
import com.upc.aventurape.platform.users.domain.model.queries.GetUserByIdQuery;
import com.upc.aventurape.platform.users.domain.model.queries.GetUserByUsernameQuery;
import com.upc.aventurape.platform.users.domain.services.UserQueryService;
import com.upc.aventurape.platform.users.infrastructure.persistence.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    public UserQueryServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }


    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
