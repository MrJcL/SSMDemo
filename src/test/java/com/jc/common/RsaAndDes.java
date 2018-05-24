package com.jc.common;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import net.sf.json.JSONArray;

public class RsaAndDes {

	@Test
	public void test1() {
		String context = "bingosoft for java";
		String key = "password";
		byte[] b = desEncrypt(context, key);
		System.out.println(b);
		System.out.println(Hex.encodeHex(b));
		byte[] b1 = desDecrypt(b, key);
		System.out.println(new String(b1));
	}

	/**
	 * DES加密
	 * 
	 * @param context
	 * @return
	 */
	public byte[] desEncrypt(String context, String key) {
		try {
			// 生成KEY
			// KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			// keyGenerator.init(56);
			// SecretKey secretKey = keyGenerator.generateKey();
			// byte[] byteKey = secretKey.getEncoded();

			// KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey conventSecretKey = secretKeyFactory.generateSecret(desKeySpec);

			// 加密
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, conventSecretKey, new IvParameterSpec(key.getBytes()));
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
	public byte[] desDecrypt(byte[] context, String key) {
		try {
			// KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey conventSecretKey = secretKeyFactory.generateSecret(desKeySpec);

			// 解密
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, conventSecretKey, new IvParameterSpec(key.getBytes()));
			return cipher.doFinal(context);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/*------------------------RSA-----------------------*/
	@Test
	public void test3(){
		genKeyPair("C:/Users/JcL/Desktop");
	}
	
	@Test
	public void test4() throws NoSuchAlgorithmException, InvalidKeySpecException{
		String data = "这是要加密的DES";
		RSAPublicKey publicKey = getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDtcA793IoCfxs_sf6AR91tTOL5TrTkryvsuynkgZgpLKqSMXZbqOg7mn4IOcFdOpbYO0IFCQ5XEgItCkrduq-XMhvXwe-JP_rSkD_UHN2-l8SOYRDx-0lSU_eI4tUcCDgNjzX1Kr7xzr7ZnzMCd2t5iopHu1U3a_piJMcHccUtwIDAQAB");
		String data1 = publicEncrypt(data, publicKey);
		System.out.println("公钥加密后："+data1);
		RSAPrivateKey privateKey = getPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIO1wDv3cigJ_Gz-x_oBH3W1M4vlOtOSvK-y7KeSBmCksqpIxdluo6Duafgg5wV06ltg7QgUJDlcSAi0KSt26r5cyG9fB74k_-tKQP9Qc3b6XxI5hEPH7SVJT94ji1RwIOA2PNfUqvvHOvtmfMwJ3a3mKike7VTdr-mIkxwdxxS3AgMBAAECgYBWwswd9HODgoiAuBi9TC4QGMwa1Ga65kdbKe8Qt3VyBiwXVhbWkzZAlazCodHs20Zh6Wha0QHHs01URnF0S0J4JQbbxggH_PyRDdLORZbRijaR2plTk5MKVlKNAPScgzpPhCM6BhZYcMwtcUPgkwgaXX717X0lex_1AVTIm3NlWQJBAPbkdxhzDZrGEykkg5I9c8cY4tfmntXbqwzlf7K7LQwzqi3P3skbP3iifD78X_EshTF2H1Xa5R1zAOysm0M_8DsCQQCIkY6ilBu9nf6EahBTixUJifCWqeaAVDmcFtspUYklU1x0v7GuzDkyKHq1zj9O89y3bFTO1d_jyP1zbhdMxgG1AkAVyS01LBncOXRBYiZ2BhQsUmbZ9FC5wEuEtM-i3t9nvztaFYisKEpeg8AA8tYoeuw55Bl5wvoKsjY3rDd7JK43AkAsvGGY6CNeLc8d2RCYDUtK4KCq-eTDzNNCSkpocokWbQQwSksOE1_5CkGIJJOgbUmz3Ay75Ft-5mv_z-RaBdKxAkAgNKThK71cl-gnUDi_PnFGtNzfj9YghexwNY6A2XZLR_kWJTQD1-NgOjeiDyT4GokLExI229t-_vCLJHAVvthM");
		String data2 = privateDecrypt(data1, privateKey);
		System.out.println("私钥解密后："+data2);
	}
	@Test
	public void test5() throws NoSuchAlgorithmException, InvalidKeySpecException{
		//先进行DES加密
		Map<String,String> map = new HashMap<String,String>();
		String context = "bingosoft for java ?__品高软件/轨道交通部?";
		map.put("a", context);
		context = JSONArray.fromObject(map).toString();
		System.out.println("DES加密内容："+context);
		String key = "password";
		System.out.println("DES加密key："+key);
		byte[] b = desEncrypt(context, key);
		System.out.println("DES加密后内容："+Base64.encodeBase64String(b));
		//进行RSA加密解密
		String data = Base64.encodeBase64String(b);
		RSAPublicKey publicKey = getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDtcA793IoCfxs_sf6AR91tTOL5TrTkryvsuynkgZgpLKqSMXZbqOg7mn4IOcFdOpbYO0IFCQ5XEgItCkrduq-XMhvXwe-JP_rSkD_UHN2-l8SOYRDx-0lSU_eI4tUcCDgNjzX1Kr7xzr7ZnzMCd2t5iopHu1U3a_piJMcHccUtwIDAQAB");
		String data1 = publicEncrypt(data, publicKey);
		System.out.println("RSA公钥加密后："+data1);
		RSAPrivateKey privateKey = getPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIO1wDv3cigJ_Gz-x_oBH3W1M4vlOtOSvK-y7KeSBmCksqpIxdluo6Duafgg5wV06ltg7QgUJDlcSAi0KSt26r5cyG9fB74k_-tKQP9Qc3b6XxI5hEPH7SVJT94ji1RwIOA2PNfUqvvHOvtmfMwJ3a3mKike7VTdr-mIkxwdxxS3AgMBAAECgYBWwswd9HODgoiAuBi9TC4QGMwa1Ga65kdbKe8Qt3VyBiwXVhbWkzZAlazCodHs20Zh6Wha0QHHs01URnF0S0J4JQbbxggH_PyRDdLORZbRijaR2plTk5MKVlKNAPScgzpPhCM6BhZYcMwtcUPgkwgaXX717X0lex_1AVTIm3NlWQJBAPbkdxhzDZrGEykkg5I9c8cY4tfmntXbqwzlf7K7LQwzqi3P3skbP3iifD78X_EshTF2H1Xa5R1zAOysm0M_8DsCQQCIkY6ilBu9nf6EahBTixUJifCWqeaAVDmcFtspUYklU1x0v7GuzDkyKHq1zj9O89y3bFTO1d_jyP1zbhdMxgG1AkAVyS01LBncOXRBYiZ2BhQsUmbZ9FC5wEuEtM-i3t9nvztaFYisKEpeg8AA8tYoeuw55Bl5wvoKsjY3rDd7JK43AkAsvGGY6CNeLc8d2RCYDUtK4KCq-eTDzNNCSkpocokWbQQwSksOE1_5CkGIJJOgbUmz3Ay75Ft-5mv_z-RaBdKxAkAgNKThK71cl-gnUDi_PnFGtNzfj9YghexwNY6A2XZLR_kWJTQD1-NgOjeiDyT4GokLExI229t-_vCLJHAVvthM");
		String data2 = privateDecrypt(data1, privateKey);
		System.out.println("RSA私钥解密后："+data2);
		//再进行DES解密
		byte[] b1 = desDecrypt(Base64.decodeBase64(data2), key);
		System.out.println("DES加密内容："+new String(b1));
	}
	
	/**
     * 转密钥字符串（base64编码）
     *
     * @return
     */
    public String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = Base64.encodeBase64String(keyBytes);
        return s;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }
	
    /**
     * 生成密钥对
     * @param filePath
     */
	public void genKeyPair(String filePath) {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		try {
			// 得到公钥字符串
			String publicKeyString = getKeyString(publicKey);
			// 得到私钥字符串
			String privateKeyString = getKeyString(privateKey);
			// 将密钥对写入到文件
			FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
			FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
			BufferedWriter pubbw = new BufferedWriter(pubfw);
			BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes("UTF-8"), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), "UTF-8");
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }
    
    private byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }
}
