package com.pushNoti;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void initializeFirebase() throws IOException {
        if (!initialized) {
            synchronized (FirebaseInitializer.class) {
                if (!initialized) {

                    InputStream serviceAccount = Files.newInputStream(Paths.get("C:/serverkey.json"));
                    //server key generated from firebase console we have to give the path for server key

                    if (serviceAccount == null) {
                        throw new IOException("Firebase service account key file not found");
                    }

                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setProjectId("YOUR-PROJECT-ID") 
//Your respective project ID need to paste here which is available on fcm firebase console project details like 'project-ab89d'
                            .build();

                    FirebaseApp.initializeApp(options);
                    System.out.println("FirebaseApp initialized successfully.");
                    initialized = true;
                }
            }
        } else {
            System.out.println("FirebaseApp is already initialized.");
        }
    }
}