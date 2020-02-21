package ch4.annotated;

import ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

    private String message;

    public ConfigurableMessageProvider(
            @Value("Love on the weekend") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
