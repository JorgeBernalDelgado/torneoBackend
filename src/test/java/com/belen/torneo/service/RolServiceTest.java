package com.belen.torneo.service;

import com.belen.torneo.domain.Rol;
import com.belen.torneo.enums.ERol;
import com.belen.torneo.repository.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByRolNombre() {
        // Arrange
        ERol nombre = ERol.ROL_ADMIN; // Enum de ejemplo para el nombre del rol
        Rol rol = new Rol(/* Construir el objeto con los datos necesarios */);
        when(rolRepository.findByNombre(nombre)).thenReturn(Optional.of(rol));

        // Act
        Optional<Rol> resultado = rolService.getByRolNombre(nombre);

        // Assert
        assertEquals(Optional.of(rol), resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Rol rol = new Rol(/* Construir el objeto con los datos necesarios */);

        // Act
        rolService.save(rol);

        // Assert
        // Verificar que se haya llamado al método save del repositorio de rol
        verify(rolRepository, times(1)).save(rol);
    }
}
