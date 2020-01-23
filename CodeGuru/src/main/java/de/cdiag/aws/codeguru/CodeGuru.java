package de.cdiag.aws.codeguru;

import de.cdiag.aws.codeguru.configuration.AwsConfig;
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

    private AwsConfig awsConfig;
    private Profiler prof;

    @Autowired
    public CodeGuru(final AwsConfig _awsConfig) {
        this.awsConfig = _awsConfig;
        System.setProperty("aws.region", this.awsConfig.getRegion());
        prof = this.awsConfig.awsCodeGuruProfiler();
    }

    @Override
    public void run(String... args) {
        logger.info("CDI AG Profiler started for Region \"{}\"", this.awsConfig.getRegion());

        prof.start();
    }

    @PreDestroy
    private void stop() {
        logger.info("Stopping Profiler");
        if (prof.isRunning()) {
            prof.stop();
        }
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(CodeGuru.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
