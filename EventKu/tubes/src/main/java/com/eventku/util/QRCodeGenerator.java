package com.eventku.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class untuk generate QR Code
 * Menggunakan ZXing library
 */
public class QRCodeGenerator {

    /**
     * Generate QR Code sebagai byte array (PNG image)
     * 
     * @param data Data yang akan di-encode ke QR code
     * @param width Width QR code dalam pixels
     * @param height Height QR code dalam pixels
     * @return byte[] PNG image data
     */
    public static byte[] generateQRCodeImage(String data, int width, int height) 
            throws WriterException, IOException {
        
        // Setup QR Code parameters
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        // Create QR Code writer
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        // Generate QR Code matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

        // Convert to PNG image
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        
        return outputStream.toByteArray();
    }

    /**
     * Generate QR Code dengan ukuran default (300x300)
     */
    public static byte[] generateQRCodeImage(String data) throws WriterException, IOException {
        return generateQRCodeImage(data, 300, 300);
    }

    /**
     * Generate data string untuk tiket QR code
     * Format: TIKET-{tiketId}|EVENT-{eventId}|PEMBELI-{pembeliId}|DATE-{timestamp}
     */
    public static String generateTiketQRData(String tiketId, String eventId, String pembeliId) {
        return String.format("TIKET-%s|EVENT-%s|PEMBELI-%s|DATE-%d|VERIFY-EVENTKU", 
                           tiketId, eventId, pembeliId, System.currentTimeMillis());
    }

    /**
     * Validate QR code data
     */
    public static boolean validateQRData(String qrData) {
        if (qrData == null || qrData.isEmpty()) {
            return false;
        }
        return qrData.contains("TIKET-") && 
               qrData.contains("EVENT-") && 
               qrData.contains("VERIFY-EVENTKU");
    }
}

