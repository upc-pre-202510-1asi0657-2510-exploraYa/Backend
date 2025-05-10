package com.upc.aventurape.platform;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingletonExampleTest {
    @Test
    void singletonSiempreMismaInstancia() {
        SingletonExample instancia1 = SingletonExample.getInstance();
        SingletonExample instancia2 = SingletonExample.getInstance();
        assertSame(instancia1, instancia2);
    }
}
