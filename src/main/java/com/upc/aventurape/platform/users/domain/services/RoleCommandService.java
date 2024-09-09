package com.upc.aventurape.platform.users.domain.services;

import com.upc.aventurape.platform.users.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
