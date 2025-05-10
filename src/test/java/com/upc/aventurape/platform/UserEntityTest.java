package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
    @Test
    void crearUsuario_DeberiaRetornarDatosCorrectos() {
        // Arrange
        User user = new User("usuario", "password");
        // Act & Assert
        assertEquals("usuario", user.getUsername());
        assertEquals("password", user.getPassword());
    }
} 