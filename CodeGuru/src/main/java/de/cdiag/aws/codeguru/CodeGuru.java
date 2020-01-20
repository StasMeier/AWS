package de.cdiag.aws.codeguru;

import de.cdiag.aws.codeguru.configuration.AwsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import software.amazon.codeguruprofilerjavaagent.Profiler;

import javax.annotation.PreDestroy;


@SpringBootApplication
@EnableConfigurationProperties
public class CodeGuru implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(CodeGuru.class);

    private AwsConfiguration awsConfiguration;
    private Profiler prof;

    @Autowired
    public CodeGuru(final AwsConfiguration _awsConfiguration) {
        this.awsConfiguration = _awsConfiguration;
        prof = this.awsConfiguration.awsCodeGuruProfiler();
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(CodeGuru.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("CDI AG Profiler started for Region \"" + this.awsConfiguration.getRegion() + "\"");
        System.setProperty("aws.region", this.awsConfiguration.getRegion());

        prof.start();
    }

    @PreDestroy
    private void stop() {
        logger.info("Stopping Profiler");
        prof.stop();
    }
}
