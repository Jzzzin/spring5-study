package ch8.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryUntype")
@Repository
@Transactional
public class SingerSummaryUntypeImpl {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public void displayAllSingerSummary() {
        List result = em.createQuery(
                "SELECT s.firstName, s.lastName, a.title FROM Singer s " +
                        "LEFT JOIN s.albums a " +
                        "WHERE a.releaseDate = (SELECT MAX(a2.releaseDate)" +
                        "FROM Album a2 WHERE a2.singer.id = s.id)"
        ).getResultList();
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " + values[0] + ", " +
                    values[1] + ", " + values[2]);
        }
    }
}
