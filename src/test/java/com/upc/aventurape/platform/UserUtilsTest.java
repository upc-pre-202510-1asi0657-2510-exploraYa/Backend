package com.upc.aventurape.platform;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserUtilsTest {
    @Test
    void passwordSegura_DeberiaRetornarTrue() {
        assertTrue(UserUtils.esPasswordSegura("12345678"));
    }

    @Test
    void passwordInsegura_DeberiaRetornarFalse() {
        assertFalse(UserUtils.esPasswordSegura("1234"));
        assertFalse(UserUtils.esPasswordSegura(null));
    }
} 