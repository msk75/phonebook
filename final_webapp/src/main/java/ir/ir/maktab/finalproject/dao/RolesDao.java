package ir.maktab.finalproject.dao;

import ir.maktab.finalproject.entities.Roles;

public interface RolesDao {
	// select a role from database by that's id
	public Roles selectById(int id);

	// select a role from database by that's name
	public Roles selectByName(String name);

}
