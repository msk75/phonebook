package ir.maktab.finalproject.dao;

import java.util.List;

import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Users;

public interface UsersDao {
	// insert user in database
	public Users insertUser(Users user);

	// update user in databse
	public Users updateUser(Users user);

	// delete user from database
	public void deleteUser(Users users);

	// get all database and change to userDto for send to client
	public List<UsersDto> selectAll();

	// get a user from database by id
	public Users selectById(String username) throws Exception;

	// send request for login a user in system
	public Integer selectByUserName(String username, String password) throws Exception;

}
