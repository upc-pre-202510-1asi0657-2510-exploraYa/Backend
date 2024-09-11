package com.upc.aventurape.platform.users.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
