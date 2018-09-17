package ir.maktab.finalproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Users;
import ir.maktab.finalproject.utilities.ConvertDto;
import ir.maktab.finalproject.utilities.JavaMd5Hash;

public class UserDaoImpl implements UsersDao {

	SessionFactory sf;
	Session session;
	Transaction tx;
	// create a singleton
	private static UserDaoImpl userDaoImpl = new UserDaoImpl();

	private UserDaoImpl() {

	}

	public static UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public Users insertUser(Users user) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
		return user;
	}

	public Users updateUser(Users user) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		session.merge(user);
		tx.commit();
		session.close();
		return user;
	}

	public void deleteUser(Users user) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}

	public List<UsersDto> selectAll() {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		List<Users> list = new ArrayList<>();
		ConvertDto cd = new ConvertDto();
		list = session.createQuery("from Users", Users.class).list();
		tx.commit();
		session.close();
		return cd.convertToUsersDto(list);
	}

	public Users selectById(String username) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		Users users = new Users();
		users = (Users) session.createQuery("from Users where username='" + username + "'").uniqueResult();
		tx.commit();
		session.close();
		return users;
	}

	public Integer selectByUserName(String username, String password) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		int ans = 0;
		JavaMd5Hash md5 = new JavaMd5Hash();
		Users users = new Users();
		users = (Users) session.createQuery("from Users where username='" + username + "'").uniqueResult();
		tx.commit();
		session.close();
		if (users.getUsername().equalsIgnoreCase(username) && users.getPassword().equalsIgnoreCase(md5.md5(password)))
			ans = users.getRole().getId();

		return ans;
	}

}
