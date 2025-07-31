package com.fxz.artagent;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility for loading backend configuration.
 */
public class BackendConfig {
    private static String baseUrl;

    public static String getBaseUrl(Context context) {
        if (baseUrl == null) {
            loadConfig(context);
        }
        return baseUrl;
    }

    private static void loadConfig(Context context) {
        baseUrl = "http://166.111.139.116:22231"; // default
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("backend_config.json");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            JSONObject obj = new JSONObject(baos.toString("UTF-8"));
            String host = obj.optString("host");
            int port = obj.optInt("port");
            if (!host.isEmpty() && port != 0) {
                baseUrl = "http://" + host + ":" + port;
            }
        } catch (IOException e) {
            // keep default
        } catch (Exception e) {
            // keep default
        }
    }
}
