package za.org.rfm.utils;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;


public class GoogleAuthorizeUtil {


    public static Credential authorize() throws IOException {

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleCredential credential = GoogleCredential.fromStream(GoogleAuthorizeUtil.class.getResourceAsStream("/google-sheets-client-secret.json"))
                .createScoped(scopes);


        return credential;
    }

    public static Credential authorize2() throws IOException {

        try {
            // Load client secrets.
            InputStream in = GoogleAuthorizeUtil.class.getResourceAsStream("/credentials.json");
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

            List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
                            .setDataStoreFactory(new MemoryDataStoreFactory())
                            .setAccessType("offline")
                            .build();
            Credential credential = new AuthorizationCodeInstalledApp(
                    flow, new LocalServerReceiver()).authorize("user");

            return credential;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
