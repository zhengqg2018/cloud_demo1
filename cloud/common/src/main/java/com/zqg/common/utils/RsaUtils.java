package com.zqg.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author: zqg
 * @create: 2021/1/28
 **/
@Slf4j
public class RsaUtils {
    /*密钥长度*/
    public final static int KEY_SIZE = 1024;
    /*签名字符集*/
    public final static String CHARSET = "UTF-8";
    /*数字签名密钥算法*/
    public static final String KEY_ALGORITHM = "RSA";

    /*数字签名验证算法*/
    public static final String SIGN_ALGORITHM = "SHA256withRSA";

    public static String encrypt(String data, String publicKey){
        return null;
    }
    /**
     * 获取 秘钥
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        generator.initialize(KEY_SIZE);
        return generator.generateKeyPair();
    }
    /**
     * 公钥加密
     */
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }
    /**
     * 私钥加密
     */
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey)throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public static PublicKey base64ToPublicKey(String string) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(string));
        return KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(keySpec);
    }

    public static PrivateKey base64ToPrivateKey(String  string) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(string));
        return KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(keySpec);
    }

    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    public static byte[] base64Byte2(String string) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(string);
    }
    /**
     * 私钥加签
     */
    public static String signatureBySHA256withRSA(String content, String privateKey, String charset) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(base64ToPrivateKey(privateKey));
        signature.update(content.getBytes(CHARSET));
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 公钥解签
     */
    public static String verifyBySHA256withRSA(String content, String publicKey, String charset) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(base64ToPublicKey(publicKey));
        signature.update(content.getBytes(CHARSET));
        return Base64.encodeBase64String(signature.sign());
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = getKeyPair();
            String message = "这是一条神奇的记录";
            byte[] publicBytes = publicEncrypt(message.getBytes(), keyPair.getPublic());

            String base64 = byte2Base64(publicBytes);
            log.info("加密后：{}", base64);

            byte[]  bytes = base64Byte2(base64);
            byte[] privateBytes  = privateDecrypt(bytes, keyPair.getPrivate());
            log.info("解密后：{}", new String(privateBytes));

            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));

            log.info("private:{}", privateKey);
            log.info("public:{}",publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}