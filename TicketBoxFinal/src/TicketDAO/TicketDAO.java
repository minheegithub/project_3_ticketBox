package TicketDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TicketDBConn.TicketDBConn;
import TicketVO.TicketVO;

// table
/*ticketName varchar2(30) not null,
MovieDate varchar2(20) not null,
MovieTime varchar2(20) not null,
MovieSeat varchar2(20) not null */ 

public class TicketDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs= null;
	
	public TicketDAO() throws ClassNotFoundException, SQLException {
		con = new TicketDBConn().getConnection();
	}
	
	//ticket 정보 다 받아오기    //쓸일이 없을듯..
/*	public ArrayList<TicketVO> getAllInfo() throws SQLException{
	ArrayList<TicketVO> tiarray = new ArrayList<TicketVO>();
	String sql = "select * from ticket1 order by Id";
	pstmt= con.prepareStatement(sql);
	rs= pstmt.executeQuery();
	
	while(rs.next()) {
		int id =rs.getInt(1);
		String ticketName = rs.getString(2);
		String MovieDate = rs.getString(3);
		String MovieTime = rs.getString(4);
		String MovieSeat = rs.getString(5);
		
		TicketVO tv = new TicketVO(id,ticketName, MovieDate, MovieTime, MovieSeat);
		tiarray.add(tv);
	}
	return tiarray;
	
	}*/
	
	//티켓정보 입력
	/* 수업방식은 한번에 직접 텍스트를 쳐서 하는 방식인데, 내가 하려는 방식은 해당 데이터를 누르면 그 데이터가 전달되게 해야함 */
	/* 티켓에 들어가야 할 내용은 기존에 입력되어 있는 id*/
	/* 그리고 뮤지컬 제목, 뮤지컬 날짜, 뮤지컬 시간,      -- 좌석--*//////////////////////////////////////////////안쓴다.
	public boolean insertTicketData(int Id, String ticketName, String MovieDate, String MovieTime, String MovieSeat) {
		String sql="insert into ticket1 values(?,?,?,?,?)";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, Id);
			pstmt.setString(2, ticketName);
			pstmt.setString(3, MovieDate);
			pstmt.setString(4, MovieTime);
			pstmt.setString(5, MovieSeat);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("insert Exception");
			return false;
		}
		return true;		
	}
	
	/////////////////////////////////////////////////////////////
	public String getPID(String pname, String pdate, String ptime) throws SQLException{
		String sql = "select p_id from performance_each where p_name =? and p_date=? and p_time=?"; //공연이름, 날짜, 시간을 바탕으로 p_id를 가져온다.
		String p_id = null;
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,pname); //마리앙뚜아네트-이름
		//날짜값은 
		int year = Integer.parseInt(pdate.substring(0,4));
		int month = Integer.parseInt(pdate.substring(5,7));
		int day = Integer.parseInt(pdate.substring(8,10));
		
		Date d = new Date(year,month,day);
		pstmt.setDate(2, d); //날짜 - sql식으로 변경			
		pstmt.setString(3, ptime); //시간- 14:00			
		 
		rs = pstmt.executeQuery();
		if(rs.next()) {
			p_id = rs.getString("p_id");
		}else {
			p_id = null; //못찾았다면
		}
		return p_id;
	}
	////////////////////////////////////////////////////////////////////////////////////////
	public List<Integer> showSeat(String p_id) {
		List<Integer> tiarray = new ArrayList<Integer>();
		String sql = "select vip, r, s from performance_each where p_id=?";		
		try {
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, p_id);
		rs = pstmt.executeQuery();
		//System.out.println("82번째");
		while(rs.next()) {
			/*System.out.println("while 안에");
			int vip = rs.getInt(1);
			int r = rs.getInt(2);
			int s = rs.getInt(3);*/
			
			tiarray.add(rs.getInt(1));
			tiarray.add(rs.getInt(2));
			tiarray.add(rs.getInt(3));
		}
		
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("catch입니다");
			return null;
		}
		System.out.println("아닙니다");
		return tiarray;
	}
	
	
}

	
