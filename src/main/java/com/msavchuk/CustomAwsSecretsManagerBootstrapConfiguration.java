package com.msavchuk;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.aws.secretsmanager.AwsSecretsManagerProperties;
import org.springframework.cloud.aws.secretsmanager.AwsSecretsManagerPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;


@Configuration
public class CustomAwsSecretsManagerBootstrapConfiguration {

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${aws.secretsmanager.prefix:/secret}")
    private String prefix;

    @Value("${aws.secretsmanager.defaultContext:application}")
    private String defaultContext;

    @Value("${aws.secretsmanager.fail-fast:true}")
    private Boolean failFast;

    @Value("${aws.secretsmanager.name:}")
    private String name;

    @Value("${aws.secretsmanager.profileSeparator:_}")
    private String profileSeparator;

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public AWSSecretsManager smClient(AWSCredentialsProvider profileCredentialsProvider) {
        var builder = AWSSecretsManagerClientBuilder.standard();
        builder.setCredentials(profileCredentialsProvider);
        var manager = builder.withRegion(awsRegion).build();
        var request = new GetSecretValueRequest();
        request.setSecretId("/secret/device");
        System.out.println(manager.getSecretValue(request).getSecretString());

        return manager;
    }
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public AwsSecretsManagerPropertySourceLocator awsSecretsManagerPropertySourceLocator(
            AWSSecretsManager smClient,
            ConfigurableEnvironment environment
    ) {
        var awsSecretsManagerProperties = new AwsSecretsManagerProperties();

        awsSecretsManagerProperties.setPrefix(prefix);
        awsSecretsManagerProperties.setDefaultContext(defaultContext);
        awsSecretsManagerProperties.setFailFast(failFast);
        awsSecretsManagerProperties.setName(name);
        awsSecretsManagerProperties.setProfileSeparator(profileSeparator);

        var awsSecretsManagerPropertySourceLocator = new AwsSecretsManagerPropertySourceLocator(
                smClient,
                awsSecretsManagerProperties
        );
        var properySource = awsSecretsManagerPropertySourceLocator.locate(environment);
        environment.getPropertySources().addLast(properySource);

        return awsSecretsManagerPropertySourceLocator;
    }


}
