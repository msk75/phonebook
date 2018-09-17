package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.entities.Calls;

public interface CallsService {
	public Boolean add(Calls calls) throws Exception;

	public Boolean edit(Calls calls) throws Exception;

	public Boolean remove(Calls calls) throws Exception;

	public List<Calls> getAll();

	public List<Calls> toFind(String[][] str) throws Exception;
}
