package com.pushNoti;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.io.IOException;

public class ApplicationClass {

    static {
        try {
            FirebaseInitializer.initializeFirebase();
        } catch (IOException e) {
            System.out.println("Failed to initialize Firebase: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void triggerPush(String msg, String title, String fcmParam) {

        Message message = Message.builder()
                .setToken(fcmParam)
                .setNotification(
                		Notification.builder()
                		.setTitle(title)
                		.setBody(msg)
                		.build()
                		)
                .build();

        String response;

        try {
            response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Push notification triggered successfully: " + response);
        } catch (FirebaseMessagingException e) {
            System.out.println("Push notification trigger error (FirebaseMessagingException): " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Push notification trigger error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    	triggerPush(
    			"Annual leave request by Shubham", 
    			"Leave Request",
                "cxQzVN03TuyaAqmWHj9rhP:APA91bFUhWlXTKMHGapHzd7FR0S05GocSczUai5B8vLWMXMfZQOmQqd5m87yBssazBXFhjMRbwz_Lv0g6UYALMe11N3iEgk7jwXHWkCsWcOk8Me_wgOj3TNtF7KtTK0NFMduJ49jcKxD"
                );
    	
    }
}