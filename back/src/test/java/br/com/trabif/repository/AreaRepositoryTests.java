package br.com.trabif.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.trabif.domain.Area;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AreaRepositoryTests {

    @Mock
    private AreaRepository areaRepository;

    @Test
    void findByDescricao() {
        Area area1 = new Area();
        area1.setDescricao("Area 1");

        Area area2 = new Area();
        area2.setDescricao("Area 2");

        Page<Area> page = new PageImpl<>(Arrays.asList(area1, area2));

        Mockito.when(areaRepository.findByDescricao("Ar", Pageable.unpaged())).thenReturn(page);

        Page<Area> result = areaRepository.findByDescricao("Ar", Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
        assertEquals("Area 1", result.getContent().get(0).getDescricao());
    }
}