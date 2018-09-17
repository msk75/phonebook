package ir.maktab.finalproject.service;

import java.util.List;

import ir.maktab.finalproject.dao.DescribesDaoImpl;
import ir.maktab.finalproject.dto.DescribesDto;

public class DescribesServiceImpl implements DescribesService {
	DescribesDaoImpl dapImp;
	private static DescribesServiceImpl describesServiceImpl = new DescribesServiceImpl();

	private DescribesServiceImpl() {
		dapImp = DescribesDaoImpl.getDescribesDaoImpl();
	}

	public static DescribesServiceImpl getDescribesServiceImpl() {
		return describesServiceImpl;
	}

	@Override
	public List<DescribesDto> getAll() throws Exception {
		try {
			return dapImp.selectAll();
		} catch (Exception e) {
			throw new Exception("Does Not any Data");
		}
	}

	@Override
	public Boolean add(String describes, String UserId) throws Exception {
		try {
			dapImp.insertDescribes(describes, UserId);
			return true;
		} catch (Exception e) {
			throw new Exception("Errror");
		}
	}

}
