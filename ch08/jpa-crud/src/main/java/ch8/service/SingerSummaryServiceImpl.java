package ch8.service;

import ch8.view.SingerSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        List<SingerSummary> result = em.createQuery(
                "SELECT new ch8.view.SingerSummary(" +
                        "s.firstName, s.lastName, a.title) FROM Singer s " +
                        "LEFT JOIN s.albums a " +
                        "WHERE a.releaseDate=(SELECT MAX(a2.releaseDate) " +
                        "FROM Album a2 WHERE a2.singer.id = s.id)",
                SingerSummary.class
        ).getResultList();
        return result;
    }
}
