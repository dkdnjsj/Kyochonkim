package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comm.DBConn;
import dto.mdto;

public class mdao {
	
	DBConn comm = new DBConn();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<mdto> searchM_A(int code){//전체 조회
		ArrayList<mdto> arr2 = new ArrayList<>();
			
		String query = "SELECT id, name, address, tel, age, TO_CHAR(reg_date, 'yyyy-MM-dd') FROM B12_BOOKMEMBER";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String tel = rs.getString(4);
				String age = rs.getString(5);
				String reg_date = rs.getString(6);

				
				mdto dto = new mdto(id, name, address, tel, age, reg_date);
				
				arr2.add(dto);
				}
		}catch(SQLException se){
			System.out.println("==== searchM_A query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== searchM_A ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return arr2;
	}
	public ArrayList<mdto> searchM_N(String searchN){//조건 검색
		ArrayList<mdto> arr2 = new ArrayList<>();
				
		String query = "SELECT id, name, address, tel, age, TO_CHAR(reg_date, 'yyyy-MM-dd') FROM B12_BOOKMEMBER WHERE NAME like '%" + searchN + "%'";
		
		try {
			con = comm.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String tel = rs.getString(4);
				String age = rs.getString(5);
				String reg_date = rs.getString(6);
				
				
				mdto dto = new mdto(id, name, address, tel, age, reg_date);
				
				arr2.add(dto);
				}
		}catch(SQLException se){
			System.out.println("==== searchM_N query ERROR ==== " + query);
		}catch(Exception e) {
			System.out.println("==== searchM_N ERROR ====");
		}finally {
			comm.close(con, ps, rs);
		}
		
		return arr2;
	}

}
