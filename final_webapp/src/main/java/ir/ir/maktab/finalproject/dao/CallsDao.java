package ir.maktab.finalproject.dao;

import java.util.List;

import ir.maktab.finalproject.entities.Calls;

public interface CallsDao {
	// insert call in database
	public Calls insertCalls(Calls call);

	// update call in database
	public Calls updateCalls(Calls call);

	// delete call from database
	public void deleteCalls(Calls call);

	// get all calls from database
	public List<Calls> selectAll();

	// search for a call in database
	public List<Calls> searchCalls(String[][] str);

}
