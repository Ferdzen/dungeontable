package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.repository.MagicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class MagicServiceTest {

    @InjectMocks
    private MagicService magicService;

    @Mock
    private MagicRepository magicRepository;

    private Magic validMagic;

    @BeforeEach
    void setup() {
        validMagic = new Magic();
        validMagic.setName("Andar na Água");
        validMagic.setSchoolMagic("Transmutação");
        validMagic.setLevel("3");
        validMagic.setComponents("V, S, M");
        validMagic.setCastingTime("1 ação");
        validMagic.setRange("9 metros");
        validMagic.setDuration("1 hora");
    }

    // Auxiliar para validação de campos obrigatórios
    private void assertRequiredAttribute(String expectedAttribute, Consumer<Magic> modifier, boolean isUpdate) {
        modifier.accept(validMagic);
        if (isUpdate) validMagic.setId(1L);

        Executable action = isUpdate
                ? () -> magicService.update(validMagic)
                : () -> magicService.save(validMagic);

        BusinessException ex = assertThrows(BusinessException.class, action);
        assertTrue(ex.getMessage().contains(expectedAttribute));
    }

    // --- Testes para SAVE ---

    @Test
    void testSaveWithValidMagic() {
        when(magicRepository.save(validMagic)).thenReturn(validMagic);

        Magic saved = magicService.save(validMagic);

        assertEquals(validMagic, saved);
        verify(magicRepository).save(validMagic);
    }

    @Test
    void testSaveWithoutName() {
        assertRequiredAttribute("name item", m -> m.setName(null), false);
    }

    @Test
    void testSaveWithoutSchoolMagic() {
        assertRequiredAttribute("school magic", m -> m.setSchoolMagic(""), false);
    }

    @Test
    void testSaveWithoutLevel() {
        assertRequiredAttribute("level magic", m -> m.setLevel(null), false);
    }

    @Test
    void testSaveWithoutComponents() {
        assertRequiredAttribute("components", m -> m.setComponents(null), false);
    }

    @Test
    void testSaveWithoutCastingTime() {
        assertRequiredAttribute("castingTime", m -> m.setCastingTime(""), false);
    }

    @Test
    void testSaveWithoutRange() {
        assertRequiredAttribute("Range", m -> m.setRange(null), false);
    }

    @Test
    void testSaveWithoutDuration() {
        assertRequiredAttribute("Duration", m -> m.setDuration(""), false);
    }

    // --- Testes para UPDATE ---

    @Test
    void testUpdateWithoutId() {
        validMagic.setId(null);
        BusinessException ex = assertThrows(BusinessException.class, () -> magicService.update(validMagic));
        assertTrue(ex.getMessage().contains("obrigatório"));
    }

    @Test
    void testUpdateWithoutName() {
        assertRequiredAttribute("name item", m -> m.setName(null), true);
    }

    @Test
    void testUpdateWithValidMagic() {
        validMagic.setId(1L);
        when(magicRepository.save(validMagic)).thenReturn(validMagic);

        Magic updated = magicService.update(validMagic);

        assertEquals(validMagic, updated);
        verify(magicRepository).save(validMagic);
    }
}


