package com.intecsec.java.struts2.actions;

import com.intecsec.java.struts2.services.PersonService;

public class PersonAction {

	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public String execute() {
		System.out.println("person action");
		personService.save();
		return "success";
	}
}
