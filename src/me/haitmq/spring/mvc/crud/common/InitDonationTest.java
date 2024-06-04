package me.haitmq.spring.mvc.crud.common;

import me.haitmq.spring.mvc.crud.validation.UniquedDonationCode;

public class InitDonationTest {	
	
	private String name;
	
	@UniquedDonationCode
	private String code;
	
	
	
	public InitDonationTest() {

	}

	public InitDonationTest(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
