package ch8;

import ch8.config.JpaConfig;
import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerJPATest {
    private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSinger(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());
        logger.info(singer.toString());
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()
        ));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()
        ));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1962,3,20)).getTime().getTime()
        ));
        singer.addAlbum(album);

        singerService.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        // 가수가 존재하는지 확인
        assertNotNull(singer);
        // 원하는 레코드를 조회했는지 확인
        assertEquals("Mayer", singer.getLastName());
        // 앨범을 조회
        Album album = singer.getAlbums().stream()
                .filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        // 삭제한 가수가 존재하지 않는지 확인
        assertNotNull(singer);
        singerService.delete(singer);

        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    private static void listSinger(List<Singer> singers) {
        logger.info(" ---- 가수 목록:");
        singers.forEach(s -> logger.info(s.toString()));
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- 가수가 다룰 수 있는 악기 목록이 포함된 가수 목록:");
        singers.forEach(s -> {
            logger.info(s.toString());
            if (s.getAlbums() != null) {
                s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            }
            if (s.getInstruments() != null) {
                s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
            }
        });
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}
