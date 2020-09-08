package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.cdao;
import dao.mdao;
import dao.rdao;
import dto.cdto;
import dto.mdto;
import dto.rdto;

public class car {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cdao dao = new cdao();
		mdao mdao = new mdao();
		rdao rdao = new rdao();
		
		ArrayList<cdto> arr = new ArrayList<>();
		ArrayList<mdto> arr2 = new ArrayList<>();
		ArrayList<rdto> arr3 = new ArrayList<>();
		
		
		int code = 0;
		int ccode = 0;
		String yn = "";
		do {
			System.out.print("차량 관리[1], 회원 관리[2], 렌트 관리[3], 종료[0] ? ");				
			code = sc.nextInt();
			if(code == 1) { // 차량 관리 부분
				do {
					System.out.print("차량 등록[1], 차량 조회[2], 차량 수정[3], 차량 삭제[4], 이전[0] ?");
					ccode = sc.nextInt();
					if(ccode == 1) {//등록
						System.out.print("차량 아이디를 입력해주세요 [C---]: ");
						String no = sc.next();
						System.out.print("차량 이름을 입력해주세요 : ");
						String model_name = sc.next();
						System.out.print("차량번호를 입력해주세요 : ");
						String car_num = sc.next();
						System.out.print("현대[10], 기아[20], 삼성[30], 쌍용[40], Chevrolet[50], Benz[60], BMW[70], Audi[80], Tesla[90] ");
						System.out.print("제조사를 선택 해주세요 : ");
						String car_made = sc.next();
						System.out.print("제조연도를 입력해주세요 : ");
						String car_made_ym = sc.next();
						System.out.print("자동변속기 여부를 입력해주세요 [Y/N] : ");
						String auto_yn = sc.next();
						System.out.print("옵션적용 여부를 입력해주세요 [Y/N] : ");
						String opt_yn = sc.next();
						System.out.print("사고여부를 입력해주세요 [Y/N] : ");
						String acc_yn = sc.next();
						System.out.println("가솔린[1], 디젤[2], LPG[3], 전기[4] ");
						System.out.print("연료타입을 선택해주세요 : ");
						String fuel_ty = sc.next();
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String date = simpleDateFormat.format(new Date());
						System.out.println(date);
						String import_dt = date;		
						
						int result = dao.insertC(no, model_name, car_num, car_made, car_made_ym, auto_yn, opt_yn, acc_yn, fuel_ty, import_dt);
						
						if(result == 1) {
							System.out.println(" ==== 등록에 성공했습니다 ==== ");
						}else {
							System.out.println(" ==== 등록에 실패했습니다 ==== ");
						}
					}else if(ccode == 2) {//조건 검색
						int scode = 0;
						do {
							System.out.print(" 차량 이름 조회[1], 차량 번호 조회[2], 차량 전체 조회[9], 이전[0]? ");
							scode = sc.nextInt();
							if(scode == 1) {
								System.out.print(" 차량 이름을 입력해주세요 : ");
								String search = sc.next();
								
								arr = dao.searchC(scode, search);
							}else if(scode == 2) {
								System.out.print(" 차량 아이디를 입력해주세요 [C---]: ");
								String search = sc.next();
								
								arr = dao.searchC(scode, search);
							}else if(scode == 9) {
								arr = dao.searchA(ccode);
								if(arr.size() == 0) {
									System.out.println("==== 검색 기록이 없습니다 ====");
								}else {
									System.out.println("=================================================================================================================");
									System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
									System.out.println("=================================================================================================================");
									for(int k = 0; k < arr.size(); k++) {
										System.out.print(arr.get(k).getNo() + "\t");
										System.out.print(arr.get(k).getModel_name() + "\t");
										System.out.print(arr.get(k).getCar_num() + "\t");
										System.out.print(arr.get(k).getCar_made() + "\t");
										System.out.print(arr.get(k).getCar_made_ym() + "\t");
										System.out.print(arr.get(k).getAuto_yn() + "\t");
										System.out.print(arr.get(k).getOpt_yn() + "\t");
										System.out.print(arr.get(k).getAcc_yn() + "\t");
										System.out.print(arr.get(k).getFuel_ty() + "\t");
										System.out.print(arr.get(k).getImport_dt() + "\t");
										System.out.print(arr.get(k).getRent_code() + "\n");
									}
								}
								
							}
							
							if(scode != 0 && scode !=9) {
								if(arr.size() == 0) {
									System.out.println("==== 검색 기록이 없습니다 ====");
								}else {
									System.out.println("=================================================================================================================");
									System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
									System.out.println("=================================================================================================================");
									for(int k = 0; k < arr.size(); k++) {
										System.out.print(arr.get(k).getNo() + "\t");
										System.out.print(arr.get(k).getModel_name() + "\t");
										System.out.print(arr.get(k).getCar_num() + "\t");
										System.out.print(arr.get(k).getCar_made() + "\t");
										System.out.print(arr.get(k).getCar_made_ym() + "\t");
										System.out.print(arr.get(k).getAuto_yn() + "\t");
										System.out.print(arr.get(k).getOpt_yn() + "\t");
										System.out.print(arr.get(k).getAcc_yn() + "\t");
										System.out.print(arr.get(k).getFuel_ty() + "\t");
										System.out.print(arr.get(k).getImport_dt() + "\t");
										System.out.print(arr.get(k).getRent_code() + "\n");
									}
								}
							}
						} while(scode != 0);
					}else if(ccode == 3) {//수정
						arr = dao.searchA(ccode);
							System.out.println("=================================================================================================================");
							System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
							System.out.println("=================================================================================================================");
							for(int k = 0; k < arr.size(); k++) {
								System.out.print(arr.get(k).getNo() + "\t");
								System.out.print(arr.get(k).getModel_name() + "\t");
								System.out.print(arr.get(k).getCar_num() + "\t");
								System.out.print(arr.get(k).getCar_made() + "\t");
								System.out.print(arr.get(k).getCar_made_ym() + "\t");
								System.out.print(arr.get(k).getAuto_yn() + "\t");
								System.out.print(arr.get(k).getOpt_yn() + "\t");
								System.out.print(arr.get(k).getAcc_yn() + "\t");
								System.out.print(arr.get(k).getFuel_ty() + "\t");
								System.out.print(arr.get(k).getImport_dt() + "\t");
								System.out.print(arr.get(k).getRent_code() + "\n");
							}
						System.out.print("수정할 등록 아이디를 입력하세요 [C---]: ");
						String no = sc.next();
							arr = dao.searchY(no);
							if(arr.size() != 0) {
								System.out.println("=================================================================================================================");
								System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
								System.out.println("=================================================================================================================");
									for(int k = 0; k < arr.size(); k++) {
										System.out.print(arr.get(k).getNo() + "\t");
										System.out.print(arr.get(k).getModel_name() + "\t");
										System.out.print(arr.get(k).getCar_num() + "\t");
										System.out.print(arr.get(k).getCar_made() + "\t");
										System.out.print(arr.get(k).getCar_made_ym() + "\t");
										System.out.print(arr.get(k).getAuto_yn() + "\t");
										System.out.print(arr.get(k).getOpt_yn() + "\t");
										System.out.print(arr.get(k).getAcc_yn() + "\t");
										System.out.print(arr.get(k).getFuel_ty() + "\t");
										System.out.print(arr.get(k).getImport_dt() + "\t");
										System.out.print(arr.get(k).getRent_code() + "\n");
										System.out.print("수정할 데이터가 맞습니까? [Y/N] : ");
										yn = sc.next();
										}
									}else {
										System.out.println(" ==== 유효하지 않은 번호 입니다  ==== ");
									}
								
						
						if(yn.equals("y")||yn.equals("Y")||yn.equals("ㅛ")) {
							System.out.print("차량 이름을 입력해주세요 : ");
							String model_name = sc.next();
							System.out.print("차량의 번호를 입력해주세요 : ");
							String car_num = sc.next();
							System.out.println("현대[10], 기아[20], 삼성[30], 쌍용[40], Chevrolet[50], Benz[60], BMW[70], Audi[80], Tesla[90] ");
							System.out.print("제조사를 선택 해주세요 : ");
							String car_made = sc.next();
							System.out.print("제조연도를 입력해주세요 : ");
							String car_made_ym = sc.next();
							System.out.print("자동변속기 여부를 입력해주세요 [Y/N] : ");
							String auto_yn = sc.next();
							System.out.print("옵션적용 여부를 입력해주세요 [Y/N] : ");
							String opt_yn = sc.next();
							System.out.print("사고여부를 입력해주세요 [Y/N] : ");
							String acc_yn = sc.next();
							System.out.println("가솔린[1], 디젤[2], LPG[3], 전기[4] ");
							System.out.print("연료타입을 선택해주세요 : ");
							String fuel_ty = sc.next();
							String pattern = "yyyy-MM-dd";
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
							String date = simpleDateFormat.format(new Date());
							System.out.println("등록 날짜는 "+ date +" 입니다.");
							String import_dt = date;
							int result = dao.updateC(no, model_name, car_num, car_made, car_made_ym, auto_yn, opt_yn, acc_yn, fuel_ty, import_dt);
							
								if(result == 1) {
									System.out.println(" ==== 수정에 성공하였습니다 ==== ");
									arr = dao.searchA(ccode);
									System.out.println("=================================================================================================================");
									System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
									System.out.println("=================================================================================================================");
									for(int k = 0; k < arr.size(); k++) {
										System.out.print(arr.get(k).getNo() + "\t");
										System.out.print(arr.get(k).getModel_name() + "\t");
										System.out.print(arr.get(k).getCar_num() + "\t");
										System.out.print(arr.get(k).getCar_made() + "\t");
										System.out.print(arr.get(k).getCar_made_ym() + "\t");
										System.out.print(arr.get(k).getAuto_yn() + "\t");
										System.out.print(arr.get(k).getOpt_yn() + "\t");
										System.out.print(arr.get(k).getAcc_yn() + "\t");
										System.out.print(arr.get(k).getFuel_ty() + "\t");
										System.out.print(arr.get(k).getImport_dt() + "\t");
										System.out.print(arr.get(k).getRent_code() + "\n");
									}
								}else {
									System.out.println(" ==== 수정에 실패하였습니다 ==== ");
								}
							}else {
								System.out.println(" ==== 수정에 실패하였습니다 ==== ");
							}
					
					}else if(ccode == 4) {//삭제
						arr = dao.searchA(ccode);
						System.out.println("=================================================================================================================");
							System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
							System.out.println("=================================================================================================================");
							for(int k = 0; k < arr.size(); k++) {
								System.out.print(arr.get(k).getNo() + "\t");
								System.out.print(arr.get(k).getModel_name() + "\t");
								System.out.print(arr.get(k).getCar_num() + "\t");
								System.out.print(arr.get(k).getCar_made() + "\t");
								System.out.print(arr.get(k).getCar_made_ym() + "\t");
								System.out.print(arr.get(k).getAuto_yn() + "\t");
								System.out.print(arr.get(k).getOpt_yn() + "\t");
								System.out.print(arr.get(k).getAcc_yn() + "\t");
								System.out.print(arr.get(k).getFuel_ty() + "\t");
								System.out.print(arr.get(k).getImport_dt() + "\t");
								System.out.print(arr.get(k).getRent_code() + "\n");
							}
							
						System.out.println("삭제할 차량 아이디를 입력하세요");
						String no = sc.next();				
							arr = dao.searchY(no);
							if(arr.size() != 0) {
								System.out.println("=================================================================================================================");
								System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
								System.out.println("=================================================================================================================");
									for(int k = 0; k < arr.size(); k++) {
										System.out.print(arr.get(k).getNo() + "\t");
										System.out.print(arr.get(k).getModel_name() + "\t");
										System.out.print(arr.get(k).getCar_num() + "\t");
										System.out.print(arr.get(k).getCar_made() + "\t");
										System.out.print(arr.get(k).getCar_made_ym() + "\t");
										System.out.print(arr.get(k).getAuto_yn() + "\t");
										System.out.print(arr.get(k).getOpt_yn() + "\t");
										System.out.print(arr.get(k).getAcc_yn() + "\t");
										System.out.print(arr.get(k).getFuel_ty() + "\t");
										System.out.print(arr.get(k).getImport_dt() + "\t");
										System.out.print(arr.get(k).getRent_code() + "\n");
										System.out.print("삭제할 데이터가 맞습니까? [Y/N] ");								
										yn = sc.next();
										}
									}else {
										System.out.println(" ==== 유효하지 않은 번호 입니다. ==== ");
									}
						if(yn.equals("y")||yn.equals("Y")||yn.equals("ㅛ")) {
							int result = dao.deleteC(no);
						
						if(result == 1) {
							System.out.println(" ==== 삭제에 성공하였습니다 ==== ");
							arr = dao.searchA(ccode);
							System.out.println("=================================================================================================================");
							System.out.println("아이디\t차량 이름\t차량 번호\t제조사\t연식\t자동수동\t옵션\t사고이력\t연료\t기입날짜\t\t대여 가능 여부");
							System.out.println("=================================================================================================================");
							for(int k = 0; k < arr.size(); k++) {
								System.out.print(arr.get(k).getNo() + "\t");
								System.out.print(arr.get(k).getModel_name() + "\t");
								System.out.print(arr.get(k).getCar_num() + "\t");
								System.out.print(arr.get(k).getCar_made() + "\t");
								System.out.print(arr.get(k).getCar_made_ym() + "\t");
								System.out.print(arr.get(k).getAuto_yn() + "\t");
								System.out.print(arr.get(k).getOpt_yn() + "\t");
								System.out.print(arr.get(k).getAcc_yn() + "\t");
								System.out.print(arr.get(k).getFuel_ty() + "\t");
								System.out.print(arr.get(k).getImport_dt() + "\t");
								System.out.print(arr.get(k).getRent_code() + "\n");
							}
						}else {
							System.out.println(" ==== 삭제 실패 ==== ");
						}
						}else {
							System.out.println(" ==== 삭제 실패 ==== ");
						}
						
					}
			}while(ccode != 0);
				
			}else if(code == 2) { // 회원 관리 부분
				int mscode;
				do {
					System.out.print(" 회원 이름 검색[1], 회원 전체 조회 [9], 이전[0]? ");
					mscode = sc.nextInt();
					if(mscode == 1){
						System.out.print(" 회원님의 이름을 입력해주세요 : ");
						String searchN = sc.next();
						
						arr2 = mdao.searchM_N(searchN);
					}else if(mscode == 9){
						arr2 = mdao.searchM_A(code);
						if(arr2.size() == 0) {
							System.out.println(" 검색 기록 없음 ");
						}else {
							
							System.out.println("=================================================================================================================");
							System.out.println("아이디\t이름\t주소\t전화번호\t\t나이\t등록날짜");
							System.out.println("=================================================================================================================");
							for(int k = 0; k < arr2.size(); k++) {
								System.out.print(arr2.get(k).getId() + "\t");
								System.out.print(arr2.get(k).getName() + "\t");
								System.out.print(arr2.get(k).getAddress() + "\t");
								System.out.print(arr2.get(k).getTel() + "\t");
								System.out.print(arr2.get(k).getAge() + "\t");
								System.out.print(arr2.get(k).getReg_date() + "\n");
							}
						}
					}
					
					if(mscode != 0 && mscode !=9) {
						if(arr2.size() == 0) {
							System.out.println("검색 기록 없음");
						}else {
							System.out.println("=================================================================================================================");
							System.out.println("아이디\t이름\t주소\t전화번호\t\t나이\t등록날짜");
							System.out.println("=================================================================================================================");
							for(int k = 0; k < arr2.size(); k++) {
								System.out.print(arr2.get(k).getId() + "\t");
								System.out.print(arr2.get(k).getName() + "\t");
								System.out.print(arr2.get(k).getAddress() + "\t");
								System.out.print(arr2.get(k).getTel() + "\t");
								System.out.print(arr2.get(k).getAge() + "\t");
								System.out.print(arr2.get(k).getReg_date() + "\n");
							}
						}
					}
				} while(mscode != 0);
				
			}else if(code == 3) {
				int rcode = 0;
				do {						
					System.out.println(" ==== 차량 대여 [1], 차량 반납[2], 대여 정보 조회 [3], 이전[0]? ==== ");
					rcode = sc.nextInt();
					if(rcode == 1){
						int valuecheck = 0;
						String m_no = "";
						String car_no = "";
						
						do {
							System.out.print("회원님의 회원 아이디를 입력해주세요 [B---]: ");
							m_no = sc.next();
							valuecheck = rdao.getCheckValue(1, m_no);
							
							if(valuecheck == 0) {
								System.out.println("==== 회원 아이디 입력오류 입니다 ====");
							}
						}while(valuecheck == 0);
								
						do {
							System.out.print("렌트하실 차량 아이디를 입력해주세요 [C---]: ");
							car_no = sc.next();
							valuecheck = rdao.getCheckValue(2, car_no);
							
							if(valuecheck == 0) {
								System.out.println("==== 차량 아이디 입력오류 입니다 ====");
							}					
						}while(valuecheck == 0);
						
						String r_no = rdao.getAutoNo();
						System.out.print("대여 날짜를 입력해주세요 [YY-MM-DD] : ");
						String rent_date = sc.next();
						System.out.print("반납일을 입력해주세요 [YY-MM-DD] : ");
						String sche_return_date = sc.next();
						
						int result = rdao.insertRent(r_no, m_no, car_no, rent_date, sche_return_date);
						
						if(result == 1) {
							System.out.println(" ==== 대여에 성공했습니다 ==== ");
							rdao.rentcodeChange(car_no, result, 1);
							arr3 = rdao.searchY(car_no, 1);
							System.out.println("=================================================================================================================");
							System.out.println("대여 번호\t회원 번호\t차량 번호\t대여 날짜\t\t예정 날짜\t");
							System.out.println("=================================================================================================================");														
							for(int k = 0; k < arr3.size(); k++) {
								System.out.print(arr3.get(k).getNo() + "\t");
								System.out.print(arr3.get(k).getMember_id() + "\t");
								System.out.print(arr3.get(k).getCar_no() + "\t");
								System.out.print(arr3.get(k).getRent_date() + "\t");
								System.out.print(arr3.get(k).getSche_return_date() + "\n");
							}				
						}else {
							System.out.println(" ==== 대여에 실패했습니다 ==== ");
						}					
					}else if(rcode == 2){
						int valuecheck1 = 0;
						String m_no = "";
						String car_no = "";
						
						do {
							System.out.print("회원님의 회원 아이디를 입력해주세요 [B---]: ");
							m_no = sc.next();
							valuecheck1 = rdao.getCheckValue(3, m_no);
							
							if(valuecheck1 == 0) {
								System.out.println("==== 입력하신 회원의 대여기록이 존재하지 않습니다  ====");
							}						
						}while(valuecheck1 == 0);
						
						do {
							System.out.print("렌트하실 차량 아이디를 입력해주세요 [C---]: ");
							car_no = sc.next();
							valuecheck1 = rdao.getCheckValue(4, car_no);
							
							if(valuecheck1 == 0) {
								System.out.println("==== 입력하신 차량의 대여기록이 존재하지 않습니다 ====");
							}					
						}while(valuecheck1 == 0);
						
						String pattern = "yyyy-MM-dd";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
						String date = simpleDateFormat.format(new Date());
						System.out.println("반납 날짜는 "+ date +" 입니다.");
						String return_date = date;
						
						
						
						int result = rdao.returnC(m_no, car_no, return_date);
						
						if(result == 1) {
							System.out.println(" ==== 반납에 성공했습니다 ==== ");
							rdao.rentcodeChange(car_no, result, 2);
						arr3 = rdao.searchY(car_no, 2);
						System.out.println("=================================================================================================================");
						System.out.println("대여 번호\t회원 번호\t차량 번호\t대여 날짜\t\t예정 날짜\t\t반납날짜");
						System.out.println("=================================================================================================================");														
							for(int k = 0; k < arr3.size(); k++) {
								System.out.print(arr3.get(k).getNo() + "\t");
								System.out.print(arr3.get(k).getMember_id() + "\t");
								System.out.print(arr3.get(k).getCar_no() + "\t");
								System.out.print(arr3.get(k).getRent_date() + "\t");
								System.out.print(arr3.get(k).getSche_return_date() + "\t");
								System.out.print(arr3.get(k).getReturn_date() + "\n");
							}
						}else {
							System.out.println(" ==== 반납에 실패했습니다 ==== ");
						}
						
						
						
						
					}else if(rcode == 3){
						int rscode = 0;
						String search = "";
						do {
							System.out.println(" ==== 회원 아이디 조회 [1], 전체 조회 [9], 이전[0]? ==== ");
							rscode = sc.nextInt();
							if(rscode == 1){
								System.out.print(" ==== 검색할 회원 아이디를 입력해주세요 [B---]: ==== ");
								search = sc.next();
								arr3 = rdao.searchC(rscode, search);
								if(arr3.size() == 0) {
									System.out.println(" 검색 기록 없음 ");
								}else {
									System.out.println("=================================================================================================================");
									System.out.println("대여 번호\t회원 번호\t차량 번호\t대여 날짜\t\t예정 날짜\t\t반납날짜");
									System.out.println("=================================================================================================================");														
										for(int k = 0; k < arr3.size(); k++) {
											System.out.print(arr3.get(k).getNo() + "\t");
											System.out.print(arr3.get(k).getMember_id() + "\t");
											System.out.print(arr3.get(k).getCar_no() + "\t");
											System.out.print(arr3.get(k).getRent_date() + "\t");
											System.out.print(arr3.get(k).getSche_return_date() + "\t");
											System.out.print(arr3.get(k).getReturn_date() + "\n");
										}
								}								
							}else if(rscode == 9) {
								arr3 = rdao.searchC(rscode, "");	
								System.out.println("=================================================================================================================");
								System.out.println("대여 번호\t회원 번호\t차량 번호\t대여 날짜\t\t예정 날짜\t\t반납날짜");
								System.out.println("=================================================================================================================");														
									for(int k = 0; k < arr3.size(); k++) {
										System.out.print(arr3.get(k).getNo() + "\t");
										System.out.print(arr3.get(k).getMember_id() + "\t");
										System.out.print(arr3.get(k).getCar_no() + "\t");
										System.out.print(arr3.get(k).getRent_date() + "\t");
										System.out.print(arr3.get(k).getSche_return_date() + "\t");
										System.out.print(arr3.get(k).getReturn_date() + "\n");
									}
								
							}
						}while(rscode != 0);													
					}						
				}while(rcode != 0);
				
				}else if(code != 0){
					System.out.println(" ==== 번호를 올바르게 입력해주세요 ==== ");
				}
		} while(code != 0);
		System.out.println("===== 종료 =====");
		sc.close();
	}


	}


