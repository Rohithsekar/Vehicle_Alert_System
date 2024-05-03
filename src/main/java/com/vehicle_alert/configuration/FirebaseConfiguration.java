package com.vehicle_alert.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseConfiguration {
    private final FirebaseProperties firebaseProperties;

    public FirebaseConfiguration(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;

    }

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        Resource serviceAccountResource = firebaseProperties.getServiceAccount();
        InputStream serviceAccountStream = serviceAccountResource.getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options, firebaseProperties.getName());
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}