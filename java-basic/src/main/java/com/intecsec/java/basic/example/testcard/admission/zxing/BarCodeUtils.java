package com.intecsec.java.basic.example.testcard.admission.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class BarCodeUtils {
    public static BitMatrix getShapeCode(String code, int width, int height) {
        // 编码条形码
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        BitMatrix matrix = null;
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            matrix = new MultiFormatWriter().encode(code,
                    BarcodeFormat.CODE_128, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static byte[] getByte(String code, int width, int height) {
        BitMatrix matrix = getShapeCode(code, width, height);
        // 返回png图片流
        // 获得Servlet输出流

        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png",
                    outStream);
            outStream.flush();
            return outStream.toByteArray();
        } catch (Exception e) {
            return null;
        }

    }

    public static void getByte(File file, String code, int width, int height) {
        BitMatrix matrix = getShapeCode(code, width, height);
        // 返回png图片流
        // 获得Servlet输出流

        try (FileOutputStream outStream = new FileOutputStream(file)) {
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png",
                    outStream);
            outStream.flush();

        } catch (Exception e) {

        }

    }

    public static void main(String[] args) throws Exception {
        getByte(new File("123.png"), "6051930500003940", 500, 250);
    }
}
