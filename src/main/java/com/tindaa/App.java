package com.tindaa;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class App {
  public static void main(String[] args) {

    System.setProperty("FIRESTORE_EMULATOR_HOST", "http://localhost:5000");

    try {
      // Use a service account
      InputStream serviceAccount = new FileInputStream("src/main/resources/service-account-key.json");
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(credentials)
          .build();
      FirebaseApp.initializeApp(options);

    } catch (IOException e1) {
      e1.printStackTrace();
    }

    Firestore db = FirestoreClient.getFirestore();

    DocumentReference docRef = db.collection("users").document("alovelace");
    // Add document data with id "alovelace" using a hashmap
    Map<String, Object> data = new HashMap<>();
    data.put("first", "Ada");
    data.put("last", "Lovelace");
    data.put("born", 1815);
    // asynchronously write data
    ApiFuture<WriteResult> result = docRef.set(data);

    // result.get() blocks on response
    try {
      System.out.println("Update time : " + result.get().getUpdateTime());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
