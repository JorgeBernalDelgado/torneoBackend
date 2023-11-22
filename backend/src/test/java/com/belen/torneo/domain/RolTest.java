package com.belen.torneo.domain;

import com.belen.torneo.enums.ERol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RolTest {

    private Rol rol;

    @BeforeEach
    public void setUp() {
        rol = new Rol();
        rol.setId(1);
        rol.setNombre(ERol.ROL_ADMIN);
    }

    @Test
    public void testGetters() {
        assertEquals(1, rol.getId());
        assertEquals(ERol.ROL_ADMIN, rol.getNombre());
    }
}
