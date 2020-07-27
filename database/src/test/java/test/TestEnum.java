package test;

import com.intecsec.java.enums.PaymethodEnum;
import org.junit.Test;


public class TestEnum {
	
	@Test
	public void testGomsPaymethod() {
		int code = 4;
		String gomsCode = "P012";
		PaymethodEnum paymethodEnum = PaymethodEnum.codeOf(code);
		System.out.println(paymethodEnum.getCode());
		System.out.println(paymethodEnum.getGomsCode());
		System.out.println(paymethodEnum.getName());
	}
}
