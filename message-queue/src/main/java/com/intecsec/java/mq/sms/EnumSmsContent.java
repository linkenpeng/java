package com.intecsec.java.mq.sms;

/**
 * @author peter.peng
 * @date 2020/9/28
 */
public enum EnumSmsContent {
	REG("1", ""),
	CHECK_PASS("2", ""),
	CHECK_UN_PASS("3", ""),
	;

	private String templateId;
	private String templateContent;

	EnumSmsContent(String templateId, String templateContent) {
		this.templateId = templateId;
		this.templateContent = templateContent;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTemplateContent() {
		return templateContent;
	}
}
