package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.dto.DescribesDto;

public interface DescribesService {
	public List<DescribesDto> getAll() throws Exception;

	public Boolean add(String describes, String UserId) throws Exception;

}
