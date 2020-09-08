package dto;

public class rdto {
	String no, member_id, car_no, rent_date, sche_return_date, return_date;

	public String getNo() {
		return no;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getCar_no() {
		return car_no;
	}

	public String getRent_date() {
		return rent_date;
	}

	public String getSche_return_date() {
		return sche_return_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public rdto(String no, String member_id, String car_no, String rent_date, String sche_return_date,
			String return_date) {
		super();
		this.no = no;
		this.member_id = member_id;
		this.car_no = car_no;
		this.rent_date = rent_date;
		this.sche_return_date = sche_return_date;
		this.return_date = return_date;
	}
	
	public rdto(String no, String member_id, String car_no, String rent_date, String sche_return_date) {
		super();
		this.no = no;
		this.member_id = member_id;
		this.car_no = car_no;
		this.rent_date = rent_date;
		this.sche_return_date = sche_return_date;
	}
	
	

}
