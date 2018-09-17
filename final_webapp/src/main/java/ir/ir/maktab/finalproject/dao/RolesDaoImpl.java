package ir.maktab.finalproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ir.maktab.finalproject.entities.Roles;

public class RolesDaoImpl implements RolesDao {

	public Roles selectById(int id) {
		Roles role = new Roles();
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		role = (Roles) session.get(Roles.class, id);
		tx.commit();
		session.close();
		return role;
	}

	@Override
	public Roles selectByName(String name) {
		Roles role = new Roles();
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		role = (Roles) session.createQuery("from Roles where name='" + name + "'");
		tx.commit();
		session.close();
		return role;
	}

}
