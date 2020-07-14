package com.intecsec.java.enums;

public enum PaymethodEnum {
	ALI_PAY(4, "P001", "支付宝"),
	WEIXIN_PAY(5, "P012", "微信"),
	UNION_PAY(6, "P005", "银联");
	
	private int code;
	private String gomsCode;
	private String name;
	
	private PaymethodEnum(int code, String gomsCode, String name) {
		this.code = code;
		this.gomsCode = gomsCode;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getGomsCode() {
		return gomsCode;
	}

	public void setGomsCode(String gomsCode) {
		this.gomsCode = gomsCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static PaymethodEnum codeOf(int index) {
        for (PaymethodEnum code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }
	
	public static PaymethodEnum gomsCodeOf(String key) {
        for (PaymethodEnum code : values()) {
            if (code.getGomsCode() == key) {
                return code;
            }
        }
        return null;
    }	
}
