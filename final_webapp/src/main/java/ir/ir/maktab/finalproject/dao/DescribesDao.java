package ir.maktab.finalproject.dao;

import java.util.List;

import ir.maktab.finalproject.dto.DescribesDto;
import ir.maktab.finalproject.entities.Describes;

public interface DescribesDao {
	// get all describes from database
	public List<DescribesDto> selectAll();

	// insert a describes in database
	public Describes insertDescribes(String describes, String username) throws Exception;

}
