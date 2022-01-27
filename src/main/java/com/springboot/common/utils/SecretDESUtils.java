package com.springboot.common.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecretDESUtils {

	// 定义加密算法，有DES、DESede(即3DES)、Blowfish
	private static final String Algorithm = "DESede";

	/**
	 * 加密方法
	 * 
	 * @param src
	 *            源数据的字节数组
	 * @return
	 */
	public static String encrypt(String key, String text) {
		try {

			SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm); // 生成密钥
			Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
			byte[] src = text.getBytes("utf-8");
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
			byte[] result = c1.doFinal(src);
			return Base64.encodeBase64String(result);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 * 
	 * @param src
	 *            密文的字节数组
	 * @return
	 */
	public static String decrypt(String key, String text) {
		try {
			SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
			byte[] result = c1.doFinal(Base64.decodeBase64(text));
			return new String(result, "utf-8");
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/*
	 * 根据字符串生成密钥字节数组
	 * 
	 * @param keyStr 密钥字符串
	 * 
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		String key = "9oyKs7cVo1yYzkuisP9bhA=="; 
		String msg = "3DES加密解密案例";
		System.out.println("【加密前】：" + msg);

		// 加密
		String secretArr = encrypt(key, msg);
		System.out.println("【加密后】：" + secretArr);

		// 解密
		String myMsgArr = decrypt(key, secretArr);
		System.out.println("【解密后】：" + myMsgArr);
	}*/
}
