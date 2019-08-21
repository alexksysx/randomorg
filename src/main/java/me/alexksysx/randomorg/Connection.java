package me.alexksysx.randomorg;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
    public static String post(URL url, String request) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream os = con.getOutputStream();
        os.write(request.getBytes("UTF-8"));
        os.close();
        InputStream in = new BufferedInputStream(con.getInputStream());
        String result = IOUtils.toString(in, "UTF-8");
        in.close();
        con.disconnect();
        return result;
    }
}
