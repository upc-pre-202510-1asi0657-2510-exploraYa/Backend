package com.upc.aventurape.platform.users.domain.services;

import com.upc.aventurape.platform.users.domain.model.aggregates.User;
import com.upc.aventurape.platform.users.domain.model.commands.SignInCommand;
import com.upc.aventurape.platform.users.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
