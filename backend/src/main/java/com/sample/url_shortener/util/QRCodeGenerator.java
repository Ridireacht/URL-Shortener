package com.sample.url_shortener.util;

import io.nayuki.qrcodegen.QrCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.Base64;

public class QRCodeGenerator {

    public static String generate(String codeText) {
        QrCode qrCode = QrCode.encodeText(codeText, QrCode.Ecc.MEDIUM);
        BufferedImage img = qrCodeToImage(qrCode, 4, 10);
        return imageToBase64String(img, "png");
    }

    public static BufferedImage qrCodeToImage(QrCode qr, int scale, int border) {
        int lightColor = 0xFFFFFF;
        int darkColor = 0x000000;


        if (scale <= 0 || border < 0) {
            throw new IllegalArgumentException("Value out of range");
        }

        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale) {
            throw new IllegalArgumentException("Scale or border too large");
        }


        BufferedImage result = new BufferedImage(
                (qr.size + border * 2) * scale,
                (qr.size + border * 2) * scale,
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }

        return result;
    }

    public static String imageToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try (final OutputStream b64os = Base64.getEncoder().wrap(os)) {
            ImageIO.write(img, formatName, b64os);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }

        return os.toString();
    }
}
