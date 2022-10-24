package com.intecsec.java.util.encrypt;

import com.intecsec.java.util.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Symmetric Encryption
 */
public class AESUtil {
	
	/**
	 * secret algorithm
	*/
	private static final String KEY_ALGORITHM = "AES";
	
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static String aesEncrypt(String paramStr, String sysKey) throws Exception {
		byte[] key = sysKey.getBytes();
		byte[] encryptData = encrypt(paramStr.getBytes(),key);
        return Hex.encodeHexStr(encryptData);
	}

	public static String aesDecrypt(String paramStr, String sysKey) throws Exception {
		byte[] key = sysKey.getBytes();
		byte[] encryptData = Hex.decodeHex(paramStr.toCharArray());
		byte[] decryptData = decrypt(encryptData,key);
        return new String(decryptData);
	}

	public static byte[] initSecretKey() {
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			return new byte[0];
		}
		kg.init(128);
		SecretKey  secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
	
	/**
	 * convert secrete
	 * @param key	binary secret
	 */
	public static Key toKey(byte[] key){
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	/**
	 * encrypt
	 * @param data	to be encrypt
	 * @param key	binary key
	 * @param cipherAlgorithm	encrypt algorithm method
	 * @return byte[]	encrypted data
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception{
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}
	
	public static byte[] encrypt(byte[] data, Key key) throws Exception{
		return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}
	
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
		return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}

	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	
	
	
	/**
	 * decrypt
	 * @param data	to be decrypted
	 * @param key	binary key
	 * @return byte[]	decrypted data
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
		return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}

	public static byte[] decrypt(byte[] data, Key key) throws Exception{
		return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
	}

	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception{
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}
	
	private static String showByteArray(byte[] data){
		if(null == data){
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for(byte b:data){
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString();
	}

	public static Map<String, String> paramsStr2Map(String params){
	    Map<String, String> map = new HashMap<>();
        String[] kvsArr = params.split("\\|");
        for (String kv : kvsArr) {
            String[] kvArr = kv.split("=");
            if(kvArr.length >= 2){
                map.put(kvArr[0], kvArr[1]);
            }
        }
        return map;
    }

    public static String map2ParamsStr(Map<String, String> map){
	    if(map.isEmpty()){
	        return "";
        }

	    StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> encrypt : map.entrySet()) {
            sb.append(encrypt.getKey());
            sb.append("=");
            sb.append(encrypt.getValue());
            sb.append("|");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
	
	public static void main(String[] args) throws Exception {
    	String key = "abc123efg4567891";
		System.out.println(AESUtil.aesEncrypt("k1=v1&k2=v2&k3=v3", key));
		System.out.println(AESUtil.aesDecrypt("ce72461820a4094760eac8919b953037d966c82e655677fa491333ea2fb6d178", key));

        HashMap<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println(paramsStr2Map(map2ParamsStr(map)));


		String decryptData = AESUtil.aesDecrypt("", "");
		System.out.println(decryptData);
	}

}