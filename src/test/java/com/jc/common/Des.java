package com.jc.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Des {

	@Test
	public void test(){
		
		String key =  Base64.encodeBase64String(getKey());
		System.out.println(key);
		try{
			FileWriter keyfile = new FileWriter("C:\\Users\\JcL\\Desktop\\key.xml");
			BufferedWriter keybw = new BufferedWriter(keyfile);
			keybw.write(key);
			keybw.flush();
			keybw.close();
			keyfile.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  生成KEY
	 */
	public static byte[] getKey(){
		 KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("----------------KEY生成失败！");
			return null;
		}
		 keyGenerator.init(56);
		 SecretKey secretKey = keyGenerator.generateKey();
		 byte[] byteKey = secretKey.getEncoded();
		 return byteKey;
	}
	
	/**
	 * DES加密
	 * 
	 * @param context
	 * @return
	 */
	public static byte[] desEncrypt(String context, byte[] key) {
		try {
			// KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey conventSecretKey = secretKeyFactory.generateSecret(desKeySpec);

			// 加密
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, conventSecretKey, new IvParameterSpec(key));
			return cipher.doFinal(context.getBytes("UTF-8"));
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * DES解密
	 * 
	 * @param context
	 * @return
	 */
	public static byte[] desDecrypt(byte[] context, byte[] key) {
		try {
			// KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey conventSecretKey = secretKeyFactory.generateSecret(desKeySpec);

			// 解密
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, conventSecretKey, new IvParameterSpec(key));
			return cipher.doFinal(context);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
