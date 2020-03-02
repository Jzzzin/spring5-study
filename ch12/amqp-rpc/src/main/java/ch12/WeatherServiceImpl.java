package ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/* XML 구성의 경우
@Component
public class WeatherServiceImpl implements WeatherService {

    @Override
    public String getForecast(String stateCode) {
        if ("FL".equals(stateCode)) {
            return "더움";
        } else if ("MA".equals(stateCode)) {
            return "추움";
        }

        return "지금은 이용할 수 없음";
    }
}
*/

@Service
public class WeatherServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", queues = "forecasts")
    public void getForecast(String stateCode) {
        if ("FL".equals(stateCode)) {
            logger.info("더움");
        } else if ("MA".equals(stateCode)) {
            logger.info("추움");
        } else {
            logger.info("지금은 이용할 수 없음");
        }
    }
}