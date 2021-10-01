package com.intecsec.java.enums;

public enum PaymethodEnum {
	ALI_PAY(1, "01", "支付宝"),
	WEIXIN_PAY(2, "02", "微信"),
	UNION_PAY(3, "03", "银联");
	
	private int code;
	private String toCode;
	private String name;
	
	private PaymethodEnum(int code, String toCode, String name) {
		this.code = code;
		this.toCode = toCode;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getToCode() {
		return toCode;
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
	
	public static PaymethodEnum toCodeOf(String key) {
        for (PaymethodEnum code : values()) {
            if (code.getToCode().equals(key)) {
                return code;
            }
        }
        return null;
    }	
}
