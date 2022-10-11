package com.lianlianpay.ytapi.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class LLianPayEncrypt {
    private LLianPayEncrypt() {
    }

    public static String encrypt(String json, String publicKey, String hmackKey, String version, String aesKey, String nonce) {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String B64hmack_key = rsaEncrypt(hmackKey, publicKey);
            String B64aes_key = rsaEncrypt(aesKey, publicKey);
            String B64nonce = encoder.encode(nonce.getBytes());
            String encry = aesEncrypt(json.getBytes(StandardCharsets.UTF_8), aesKey.getBytes(), nonce.getBytes());
            String message = B64nonce + "$" + encry;
            byte[] sign = encodeHmacSHA256(message.getBytes(), hmackKey.getBytes());
            String B64sign = encoder.encode(sign);
            String encrpt_str = version + "$" + B64hmack_key + "$" + B64aes_key + "$" + B64nonce + "$" + encry + "$" + B64sign;
            return encrpt_str;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String rsaEncrypt(String source, String publicKey) throws Exception {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(publicKey);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(1, keyFactory.generatePublic(x509ek));
        byte[] sbt = source.getBytes(StandardCharsets.UTF_8);
        byte[] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    private static String aesEncrypt(byte[] msgbt, byte[] aesKey, byte[] nonce) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        IvParameterSpec ips = createCtrIv(nonce);
        cipher.init(1, secretKeySpec, ips);
        byte[] epByte = cipher.doFinal(msgbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    private static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data);
        return digest;
    }

    private static IvParameterSpec createCtrIv(byte[] nonce) {
        byte[] counter = new byte[]{0, 0, 0, 0, 0, 0, 0, 1};
        byte[] output = new byte[nonce.length + counter.length];
        for (int i = 0; i < nonce.length; ++i) {
            output[i] = nonce[i];
        }
        for (int i = 0; i < counter.length; ++i) {
            output[i + nonce.length] = counter[i];
        }
        return new IvParameterSpec(output);
    }
}
