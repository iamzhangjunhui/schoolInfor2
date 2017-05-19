package com.cdxy.schoolinforapplication.util;

/**
 * 号码管理类(手机号码/身份证)
 * 
 * @author 7000
 * 
 */
public class NumberCheckUtil {
	/**
	 * 判断是不是手机号码
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return
	 */
	public static boolean isMoibleNumber(String phoneNumber) {
		if (phoneNumber.matches("^[1][3-8]\\d{9}$")) {
			return true;
		} else {
			return false;
		}
	}
}
