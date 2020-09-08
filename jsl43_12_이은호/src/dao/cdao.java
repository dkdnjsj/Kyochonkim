package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comm.DBConn;
import dto.cdto;

public class cdao {
	DBConn comm = new DBConn();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public int insertC(String no, String model_name, String car_num, String car_made, String car_made_ym,
			String auto_yn, String opt_yn, String acc_yn, String fuel_ty, String import_dt) {//등록
		int result = 0;
		String query = "insert into c12_car\r\n" + 
				"values('" + no + "', '"  + model_name + "', '" + car_num + "', '" 
				+ car_made + "', '" + car_made_ym + "', '" + auto_yn + "', '" + opt_yn + "', '" + acc_yn 
				+ "', '" + fuel_ty + "', '" + import_dt + "','y')";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("==== insertC query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== insertC ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return result;
	}
	
	public ArrayList<cdto> searchC(int scode, String search){//조건 검색
		ArrayList<cdto> arr = new ArrayList<>();
		String code = "";
		if(scode == 1) {
			code = "model_name";
		}else if(scode == 2) {
			code = "car_number";
		}
		
		String query = "select a.no, a.model_name, a.car_number, b.made_name, a.car_made_ym, a.auto_yn, a.option_yn, a.accident_yn, c.fuel_name, TO_CHAR(a.import_date, 'yyyy-MM-dd'), a.rent_code\r\n" + 
				"from c12_car a, car_common_made b, car_common_fuel c\r\n" + 
				"where a.car_made = b.made_code and a.fuel_type = c.fuel_type and " + code + " like '%" + search + "%'";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString(1);
				String model_name = rs.getString(2);
				String car_num = rs.getString(3);
				String car_made = rs.getString(4);
				String car_made_ym = rs.getString(5);
				String auto_yn = rs.getString(6);
				String opt_yn = rs.getString(7);
				String acc_yn = rs.getString(8);
				String fuel_ty = rs.getString(9);
				String import_dt = rs.getString(10);
				String rent_code = rs.getString(11);
				
				cdto dto = new cdto(no, model_name, car_num, car_made, car_made_ym, auto_yn, opt_yn, acc_yn, fuel_ty, import_dt, rent_code);
				
				arr.add(dto);
				}
		}catch(SQLException se){
			System.out.println("==== searchC query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== searchC ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return arr;
	}
	
	public ArrayList<cdto> searchA(int code){//전체 조회
		ArrayList<cdto> arr = new ArrayList<>();
			
		String query = "select a.no, a.model_name, a.car_number, b.made_name, a.car_made_ym, a.auto_yn, a.option_yn, a.accident_yn, c.fuel_name, TO_CHAR(a.import_date, 'yyyy-MM-dd'), a.rent_code\r\n" + 
				"from c12_car a, car_common_made b, car_common_fuel c where a.car_made = b.made_code and a.fuel_type = c.fuel_type";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString(1);
				String model_name = rs.getString(2);
				String car_num = rs.getString(3);
				String car_made = rs.getString(4);
				String car_made_ym = rs.getString(5);
				String auto_yn = rs.getString(6);
				
				String opt_yn = rs.getString(7);
				String acc_yn = rs.getString(8);
				String fuel_ty = rs.getString(9);
				String import_dt = rs.getString(10);
				String rent_code = rs.getString(11);
				
				cdto dto = new cdto(no, model_name, car_num, car_made, car_made_ym, auto_yn, opt_yn, acc_yn, fuel_ty, import_dt, rent_code);
				
				arr.add(dto);
				}
		}catch(SQLException se){
			System.out.println("==== searchA query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== searchA ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return arr;
	}
	public int updateC(String no, String model_name, String car_num, String car_made, String car_made_ym,
			String auto_yn, String opt_yn, String acc_yn, String fuel_ty, String import_dt) {// 수정
		String query = "UPDATE C12_car set model_name='"+ model_name +"', car_number='"+ car_num +"'\r\n"+
		",car_made='"+ car_made +"', car_made_ym='"+ car_made_ym +"',auto_yn='"+ auto_yn +"', option_yn='"+ opt_yn +"'\r\n" + 
				", accident_yn='"+ acc_yn +"', fuel_type='"+ fuel_ty +"', import_date='"+ import_dt +"' where no ='" + no + "'";
		
		int result = 0;
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("==== updateC query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== updateC ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return result;
		
	}
	public int deleteC(String delcode) { //삭제
		String query ="DELETE FROM C12_car where no ='" + delcode + "'"; 
		int result = 0;
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("==== deleteC query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== deleteC ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return result;
	}
	
	public ArrayList<cdto> searchY(String nocode){//수정, 삭제시 데이터 조회
		ArrayList<cdto> arr = new ArrayList<>();
				
		String query = "select a.no, a.model_name, a.car_number, b.made_name, a.car_made_ym, a.auto_yn, a.option_yn, a.accident_yn, c.fuel_name, TO_CHAR(a.import_date, 'yyyy-MM-dd'), a.rent_code\r\n" + 
				"from c12_car a, car_common_made b, car_common_fuel c\r\n" + 
				"where a.car_made = b.made_code and a.fuel_type = c.fuel_type and a.no = '"+ nocode +"'";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString(1);
				String model_name = rs.getString(2);
				String car_num = rs.getString(3);
				String car_made = rs.getString(4);
				String car_made_ym = rs.getString(5);
				String auto_yn = rs.getString(6);
				String opt_yn = rs.getString(7);
				String acc_yn = rs.getString(8);
				String fuel_ty = rs.getString(9);
				String import_dt = rs.getString(10);
				String rent_code = rs.getString(11);
				
				cdto dto = new cdto(no, model_name, car_num, car_made, car_made_ym, auto_yn, opt_yn, acc_yn, fuel_ty, import_dt, rent_code);
				
				arr.add(dto);
				}
		}catch(SQLException se){
			System.out.println("==== searchY query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== searchY ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return arr;
	}
	
	 

	}

