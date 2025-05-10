package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;
import com.upc.aventurape.platform.iam.domain.model.commands.SignUpCommand;
import com.upc.aventurape.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.upc.aventurape.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.upc.aventurape.platform.iam.domain.services.UserCommandService;
import com.upc.aventurape.platform.iam.domain.services.UserQueryService;
import com.upc.aventurape.platform.iam.interfaces.acl.IamContextFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Field;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

// Nota: Implementa la clase UserService en tu proyecto si no existe.
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserCommandService userCommandService;
    @Mock
    private UserQueryService userQueryService;
    @InjectMocks
    private IamContextFacade iamContextFacade;

    @BeforeEach
    void setUp() {
        iamContextFacade = new IamContextFacade(userCommandService, userQueryService);
    }

    private void setUserId(User user, Long id) {
        try {
            Field field = user.getClass().getSuperclass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(user, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void crearUsuario_DeberiaRetornarIdValido() {
        // Arrange
        User user = new User("usuario", "password");
        setUserId(user, 1L);
        when(userCommandService.handle(isA(SignUpCommand.class))).thenReturn(Optional.of(user));
        // Act
        Long id = iamContextFacade.createUser("usuario", "password");
        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    void fetchUserIdByUsername_DeberiaRetornarId() {
        // Arrange
        User user = new User("usuario", "password");
        setUserId(user, 10L);
        when(userQueryService.handle(isA(GetUserByUsernameQuery.class))).thenReturn(Optional.of(user));
        // Act
        Long id = iamContextFacade.fetchUserIdByUsername("usuario");
        // Assert
        assertEquals(10L, id);
    }

    @Test
    void fetchUsernameByUserId_DeberiaRetornarUsername() {
        // Arrange
        User user = new User("usuario", "password");
        setUserId(user, 20L);
        when(userQueryService.handle(isA(GetUserByIdQuery.class))).thenReturn(Optional.of(user));
        // Act
        String username = iamContextFacade.fetchUsernameByUserId(20L);
        // Assert
        assertEquals("usuario", username);
    }
} 