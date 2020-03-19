package com.ibm.algorithms;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class des {

    private SecretKeySpec secretKey;
    private byte[] key;
 
    public void setKey(String myKey) throws Exception
    {
        //IDK how to do this, please figure out 
        //byte[] decodedKey = Base64.getDecoder().decode(myKey);
        //SecretKey sceretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
    }
 
    public String encrypt(String strToEncrypt, String secret) 
    {
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    

}