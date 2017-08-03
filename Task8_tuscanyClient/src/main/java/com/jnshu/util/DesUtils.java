package com.jnshu.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * Created by think on 2017/5/29.
 */
public class DesUtils {

    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String DEFAULT_INCODING = "utf-8";

    public final static String encrypt(String code, String key) {
        try{
            return Base64.encodeBase64String(encrypt(code.getBytes(DEFAULT_INCODING), key.getBytes(DEFAULT_INCODING)));
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static byte[] encrypt(byte[] code, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        //生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        //进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(code);
    }
    public final static String decrypt(String code, String key) {
        try{
            return new String(decrypt(Base64.decodeBase64(code), key.getBytes(DEFAULT_INCODING)), DEFAULT_INCODING);
        }catch(Exception e) {

        }
        return null;
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        //生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sectetKey = keyFactory.generateSecret(dks);
        //进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, sectetKey, sr);
        return cipher.doFinal(src);
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "123456";
        String encrypt = DesUtils.encrypt(s, "12345678");
        String encode = URLEncoder.encode(encrypt, "UTF-8");
        System.out.println(encode);

        String decode = URLDecoder.decode(encode, "UTF-8");
        String decrypt = DesUtils.decrypt(decode, "12345678");
        System.out.println(decrypt);
    }
}
