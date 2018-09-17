package ir.maktab.finalproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ir.maktab.finalproject.dao.CallsDao;
import ir.maktab.finalproject.entities.Calls;

public class CallsDaoImpl implements CallsDao {

	SessionFactory sf;
	Session session;
	Transaction tx;
	// create a singleton
	private static CallsDaoImpl callsDaoImpl = new CallsDaoImpl();

	private CallsDaoImpl() {

	}

	public static CallsDaoImpl getCallsDaoImpl() {
		return callsDaoImpl;
	}

	public Calls insertCalls(Calls call) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();

		session.persist(call);
		tx.commit();
		session.close();
		return call;
	}

	public Calls updateCalls(Calls call) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		session.merge(call);
		tx.commit();
		session.close();
		return call;
	}

	public void deleteCalls(Calls call) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		session.delete(call);
		tx.commit();
		session.close();
	}

	public List<Calls> selectAll() {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		List<Calls> list = new ArrayList<>();
		list = (List<Calls>) session.createQuery("from Calls", Calls.class).list();
		tx.commit();
		session.close();
		return list;

	}

	public List<Calls> searchCalls(String[][] str) {
		sf = new Configuration().configure().buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		List<Calls> calls = session.createQuery("from Calls where fname like'%" + str[0][1] + "%' and lname like'%"
				+ str[1][1] + "%'or mobileNumber='" + str[2][1] + "'or phoneNumber='" + str[3][1] + "'or email='"
				+ str[4][1] + "'or address='" + str[5][1] + "'", Calls.class).getResultList();
		tx.commit();
		session.close();
		return calls;

	}

}
