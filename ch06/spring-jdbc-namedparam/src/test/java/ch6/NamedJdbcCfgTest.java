package ch6;

import ch6.config.NamedJdbcCfg;
import ch6.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NamedJdbcCfgTest {

    @Test
    public void testCfg() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertTrue("John Mayer".equals(singerName));

        ctx.close();
    }
}
