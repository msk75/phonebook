package ir.maktab.finalproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ir.maktab.finalproject.dto.DescribesDto;
import ir.maktab.finalproject.entities.Describes;
import ir.maktab.finalproject.entities.Users;
import ir.maktab.finalproject.service.UserServiceImpl;
import ir.maktab.finalproject.utilities.ConvertDto;

public class DescribesDaoImpl implements DescribesDao {
	SessionFactory sf;
	Session session;
	Transaction tx;
	// create a singleton
	private static DescribesDaoImpl describesDaoImpl = new DescribesDaoImpl();

	private DescribesDaoImpl() {
	}

	public static DescribesDaoImpl getDescribesDaoImpl() {
		return describesDaoImpl;
	}

	@Override
	public List<DescribesDto> selectAll() {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		ConvertDto cd = new ConvertDto();
		List<Describes> list = session.createQuery("from Describes", Describes.class).list();
		tx.commit();
		session.close();
		return cd.convertToDescribesDto(list);
	}

	@Override
	public Describes insertDescribes(String describes, String username) throws Exception {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		UserServiceImpl userImpl = UserServiceImpl.getUserServiceImpl();
		Users users = userImpl.getById(username);
		Describes describes2 = new Describes();
		describes2.setDescri(describes);
		describes2.setUser(users);
		session.persist(describes2);
		tx.commit();
		session.close();
		return describes2;
	}
}
