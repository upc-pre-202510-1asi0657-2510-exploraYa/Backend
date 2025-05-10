package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;
import com.upc.aventurape.platform.iam.domain.model.commands.SignUpCommand;
import com.upc.aventurape.platform.iam.domain.services.UserCommandService;
import com.upc.aventurape.platform.iam.domain.services.UserQueryService;
import com.upc.aventurape.platform.iam.interfaces.acl.IamContextFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

class IamContextFacadeTest {
    @Mock
    private UserCommandService userCommandService;
    @Mock
    private UserQueryService userQueryService;
    private IamContextFacade iamContextFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iamContextFacade = new IamContextFacade(userCommandService, userQueryService);
    }

    @Test
    void facadeCreaUsuarioYRetornaId() {
        User user = new User("usuario", "password");
        setUserId(user, 99L);
        when(userCommandService.handle(isA(SignUpCommand.class))).thenReturn(Optional.of(user));
        Long id = iamContextFacade.createUser("usuario", "password");
        assertEquals(99L, id);
    }

    private void setUserId(User user, Long id) {
        try {
            var field = user.getClass().getSuperclass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(user, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 