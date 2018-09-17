package ir.maktab.finalproject.utilities;

import java.util.ArrayList;
import java.util.List;

import ir.maktab.finalproject.dto.DescribesDto;
import ir.maktab.finalproject.dto.UsersDto;
import ir.maktab.finalproject.entities.Describes;
import ir.maktab.finalproject.entities.Users;

public class ConvertDto {
	// convert users to userdto for send to client
	public List<UsersDto> convertToUsersDto(List<Users> list) {
		List<UsersDto> usersDto = new ArrayList<>();
		for (int i = 0; i < list.size(); i++)
			usersDto.add(new UsersDto(list.get(i).getId(), list.get(i).getFname(), list.get(i).getLname(),
					list.get(i).getUsername(), list.get(i).getPassword(), list.get(i).getEmail(),
					list.get(i).getAddress(), list.get(i).getRole().getName()));
		return usersDto;
	}

	// convert describes to describesdto for send to client
	public List<DescribesDto> convertToDescribesDto(List<Describes> describes) {
		List<DescribesDto> describesDtos = new ArrayList<>();
		for (int i = 0; i < describes.size(); i++)
			describesDtos.add(new DescribesDto(describes.get(i).getId(), describes.get(i).getDescri(),
					describes.get(i).getUser().getId()));
		return describesDtos;
	}

}
