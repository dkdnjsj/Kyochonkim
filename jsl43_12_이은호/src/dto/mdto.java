package dto;

public class mdto {
	String id, name, address, tel, age, reg_date;
	
	
	
	public mdto(String id, String name, String address, String tel,  String age, String reg_date) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.reg_date = reg_date;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getTel() {
		return tel;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getAge() {
		return age;
	}
	
	
	
	

}
