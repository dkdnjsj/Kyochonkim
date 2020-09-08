package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import comm.DBConn;
import dto.rdto;

public class rdao {
	DBConn comm = new DBConn();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public int insertRent(String r_no, String m_no, String car_no, String rent_date, String sche_return_date) {//등록
		int result = 0;
		String query = "INSERT INTO c12_rent\r\n" + 
				"values('" + r_no + "', '"  + m_no + "', '" + car_no + "', '" + rent_date + "', '" + sche_return_date + "', '')";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("==== insertRent query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== insertRent ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return result;
	}
	
	public int getCheckValue(int attribute, String value){
		int result = 0;
		String query="";
		if(attribute == 1) {
			 query="SELECT COUNT(*) FROM b12_bookmember WHERE id = '" + value + "'";									
		}else if(attribute == 2){
			 query="SELECT COUNT(*) FROM c12_car WHERE no = '" + value + "' AND rent_code = 'y'";
		}else if(attribute == 3) {
			query="SELECT COUNT(*) FROM c12_rent WHERE member_id = '" + value + "'";
		}else if(attribute == 4) {
			query="SELECT COUNT(*) FROM c12_rent WHERE car_no = '" + value + "'";
		}
	
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(SQLException se) {
			System.out.println("==== getCheckValue() query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== getCheckValue() ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return result;
	}
	public String getAutoNo() {
		String AutoNo="";
		String query="SELECT MAX(no) from c12_rent";
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				AutoNo = rs.getString(1);
			}
			
			if(AutoNo.equals("null")) {
				AutoNo ="R001";
			}else {
				String no = AutoNo.substring(1);
				int i = Integer.parseInt(no);
				i = i + 1;
				DecimalFormat df = new DecimalFormat("000");
				String nextNo = df.format((double)i);
				AutoNo = "R" + nextNo;
			}
			
		}catch(SQLException se) {
			System.out.println("==== getAutoNo() query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== getAutoNo() ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}		
		return AutoNo;		
	}
	
	public int rentcodeChange(String no, int res, int not) {
		String query = "";
		if(res == 1 && not == 1) {
			query = "UPDATE C12_car set rent_code ='n' where no ='" + no + "'";
		}else if(res == 1 && not == 2) {
			query = "UPDATE C12_car set rent_code ='y' where no ='" + no + "'";
		}
				int result = 0;
				try {
					con = comm.getConnection();
					ps = con.prepareStatement(query);
					result = ps.executeUpdate();
				}catch(SQLException se) {
					System.out.println("==== rentcodeChange() query ERROR ==== " + query);
				}catch(Exception e) {
					System.out.println("==== rentcodeChange() ERROR ====");
				}finally {
					comm.close(con, ps, rs);
				}
		return result;	
	}
	
	public ArrayList<rdto> searchY(String nocode, int not){//수정, 삭제시 데이터 조회
		ArrayList<rdto> arr = new ArrayList<>();
		String query = "";
		if(not == 1) {
			query = "SELECT no, member_id, car_no, TO_CHAR(rent_date, 'yyyy-MM-dd'),\r\n"
				+"TO_CHAR(sche_return_date, 'yyyy-MM-dd') FROM C12_RENT WHERE car_no = '"+ nocode +"'";
		}else if (not == 2){
			query = "SELECT no, member_id, car_no, TO_CHAR(rent_date, 'yyyy-MM-dd'),\r\n"
					+"TO_CHAR(sche_return_date, 'yyyy-MM-dd'), TO_CHAR(return_date, 'yyyy-MM-dd') FROM C12_RENT WHERE car_no = '"+ nocode +"'";
		}
		
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(not == 1) {
				while(rs.next()) {
				String no = rs.getString(1);
				String member_id = rs.getString(2);
				String car_no = rs.getString(3);
				String rent_date = rs.getString(4);
				String sche_return_date = rs.getString(5);
				
				
				rdto dto = new rdto(no, member_id, car_no, rent_date, sche_return_date);
				
				arr.add(dto);
				}
			}else if(not == 2){
				while(rs.next()) {
					String no = rs.getString(1);
					String member_id = rs.getString(2);
					String car_no = rs.getString(3);
					String rent_date = rs.getString(4);
					String sche_return_date = rs.getString(5);
					String return_date = rs.getString(6);
					
					
					rdto dto = new rdto(no, member_id, car_no, rent_date, sche_return_date, return_date);
					
					arr.add(dto);
					}
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
	
	public int returnC(String member_id, String car_no, String return_date ) {// 반납
		String query = "UPDATE C12_rent set return_date='"+ return_date +"' where\r\n"
				+ "member_id ='" + member_id + "' AND car_no ='"+ car_no +"'";
		
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
	
	public ArrayList<rdto> searchC(int scode, String search){//조건 검색
		ArrayList<rdto> arr = new ArrayList<>();
		String query, query2 = "";
		if(scode == 1) {
			query2 = "WHERE member_id = '"+ search +"'";
		}else if(scode == 9) {
			query = "";
		}
		query = "SELECT no, member_id, car_no, TO_CHAR(rent_date, 'yyyy-MM-dd'),\r\n"
				+ "TO_CHAR(sche_return_date, 'yyyy-MM-dd'), TO_CHAR(return_date, 'yyyy-MM-dd') FROM c12_rent "+ query2 +"";
		
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString(1);
				String member_id = rs.getString(2);
				String car_no = rs.getString(3);
				String rent_date = rs.getString(4);
				String sche_return_date = rs.getString(5);
				String return_date = rs.getString(6);
				
				
				rdto dto = new rdto(no, member_id, car_no, rent_date, sche_return_date, return_date);
				
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
	
	

}
