package com.michaellouie.healthcheckservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    private static final Logger _log = LoggerFactory.getLogger(Utils.class);

    public static String doHttpGet(String getUrl, int timeoutSeconds) throws Exception {
        timeoutSeconds *= 1000; // convert from milliseconds -> seconds
        StringBuffer response = new StringBuffer();
        URL url = new URL(getUrl);
        HttpURLConnection conn;
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(timeoutSeconds);
        conn.setReadTimeout(timeoutSeconds);
        return doGet(conn);
    }

    public static String doHttpsGet(String getUrl, int timeoutSeconds) throws Exception {
        timeoutSeconds *= 1000; // convert from milliseconds -> seconds
        StringBuffer response = new StringBuffer();
        URL url = new URL(getUrl);
        HttpsURLConnection conn;
        conn = (HttpsURLConnection) url.openConnection();
        conn.setConnectTimeout(timeoutSeconds);
        conn.setReadTimeout(timeoutSeconds);
        return doGet(conn);
    }

    private static String doGet(HttpURLConnection conn) throws Exception {
        if (null == conn) throw new IllegalArgumentException("Cannot GET on a null connection");
        conn.setRequestMethod("GET");
        conn.setUseCaches(true);
        conn.setAllowUserInteraction(true);

        StringBuffer response = new StringBuffer();
        int responseCode = conn.getResponseCode();
        _log.debug("Response code: " + responseCode);

        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }
}
