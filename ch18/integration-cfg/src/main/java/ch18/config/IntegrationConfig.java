package ch18.config;

import ch18.MessageToJobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@Import(BatchConfig.class)
public class IntegrationConfig {

    @Autowired
    Job job;

    // <=> <int:channel id="inbound"/>
    @Bean
    public MessageChannel inbound() {
        return new DirectChannel();
    }

    // <=> <int:channel id="outbound"/>
    @Bean
    public MessageChannel outbound() {
        return new DirectChannel();
    }

    // <=> <int:channel id="loggingChannel"/>
    @Bean
    public MessageChannel loggingChannel() {
        return new DirectChannel();
    }

    // <=> <int-file:inbound-channel-adapter .../>
    @Bean
    @InboundChannelAdapter(channel = "inbound", poller = @Poller(fixedRate = "1000"))
    public MessageSource<File> inboundFileChannelAdapter() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("c://tmp"));
        source.setFilter(new SimplePatternFileListFilter("*.csv"));
        // 변경사항이 생길 때만 작동
        source.setUseWatchService(true);
        return source;
    }

    // <=> <int:transformer .../>
    @Bean
    @Transformer(inputChannel = "inbound", outputChannel = "outbound")
    public MessageToJobLauncher messageToJobLauncher() {
        return new MessageToJobLauncher(job, "file.name");
    }

    // <=> <batch-int:job-launching-gateway request-channel="outbound" reply-channel="loggingChannel"/>
    @Bean
    @ServiceActivator(inputChannel = "outbound")
    public JobLaunchingGateway jobLaunchingGateway(JobLauncher jobLauncher) {
        JobLaunchingGateway jg = new JobLaunchingGateway(jobLauncher);
        jg.setOutputChannel(loggingChannel());
        return jg;
    }

    // <=> <int:logging-channel-adapter channel="loggingChannel"/>
    @Bean
    @ServiceActivator(inputChannel = "loggingChannel")
    public LoggingHandler logging() {
        return new LoggingHandler(LoggingHandler.Level.INFO);
    }
}
