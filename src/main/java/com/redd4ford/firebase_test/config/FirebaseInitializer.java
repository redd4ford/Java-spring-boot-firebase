package com.redd4ford.firebase_test.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitializer {

  @PostConstruct
  public void initialize() throws IOException {

    InputStream serviceAccount = this.getClass()
        .getClassLoader()
        .getResourceAsStream("./firebase-private-key.json");

    assert serviceAccount != null : "firebase-private-key.json file is empty";

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://iot-test-spring-connection.firebaseio.com")
        .build();

    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(options);
    }

  }

}
