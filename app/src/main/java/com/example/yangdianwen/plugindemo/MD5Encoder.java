package com.example.yangdianwen.plugindemo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangdianwen on 2016/5/17.
 */
public class MD5Encoder{
//{
//    private static final char[] HEX =
//            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//
//    public static final String encode(String source)
//    {
//        try
//        {
//            byte[] sourceBytes = source.getBytes();
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
//            mdInst.update(sourceBytes);
//            byte[] md = mdInst.digest();
//            int j = md.length;
//            char str[] = new char[j];
//            int k = 0;
//            for (int i = 0; i < j; i++)
//            {
//                str[k++] = HEX[md[i] >>> 4 & 0xf];
//                str[k++] = HEX[md[i] & 0xf];
//            }
//            return new String(str);
//        } catch (Exception e)
//        {
//            return null;
//        }
//    }
//
//    public static final boolean isPasswordValid(String encPass, String rawPass)
//    {
//        if (encPass.equals(rawPass))
//        {
//            return true;
//        }
//        return MD5Encoder.encode(rawPass).equals(encPass);
//    }}
public static String encode(String password) {
        StringBuffer sb=new StringBuffer();
        try {
        MessageDigest MD5=MessageDigest.getInstance("MD5");
        byte[]bytes=MD5.digest(password.getBytes());
        for (byte b:bytes) {
        int c=(b+10)%256&0xff;
        String hexString=Integer.toHexString(c);
        if(hexString.length()==1){
        hexString=0+hexString;
        }
        sb.append(hexString);
        }
        } catch (NoSuchAlgorithmException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        return sb.toString();

        }}