package com.goach.client.util;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Created by Yuriy on 2016/6/11 0011.
 */
public class Utils {
    public static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
    public static String makeSign(Map<String, String> params) {
        final String signSalt = "fe#%d8ec93a1159a2a3";
        TreeMap<String, Object> sorted = new TreeMap<String, Object>();
        for (Map.Entry<String, String> kv : params.entrySet()) {
            sorted.put(kv.getKey(), kv.getValue());
        }
        StringBuilder sb = new StringBuilder(signSalt);
        for (String key : sorted.keySet()) {
            if (!"sign".equals(key) && !key.startsWith("file_")) {
                sb.append(key).append(sorted.get(key));
            }
        }
        sb.append(signSalt);
        return MD5.md5(sb.toString()).toUpperCase();
    }
}
