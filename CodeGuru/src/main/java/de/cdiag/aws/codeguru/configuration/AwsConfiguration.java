package de.cdiag.aws.codeguru.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.codeguruprofilerjavaagent.Profiler;


@Configuration
@Data
@Profile("aws")
public class AwsConfiguration {

    @Value("${aws.username}")
    protected String awsUsername;

    @Value("${aws.region}")
    protected String region;

    @Value("${aws.accessKey}")
    protected String awsAccessKey;

    @Value("${aws.secretKey}")
    protected String awsSecretKey;

    @Value("${aws.profiling.group.name}")
    protected String profilingGroupName;

    @Bean
    public Profiler awsCodeGuruProfiler() {
        return new Profiler.Builder()
                .profilingGroupName(profilingGroupName)
                .awsCredentialsProvider(this.amazonAWSCredentials())
                .build();
    }

    @Bean
    @Primary
    public AwsCredentialsProvider amazonAWSCredentials() {
        return () -> new AwsCredentials() {
            @Override
            public String accessKeyId() {
                System.out.println("awsAccessKey: " + awsAccessKey);
                return getAwsAccessKey();
            }

            @Override
            public String secretAccessKey() {
                System.out.println("awsSecretAccessKey " + awsSecretKey);
                return getAwsAccessKey();
            }
        };
    }
}
