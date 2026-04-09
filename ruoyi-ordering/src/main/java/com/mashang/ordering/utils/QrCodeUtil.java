package com.mashang.ordering.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;

public class QrCodeUtil {

    public static void generateQRCodeByContent(String content, int size, ServletOutputStream outputStream) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            javax.imageio.ImageIO.write(image, "png", outputStream);
        } catch (Exception e) {
            throw new RuntimeException("生成二维码失败");
        }
    }
}
