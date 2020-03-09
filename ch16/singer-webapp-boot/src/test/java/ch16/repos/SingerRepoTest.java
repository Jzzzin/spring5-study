package ch16.repos;

import ch16.entities.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class SingerRepoTest {

    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Test
    public void testSaveSinger() {
        Singer singer = new Singer();
        singer.setFirstName("Angus");
        singer.setLastName("Young");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1991, 2, 17)).getTime().getTime()
        ));
        singerRepository.save(singer);

        //get all singers, list should have 15
        Iterable<Singer> singers = singerRepository.findAll();
        int count = 0;

        for (Singer s : singers) {
            count++;
        }
        assertEquals(count, 15);
    }
}
