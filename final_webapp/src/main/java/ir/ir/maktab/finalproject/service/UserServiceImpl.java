package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.dao.RolesDaoImpl;
import ir.maktab.finalproject.dao.UserDaoImpl;
import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Roles;
import ir.maktab.finalproject.entities.Users;
import ir.maktab.finalproject.utilities.JavaMd5Hash;

public class UserServiceImpl implements UsersService {

	UserDaoImpl daoImpl;
	public static UserServiceImpl userServiceImpl = new UserServiceImpl();

	private UserServiceImpl() {
		daoImpl = UserDaoImpl.getUserDaoImpl();
	}

	public static UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	@SuppressWarnings("static-access")
	public Boolean add(Users users) throws Exception {
		JavaMd5Hash md = new JavaMd5Hash();
		RolesDaoImpl rd = new RolesDaoImpl();
		try {
			Roles r = rd.selectById(1);
			users.setRole(r);
			users.setPassword(md.md5(users.getPassword()));
			daoImpl.insertUser(users);
			return true;
		} catch (Exception e) {
			throw new Exception("Error, Duplicate data");
		}

	}

	public Boolean edit(Users users) throws Exception {
		try {
			daoImpl.updateUser(users);
			return true;
		} catch (Exception e) {
			throw new Exception("Error,Duplicate Data");
		}
	}

	public List<UsersDto> getAll() throws Exception {
		try {

			return daoImpl.selectAll();
		} catch (Exception e) {
			throw new Exception("Does Not any Data");
		}
	}

	public Boolean remove(Users users) throws Exception {
		try {
			daoImpl.deleteUser(users);
			return true;
		} catch (Exception e) {
			throw new Exception("Error, Does not exist any data");
		}
	}

	@Override
	public Integer loginUsers(String user, String pass) throws Exception {
		try {
			return daoImpl.selectByUserName(user, pass);
		} catch (Exception e) {
			throw new Exception("This User Does not exist");
		}
	}

	@Override
	public Users getById(String username) throws Exception {
		try {
			return daoImpl.selectById(username);
		} catch (Exception e) {
			throw new Exception("This User Does Not Exist");
		}

	}

}
