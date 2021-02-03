package i.am.pythonian23.pnwrecruiter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Sender {

    String url_text;
    URL url;
    HttpURLConnection connection;
    DataOutputStream output;

    String key, dest, subject, body;
    String urlPostParameters;

    Integer responseCode;
    BufferedReader reader;
    StringBuffer response;

    JSONParser parser;

    public Sender(String key_, String dest_, String subject_, String body_) {
        key = key_;
        dest = dest_;
        subject = subject_;
        body = body_;
        url_text = "https://politicsandwar.com/api/send-message/";
        parser = new JSONParser();
    }

    public void newDest(String dest_) {
        dest = dest_;
    }

    public void run() throws Exception {

        url = new URL(url_text);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);

        output= new DataOutputStream(connection.getOutputStream());

        urlPostParameters = "key="+key+"&to="+dest+"&subject="+subject+"&message="+body;
        output.writeBytes(urlPostParameters);
        output.flush();
        output.close();

        responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            JSONObject json = (JSONObject) parser.parse(response.toString());
            boolean success = (boolean) json.get("success");

            if (!success) {
                System.out.println("FAILED!");
            }

        }

    }

}
