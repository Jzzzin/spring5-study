package ch13.mockito;

import ch13.SingerController;
import ch13.entities.Singer;
import ch13.entities.Singers;
import ch13.services.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    @Before
    public void initSingers() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singers.add(singer);
    }

    @Test
    public void testList() throws Exception {
        SingerService singerService = mock(SingerService.class);
        when(singerService.findAll()).thenReturn(singers);

        SingerController singerController = new SingerController();

        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", singerController.listData());

        Singers modelSingers = (Singers) uiModel.get("singers");

        assertEquals(1, modelSingers.getSingers().size());
    }

    @Test
    public void testCreate() {
        final Singer newSinger = new Singer();
        newSinger.setId(999L);
        newSinger.setFirstName("Stevie");
        newSinger.setLastName("Vaughan");

        SingerService singerService = mock(SingerService.class);
        when(singerService.save(newSinger)).thenAnswer(invocation -> {
            singers.add(newSinger);
            return newSinger;
        });

        SingerController singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999L), singer.getId());
        assertEquals("Stevie", singer.getFirstName());
        assertEquals("Vaughan", singer.getLastName());

        assertEquals(2, singers.size());
    }
}