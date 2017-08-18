package util;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {
	
	/**
	 * md5 ����
	 * @param value Ҫ���ܵ�ֵ
	 * @return ���ܺ��ֵ
	 */
	public static String md5Hex(String value){
		return DigestUtils.md5Hex(value);
	}
	
	/**
	 * ����md5����
	 * @param value
	 * @return
	 */
	public static String md5Hex3(String value){
		for (int i = 0; i < 3; i++) {
			value = DigestUtils.md5Hex(value);
		}
		return value;
	}
	
	/**
	 * sha256����
	 * @param value
	 * @return
	 */
	public static String sha256Hex(String value){
		return DigestUtils.sha256Hex(value);
	}
	
	/**
	 * sha512����
	 * @param value
	 * @return
	 */
	public static String sha512Hex(String value){
		return DigestUtils.sha512Hex(value);
	}

}


