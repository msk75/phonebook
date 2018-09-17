package ir.maktab.finalproject.junit;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import ir.maktab.finalproject.dto.DescribesDto;
import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Calls;
import ir.maktab.finalproject.entities.Users;
import ir.maktab.finalproject.service.CallsServiceImpl;
import ir.maktab.finalproject.service.DescribesServiceImpl;
import ir.maktab.finalproject.service.UserServiceImpl;

public class UnitTesting {
	@Test
	public void test1() throws Exception {
		UserServiceImpl userServiceImpl = UserServiceImpl.getUserServiceImpl();
		Users users = new Users();
		users.setAddress("Gorgan & Tehran");
		users.setEmail("ms.keykha75@gmail.com");
		users.setUsername("aliahlavi");
		users.setPassword("9h9898");

		userServiceImpl.add(users);
		List<UsersDto> list = userServiceImpl.getAll();
		assertNotNull(list);

	}

	@Test
	public void test2() throws Exception {
		CallsServiceImpl callsServiceImpl = CallsServiceImpl.getCallsServiceImpl();
		Calls call = new Calls();
		call.setFname("saeed");
		call.setLname("dehghan");
		call.setAddress("Gorgan");
		call.setPhoneNumber("09358675100");
		call.setMobileNumber("01734264415");
		call.setEmail("saeed.dehghan@gmail.com");
		callsServiceImpl.add(call);
		List<Calls> list = callsServiceImpl.getAll();
		assertNotNull(list);
	}

	@Test
	public void test3() throws Exception {
		DescribesServiceImpl describesServiceImpl = DescribesServiceImpl.getDescribesServiceImpl();
		UserServiceImpl userServiceImpl = UserServiceImpl.getUserServiceImpl();
		Users users = new Users();
		users.setUsername("msk75");
		users.setPassword("13750569");

		userServiceImpl.add(users);
		describesServiceImpl.add("create database", "1");
		List<DescribesDto> describes = describesServiceImpl.getAll();
		assertNotNull(describes);
	}

}
