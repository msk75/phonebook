package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.dao.CallsDaoImpl;
import ir.maktab.finalproject.entities.Calls;

public class CallsServiceImpl implements CallsService {

	CallsDaoImpl call;
	private static CallsServiceImpl callsServiceImpl = new CallsServiceImpl();

	private CallsServiceImpl() {
		call = CallsDaoImpl.getCallsDaoImpl();
	}

	public static CallsServiceImpl getCallsServiceImpl() {
		return callsServiceImpl;
	}

	public Boolean add(Calls calls) throws Exception {

		try {
			call.insertCalls(calls);
			return true;
		} catch (Exception e) {
			throw new Exception("Error, Duplicate Data");
		}
	}

	public Boolean edit(Calls calls) throws Exception {
		try {
			call.updateCalls(calls);
			return true;
		} catch (Exception e) {
			throw new Exception("Error, Duplicate Data");
		}
	}

	public Boolean remove(Calls calls) throws Exception {
		try {
			call.deleteCalls(calls);
			return true;
		} catch (Exception e) {
			throw new Exception("Does Not Exist any data");
		}
	}

	public List<Calls> getAll() {
		return call.selectAll();
	}

	public List<Calls> toFind(String[][] str) throws Exception {
		try {
			return call.searchCalls(str);
		} catch (Exception e) {
			throw new Exception("Does not Exist this search");
		}
	}

}
