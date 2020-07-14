package com.intecsec.java.block;

/**
 * @author Peter.Peng
 * @date 2018/3/15
 */
public class BlockUtil {
    //Hash 一个块
    public static String Hash(Block block){
        String sHash=null;

        //在这里Hash 一个块
        String s=block.sPreviousHash+block.sProof+block.sRecipient+block.sSender+block.tsCreateTime.toString();

        sHash = MD5(s);

        return sHash;
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E','F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            java.security.MessageDigest mdInst = java.security.MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        int x=5;
        int y=0;
        while(true){
            String md5=BlockUtil.MD5(""+(x*y));
            if(md5.charAt(md5.length()-1)=='0'){
                break;
            }
            y+=1;
        }
    }
}
