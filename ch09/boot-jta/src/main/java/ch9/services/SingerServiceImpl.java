package ch9.services;

import ch9.entities.Singer;
import ch9.ex.AsyncXAResourcesException;
import ch9.repos.SingerRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    private SingerRepository singerRepository;
    private JmsTemplate jmsTemplate;

    public SingerServiceImpl(SingerRepository singerRepository, JmsTemplate jmsTemplate) {
        this.singerRepository = singerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Singer save(Singer singer) {
        jmsTemplate.convertAndSend("singers", "방금 저장됨:" + singer);
        if (singer == null) {
            throw new AsyncXAResourcesException("잘못된 상황의 시뮬레이션");
        }
        singerRepository.save(singer);
        return singer;
    }

    @Override
    public long count() {
        return singerRepository.count();
    }
}
