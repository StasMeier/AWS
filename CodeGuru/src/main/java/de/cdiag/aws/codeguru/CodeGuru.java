package de.cdiag.aws.codeguru;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import software.amazon.codeguruprofilerjavaagent.Profiler;


@SpringBootApplication
public class CodeGuru {

//    public static final Logger LOGGER = LoggerFactory.getLogger(CodeGuru.class);

    public static void main(String[] args) {
        System.err.println("test");
        new Profiler.Builder()
                .profilingGroupName("CDI_AG")
                .build().start();

        new SpringApplicationBuilder(CodeGuru.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
