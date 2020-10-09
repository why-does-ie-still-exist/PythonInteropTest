package com.company;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        URL altitudeurl = new URL("http://localhost:8080/alt");
        HttpURLConnection con = (HttpURLConnection) altitudeurl.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String json = in.readLine();
        con.disconnect();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new StringReader(json));
        System.out.println(object.get("y"));
    }
}
