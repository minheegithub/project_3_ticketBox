package seatInfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import seatInfoDBConn.SeatInfoDBConn;
import seatInfoVO.SeatInfoVO;


public class SeatInfoDAO {

	private Connection con;	
	PreparedStatement pstmt=null;
	ResultSet rs=null;				
	
	
	public SeatInfoDAO() throws ClassNotFoundException, SQLException {
		con=new SeatInfoDBConn().getConnection();  	 
											
	}
	
	public void pstmtClose() throws SQLException {
		if(pstmt != null) 
			pstmt.close();
	}
	
	public void getAllInfoClose() throws SQLException {
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
	
	//잔여석 계산을 위해 선택된 좌석 정보를 모두 가져오는 method --> count 함수를 쓰자.------------------------------*****
	public ArrayList<SeatInfoVO> getAllInfo() throws SQLException{
		ArrayList<SeatInfoVO> siarray = new ArrayList<SeatInfoVO>();
		String sql = "select s1_id,s2_id,s3_id from member_seat where pid=?";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String s_id = rs.getString("s-id");
			
			SeatInfoVO sv = new SeatInfoVO(s_id);
			siarray.add(sv);
		}
		return siarray;
	}
	
	//사용자가 선택한 모든 좌석정보들을 가져오기 위해서 아이디로 검색. 유저아이디, 좌석번호, 공연시리얼번호, 공연이름이 출력된다.**************
	public SeatInfoVO getInfo(String id) throws SQLException{
		SeatInfoVO sv = null;
		String sql = "select * from member_seat where id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String u_id = rs.getString("id"); //유저아이디
			String s1_id = rs.getString("s1_id"); //좌석번호1
			String s2_id = rs.getString("s2_id"); //좌석번호2
			String s3_id = rs.getString("s3_id");//좌석번호3
			String p_id = rs.getString("p_id"); //공연 시리얼번호
			String p_name = rs.getString("p_name"); //공연이름
			sv = new SeatInfoVO(id,s1_id,s2_id, s3_id,p_id,p_name);
		}else {
			sv = null;
		}
		return sv; //객체 반환
	}

	///////////////////////////////쓰는 함수1./////////////////////////////////////////////////////////////////////////////
	public boolean insert_select_seat(String id, String s1_id, String s2_id, String s3_id, String p_id, String p_name) {
		String sql = "insert into member_seat values(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, s1_id);
			pstmt.setString(3, s2_id);
			pstmt.setString(4, s3_id);
			pstmt.setString(5, p_id);
			pstmt.setString(6, p_name);
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("삽입실패");
			return false;
		}
		return true;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//이전 단계 버튼을 누르면 자신이 선택한 좌석 정보가 날라간다. 쓰는 함수2///////////////
	public boolean delete_seat(String id,String p_id) {
			String sql="delete from member_seat where id=? and p_id=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);   //유저 아이디
				pstmt.setString(2, p_id); //공연회차 id(시리얼 번호)
				pstmt.executeUpdate();
				System.out.println("삭제 성공");
			} catch (SQLException e) {
				System.out.println("Delete Exception");
				return false;
			}
			return true;
		} 
	//잔여석 update를 위한 함수
	public boolean update_seats(String p_id, int vip, int r, int s) {
		String sql = "update performance_each set VIP = VIP-? , R= R-?, S=S-? where p_id =?";
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, vip);
			pstmt.setInt(2, r);
			pstmt.setInt(3, s);
			pstmt.setString(4, p_id);
			pstmt.executeUpdate();
			System.out.println("잔여석 업데이트 성공");
		}catch(SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}
	
	//update performance_each set VIP = VIP-0 , R= R-0, S=S-1 where p_id = 'MA08281400';
	
		
}
