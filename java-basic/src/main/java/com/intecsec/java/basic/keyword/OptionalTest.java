package com.intecsec.java.basic.keyword;

import com.intecsec.java.vo.Address;
import com.intecsec.java.vo.Country;
import com.intecsec.java.vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Peter.Peng
 * @date 2021/3/4
 */
public class OptionalTest {

	public static void main(String[] args) {
		list();
	}

	public static void map() {
		User user = giveUser();
		String result = Optional.ofNullable(user).map(u -> u.getUid()).orElse("11");
		System.out.println(result);
	}

	public static void predicate() {
		User user = giveUser();
		Optional result = Optional.ofNullable(user).filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
		System.out.println(result.isPresent());
	}

	public static void list() {
		List<User> users = new ArrayList<>();
		User user = users.stream().findFirst().orElse(giveUser());
		System.out.println(user);
	}

	public static void chain() {
		User user = giveUser();

		String result = Optional.ofNullable(user)
				.flatMap(u -> u.getAddress())
				.flatMap(a -> a.getCountry())
				.flatMap(c -> c.getIsoCode())
				.orElse("default");
		System.out.println(result);

		String result2 = Optional.ofNullable(user)
				.flatMap(User::getAddress)
				.flatMap(Address::getCountry)
				.flatMap(Country::getIsoCode)
				.orElse("default");
		System.out.println(result2);
	}

	private static User giveUser() {
		Address address = new Address();
		Country country = new Country();
		country.setIsoCode("abc");
		address.setCountry(country);
		User user = new User("1", "2", "peter", "peter@163.com", address);
		return user;
	}
}
