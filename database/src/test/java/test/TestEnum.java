package test;

import com.intecsec.java.enums.PaymethodEnum;
import org.junit.Test;


public class TestEnum {
	
	@Test
	public void testPaymethod() {
		int code = 4;
		String toCode = "02";
		PaymethodEnum paymethodEnum = PaymethodEnum.codeOf(code);
		System.out.println(paymethodEnum.getCode());
		System.out.println(paymethodEnum.getCode());
		System.out.println(paymethodEnum.getName());
	}
}
