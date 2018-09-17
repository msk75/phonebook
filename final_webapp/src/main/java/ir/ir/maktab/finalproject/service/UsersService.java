package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Users;

public interface UsersService {
	public Boolean add(Users users) throws Exception;

	public Boolean edit(Users users) throws Exception;

	public Boolean remove(Users users) throws Exception;

	public List<UsersDto> getAll() throws Exception;

	public Users getById(String username) throws Exception;

	public Integer loginUsers(String user, String pass) throws Exception;

}
