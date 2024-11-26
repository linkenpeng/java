package com.intecsec.java.util;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    public static String gzip(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("gzip failure");
        }

        return Base64.encodeBase64String(out.toByteArray());
    }

    public static String unzip(String compressedStr) {
        if (compressedStr == null || compressedStr.length() <= 0) {
            return compressedStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in;
        GZIPInputStream gzip = null;
        byte[] compressed;
        String decompressed;

        try {
            compressed = Base64.decodeBase64(compressedStr);
            in = new ByteArrayInputStream(compressed);
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = gzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("string unzip failure");
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException ignored) {
                }
            }
            try {
                out.close();
            } catch (IOException ignored) {
            }
        }
        return decompressed;
    }

    public static String unzipByte(byte[] compressedBytes) {
        if (compressedBytes == null || compressedBytes.length <= 0) {
            return "";
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in;
        GZIPInputStream gzip = null;
        String decompressed;

        try {
            in = new ByteArrayInputStream(compressedBytes);
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = gzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("String unzip failure");
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException ignored) {
                }
            }
            try {
                out.close();
            } catch (IOException ignored) {
            }
        }
        return decompressed;
    }

    public static void main(String[] args) {
        String s = "Gzip test string";
        String gzipString = gzip(s);
        System.out.println(gzipString);
        String unzipString = unzip(gzipString);
        System.out.println(unzipString);
    }

}
