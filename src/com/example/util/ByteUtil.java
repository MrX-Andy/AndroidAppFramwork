package com.example.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import android.util.Log;


/**
 * byte 类型帮助类
 * @author zzp
 *
 */
public class ByteUtil {
    /**
     * 随机获取0~255的byte数
     * @return
     */
    public static byte getRandomByte(){
        /*
        String adb = "http://192.168.2.59/index中文测试.jsp";
        Log.v("", "adb : " + adb);
        Log.v("", "adb to byte : " + putString(adb));
        Log.e("", "adb to byte : " + getString(putString(adb)));
        Log.e("", "adb to byte : " + getString(putString(adb), 25, 12));
         */
        byte randomNum = 0;

        Random random = new Random();
        int num = Math.abs(random.nextInt() % 256);       
        randomNum = (byte) num;
        Log.d("", "--> getRandomNum num : " + num + "  ,  randomNum : " +  (randomNum & 0xFF));
        return randomNum;
    }

    /**
     * 获取byte随机数组
     * @param arrSize 数组长度
     * @return
     */
    public static byte[] getRandomByteArrays(int arrSize){
        byte[] byteArr =new byte[arrSize];
        for(int i=0; i<arrSize; i++){
            byteArr[i] = getRandomByte();
        }
        return byteArr;
    }
    ///////////////////////////////////////////////////////////////
    // short 与 byte 之间转换
    public static void putShort(byte b[], short s, int index) {
        b[index] = ( byte) (s >> 8);
        b[index + 1] = ( byte) (s >> 0);

    }

    public static void putReverseBytesShort(byte b[], short s, int index) {
        b[index] = ( byte) (s >> 0);
        b[index + 1] = ( byte) (s >> 8);

    }

    public static short getShort(byte[] b, int index) {
        return (short ) (((b[index] << 8) | b[index + 1] & 0xff));
    }

    public static short getReverseBytesShort(byte[] b, int index) {
        return (short ) (((b[index+1] << 8) | b[index] & 0xff));
    }


    //////////////////////////////////////////////////////////////
    // Long 与 byte[] 之间转换
    public static void putLong(byte[] bb, long x, int index) {

        bb[index + 0] = ( byte) (x >> 56);
        bb[index + 1] = ( byte) (x >> 48);
        bb[index + 2] = ( byte) (x >> 40);
        bb[index + 3] = ( byte) (x >> 32);
        bb[index + 4] = ( byte) (x >> 24);
        bb[index + 5] = ( byte) (x >> 16);
        bb[index + 6] = ( byte) (x >> 8);
        bb[index + 7] = ( byte) (x >> 0);
    }

    public static void putReverseBytesLong(byte[] bb, long x, int index) {

        bb[index + 7] = ( byte) (x >> 56);
        bb[index + 6] = ( byte) (x >> 48);
        bb[index + 5] = ( byte) (x >> 40);
        bb[index + 4] = ( byte) (x >> 32);
        bb[index + 3] = ( byte) (x >> 24);
        bb[index + 2] = ( byte) (x >> 16);
        bb[index + 1] = ( byte) (x >> 8);
        bb[index + 0] = ( byte) (x >> 0);
    }

    public static long getLong(byte[] bb, int index) {

        return ((((long) bb[index + 0] & 0xff) << 56)
                | ((( long) bb[index + 1] & 0xff) << 48)
                | ((( long) bb[index + 2] & 0xff) << 40)
                | ((( long) bb[index + 3] & 0xff) << 32)
                | ((( long) bb[index + 4] & 0xff) << 24)
                | ((( long) bb[index + 5] & 0xff) << 16)
                | ((( long) bb[index + 6] & 0xff) << 8) 
                | (((long) bb[index + 7] & 0xff) << 0));

    }

    public static long getReverseBytesLong(byte[] bb, int index) {

        return ((((long) bb[index + 7] & 0xff) << 56)
                | ((( long) bb[index + 6] & 0xff) << 48)
                | ((( long) bb[index + 5] & 0xff) << 40)
                | ((( long) bb[index + 4] & 0xff) << 32)
                | ((( long) bb[index + 3] & 0xff) << 24)
                | ((( long) bb[index + 2] & 0xff) << 16)
                | ((( long) bb[index + 1] & 0xff) << 8)
                | (((long) bb[index + 0] & 0xff) << 0));

    }

    /////////////////////////////////////////////////////////////
    /**
     * int转byte 大码
     * @param bb    输出byte数组
     * @param x     输入int值
     * @param index bb偏移量
     */
    public static void putInt(byte[] bb,int x,int index){
        bb[index+0]=(byte)(x>>24);
        bb[index+1]=(byte)(x>>16);
        bb[index+2]=(byte)(x>>8);
        bb[index+3]=(byte)(x>>0);
    }

    /**
     * int转byte 小码
     * @param bb
     * @param x
     * @param index
     */
    public static void putReverseBytesInt(byte[] bb,int x,int index){
        bb[index+3]=(byte)(x>>24);
        bb[index+2]=(byte)(x>>16);
        bb[index+1]=(byte)(x>>8);
        bb[index+0]=(byte)(x>>0);
    }

    /**
     * byte转int 大码
     * @param bb
     * @param index
     * @return
     */
    public static int getInt(byte[] bb,int index){
        return(int)((((bb[index+0]&0xff)<<24)
                |((bb[index+1]&0xff)<<16)
                |((bb[index+2]&0xff)<<8)
                |((bb[index+3]&0xff)<<0)));
    }

    /**
     * byte转int 小码
     * @param bb
     * @param index
     * @return
     */
    public static int getReverseBytesInt(byte[] bb,int index){
        return(int)((((bb[index+3]&0xff)<<24)
                |((bb[index+2]&0xff)<<16)
                |((bb[index+1]&0xff)<<8)
                |((bb[index+0]&0xff)<<0)));
    }

    ///////////////////////////////////////////////////////////

    /**
     * String转byte[]
     */
    public static byte[] putString(String str){
        return str.getBytes();
    }

    /**
     * byte[] 转String
     */    
    public static String getString(byte[] bb){
        return new String(bb);
    }

    /**
     * byte[] 转String
     * @param originalByte 原始byte数组
     * @param start     截取byte数组开始位置
     * @param length    截取byte数组长度
     * @return
     */
    public static String getString(byte[] originalByte, int start, int length){

        byte [] strByte = Arrays.copyOfRange(originalByte, start, start + length);
        return new String(strByte);
    }

    ////////////////////////////////////////////////////////////////

    /**
     * 图片文件转byte[]
     * 方法1
     */
    public static byte[] putImagePath(String imagePath){
        byte[] imageByte = null;

        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(imagePath));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024); 

            byte[] temp = new byte[1024]; 
            int size = 0; 

            while ((size = in.read(temp)) != -1) { 
                out.write(temp, 0, size); 
            }

            in.close(); 

            imageByte = out.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return imageByte;
    }

    /** 
     * 文件到byte数组
     * @param path
     * @return
     */
    public static byte[] file2byte(String path){
        byte[] data = null;
        data = file2byte(new File(path));
        return data;
    }

    /**
     * 文件到byte数组
     * @param file
     * @return
     */
    public static byte[] file2byte(File file){
        byte[] data = null;
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * 将byte[]转成文件
     */
    public static void byte2file(byte[] data,String path){
        if(null == data) return;
        if(data.length<3||path.equals("")) return;
        try{
            FileOutputStream imageOutput = new FileOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * byte数组到16进制字符串
     * @param data
     * @return
     */
    public String byte2string(byte[] data){
        if(data==null||data.length<=1) return "0x";
        if(data.length>200000) return "0x";
        StringBuffer sb = new StringBuffer();
        int buf[] = new int[data.length];
        //byte数组转化成十进制
        for(int k=0;k<data.length;k++){
            buf[k] = data[k]<0?(data[k]+256):(data[k]);
        }
        //十进制转化成十六进制
        for(int k=0;k<buf.length;k++){
            if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
            else sb.append(Integer.toHexString(buf[k]));
        }
        return "0x"+sb.toString().toUpperCase();
    } 


    ////////////////////////////////////////////////////////////////////////

    //复制 比较 连接
    
    /**
     * 复制一个跟源byte数组一样的byte数组
     * @param rSource 源byte数组
     * @return 跟源byte[]数组一样的byte[]数组
     */
    static public byte[] copy(byte[] rSource) {
        byte[] aResult = new byte[rSource.length];
        System.arraycopy(rSource, 0, aResult, 0, aResult.length);
        return aResult;
    }
    /**
     * 复制一个跟源int数组一样的int数组
     * @param rSource 源int数组
     * @return 跟源int数组一样的int数组
     */
    static public int[] copy(int[] rSource) {
        int[] aResult = new int[rSource.length];
        System.arraycopy(rSource, 0, aResult, 0, aResult.length);
        return aResult;
    }
    /**
     * 比较两个byte数组的内容及长度是否相等.
     * @param a1 第一个byte数组
     * @param a2 第二个byte数组
     * @return 相等的话返回true，否则返回false
     */
    static public boolean equals(byte[] a1, byte[] a2) {
        if ( (a1 == null) || (a2 == null)) {
            return a1 == a2;
        }
        int nLength = a1.length;
        if (nLength != a2.length) {
            return false;
        }
        for (int i = 0; i < nLength; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
    /**
     * 比较两个int数组的内容及长度是否相等.
     * @param a1 第一个int数组
     * @param a2 第二个int数组
     * @return 相等的话返回true，否则返回false
     */
    static public boolean equals(int[] a1, int[] a2) {
        if ( (a1 == null) || (a2 == null)) {
            return a1 == a2;
        }
        int nLength = a1.length;
        if (nLength != a2.length) {
            return false;
        }
        for (int i = 0; i < nLength; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
    /**
     * 连接两个byte数组，之后返回一个新的连接好的byte数组
     * @param a1
     * @param a2
     * @return 一个新的连接好的byte数组
     */
    static public byte[] join(byte[] a1, byte[] a2) {
        byte[] result = new byte[a1.length + a2.length];
        System.arraycopy(a1, 0, result, 0, a1.length);
        System.arraycopy(a2, 0, result, a1.length, a2.length);
        return result;
    }
    /**
     * 连接两个int数组，之后返回一个新的连接好的int数组
     * @param a1
     * @param a2
     * @return 一个新的连接好的int数组
     */
    static public int[] join(int[] a1, int[] a2) {
        int[] result = new int[a1.length + a2.length];
        System.arraycopy(a1, 0, result, 0, a1.length);
        System.arraycopy(a2, 0, result, a1.length, a2.length);
        return result;
    }

}
