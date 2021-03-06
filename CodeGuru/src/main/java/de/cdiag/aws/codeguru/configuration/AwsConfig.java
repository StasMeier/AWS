package de.cdiag.aws.codeguru.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.codeguruprofilerjavaagent.Profiler;


@Configuration
@PropertySource(value = "classpath:aws.gitignore.yml")
@Data
@Profile("aws")
public class AwsConfig {

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

    public Profiler awsCodeGuruProfiler() {
        return new Profiler.Builder()
                .profilingGroupName(profilingGroupName)
                .awsRegionToReportTo(Region.of(this.region))
                .awsCredentialsProvider(this.amazonAWSCredentials())
                .build();
    }

    public AwsCredentialsProvider amazonAWSCredentials() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(getAwsAccessKey(),getAwsSecretKey()));
    }
}
