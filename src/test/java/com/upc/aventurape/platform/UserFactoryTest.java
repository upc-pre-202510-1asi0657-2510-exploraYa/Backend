package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {
    @Test
    void factoryCreaUsuarioCorrectamente() {
        User user = UserFactory.crearUsuario("usuario", "password");
        assertEquals("usuario", user.getUsername());
        assertEquals("password", user.getPassword());
    }
} 