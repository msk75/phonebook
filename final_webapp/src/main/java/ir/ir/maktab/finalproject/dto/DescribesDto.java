package ir.maktab.finalproject.dto;

public class DescribesDto {
	private int id;
	private String describes;
	private int userId;

	public DescribesDto(int id, String describes, int userId) {
		this.id = id;
		this.describes = describes;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "DescribesDto [id=" + id + ", describes=" + describes + ", userId=" + userId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public int getUser() {
		return userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

}
