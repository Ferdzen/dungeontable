package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.model.vo.MagicVO;
import br.edu.utfpr.dungeontable.service.MagicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MagicControllerTest {

    @InjectMocks
    private MagicController magicController;

    @Mock
    private MagicService magicService;

    @Mock
    private ModelMapper modelMapper;

    private MagicVO magicVO;
    private Magic magic;

    @BeforeEach
    void setup() {
        magicVO = new MagicVO();
        magicVO.setName("Andar na Água");
        magicVO.setSchoolMagic("Transmutação");
        magicVO.setLevel("3");
        magicVO.setComponents("V, S, M");
        magicVO.setCastingTime("1 ação");
        magicVO.setRange("9 metros");
        magicVO.setDuration("1 hora");

        magic = new Magic();
        magic.setId(1L);
        magic.setName("Andar na Água");
        magic.setSchoolMagic("Transmutação");
        magic.setLevel("3");
        magic.setComponents("V, S, M");
        magic.setCastingTime("1 ação");
        magic.setRange("9 metros");
        magic.setDuration("1 hora");
    }

    @Test
    void testSaveMagic() {
        when(modelMapper.map(magicVO, Magic.class)).thenReturn(magic);

        ResponseEntity<MagicVO> response = magicController.save(magicVO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(magicService).save(magic);
    }

    @Test
    void testUpdateMagic() {
        Long id = 1L;
        magicVO.setId(id);
        magic.setId(id);

        when(modelMapper.map(magicVO, Magic.class)).thenReturn(magic);

        ResponseEntity<MagicVO> response = magicController.update(id, magicVO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(magicVO, response.getBody());
        verify(magicService).update(magic);
    }
}
