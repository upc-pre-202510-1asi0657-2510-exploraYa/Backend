package com.upc.aventurape.platform.users.domain.model.commands;
import com.upc.aventurape.platform.users.domain.model.entities.Role;
import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
