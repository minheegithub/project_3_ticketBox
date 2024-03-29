package boardDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import boardLoginDbconn.DBConn;
import boardVO.ListVO;
import boardVO.ViewVO;

public class BoardDAO {

	private Connection con;
	private static BoardDAO dao;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	private BoardDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public static BoardDAO getInstance() throws ClassNotFoundException, SQLException {
		if(dao == null)
			dao = new BoardDAO();
		return dao;  //싱글톤 디자인패턴// 변수선언 x, 클래스.getInstance().함수(); 디비 커넥션을 한번만 진행하고 객체를 불러와서 쓴다.
	}

	public void pstmtClose() throws SQLException {
		if (pstmt != null)
			pstmt.close();
	}

	public boolean insert_board(String dbsubject, String password, String dbname, String dbmemo, String jumsoo) {
				
		String seq = "select max(aid) from board";  // 쿼리문
		String sql = "insert into board values(?,?,?,?,?,sysdate,?,?,?)";
		try {
			stmt = con.createStatement();  // 커넥션 개체로 부터 Statement 생성 핸들러 연결
			rs = stmt.executeQuery( seq );  
			
			int aid = -1;
			if( rs.next() )
				aid = rs.getInt(1);       // re.next()가 있으면 Integer형 aid에 결과값 할당
			
			aid++;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aid);
			pstmt.setInt(2, aid);
			pstmt.setString(3, password);
			pstmt.setString(4, dbsubject);
			pstmt.setString(5, dbname);
			pstmt.setInt(6,0);//////// 문자그대로 넣기
			pstmt.setString(7, dbmemo);
			pstmt.setString(8, jumsoo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Insert Exception");
			return false;
		} /*finally {	
			try {
				if(con != null)con.close();
				if(pstmt != null)pstmt.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
		return true;
	}
	//str_aid현재 페이지
	public ViewVO contentView(String str_aid){
		ViewVO v = null;
		int hits=0;
		int aid1 = 0;
		String sql="select aid, dbname, dbsubject, dbmemo, dbhits, jumsoo from board where aid="+str_aid;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				int aid = rs.getInt(1);
				String dbname = rs.getString(2);
				String dbsubject = rs.getString(3);
				String dbmemo = rs.getString(4);
				int dbhits = rs.getInt(5);
				String jumsoo = rs.getString(6);
				v=new ViewVO(aid, dbname, dbsubject, dbmemo, dbhits, jumsoo);
				hits += dbhits;
			}//while-end
			
			hits++;
			aid1 += Integer.parseInt(str_aid);
			sql ="update board set dbhits = ? where aid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hits);
			pstmt.setInt(2, aid1);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	//result 일치는 1, 비일치는 0
	public int findPwd(String aid, String pwd, String dbname) {
		
		String sql = "select count(*) from board where aid = ? and pwd = ? and dbname = ?";
		int cnt = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, pwd);
			pstmt.setString(3, dbname);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} /*finally {	
			try {
				if(con != null)con.close();
				if(pstmt != null)pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}*/
		
		return cnt;
		
	}
	
	public boolean boardDelete(String str_aid) {
		
		String sql = "delete from board where aid="+str_aid;
	
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("delete Exception");
			return false;
		} /*finally {	
			try {
				if(con != null)con.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	*/
		return true;
	}
	
	public ViewVO modifyView(String str_aid){
		ViewVO v = null;
		int aid1 = Integer.parseInt(str_aid);
		String sql="select aid, dbname, dbsubject, dbmemo, dbhits, jumsoo from board where aid= ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aid1);	
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int aid = rs.getInt(1);
				String dbname = rs.getString(2);
				String dbsubject = rs.getString(3);
				String dbmemo = rs.getString(4);
				int dbhits = rs.getInt(5);
				String jumsoo= rs.getString(6);
				v=new ViewVO(aid, dbname, dbsubject, dbmemo, dbhits,jumsoo);
			}//while-end
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*finally {	
				try {
					if(con != null)con.close();
					if(pstmt != null)pstmt.close();
					if(rs != null) rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
		return v;
	}
	
	public boolean modify_board(String aid, String dbmemo,String jumsoo) {
		
		int aid1 = Integer.parseInt(aid);
		String sql = "update board set dbmemo = ?, jumsoo = ? where aid = ?";
		try {
				
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbmemo);
			pstmt.setString(2, jumsoo);
			pstmt.setInt(3, aid1);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("update Exception");
			return false;
		} /*finally {	
			try {
				if(con != null)con.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
		return true;
	}
	
	
	public int getTotalCnt(String dbsearch) {
		int total_cnt = 0;
		String sql_n = "select count(*) from board where dbsubject like '%" + dbsearch + "%'";
				
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery( sql_n );
			
			if( rs.next() )
				total_cnt = rs.getInt(1);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total_cnt;
	}
	
	//공연 명단 가져오기
	public ArrayList<String> getPerformanceList(){
		
		ArrayList<String> performList = new ArrayList<String>();
		
		String sql = "select p_name from performance";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				performList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return performList;
	}
	
	//공연 이미지 이름 가져오기
	public String getImageName(String p_name) {
		String image_name = "";
		String sql = "select image_name from performance where p_name = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				image_name = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image_name;
	}

	public ArrayList<ListVO> getAllList(String dbsearch, int start,  int list_num){
		
		ArrayList<ListVO> array = new ArrayList<>();
		
		try{
			
			String sql_c = "";
//			sql_c +=	 "select aid, dbname, dbsubject, to_char( dbdate, 'yyyy/mm/dd hh:mi:ss' ), dbhits, dbmemo from ( ";
//			sql_c +=	 "select aid, dbname, dbsubject, to_char( dbdate, 'yyyy-mm-dd' ), dbhits, dbmemo from ( ";
			sql_c +=	 "select aid, dbname, dbsubject, dbdate, dbhits, dbmemo, jumsoo from ( ";
			sql_c +=	"	select * from ( ";
			sql_c +=			"select * from ( ";
			sql_c +=				"select * from board order by rid asc ) ";
			sql_c +=			"where dbsubject like '%" + dbsearch + "%') ";
			sql_c +=		"where rownum <= " + start + " order by rid desc ) ";
			sql_c +=	"where rownum <= " + list_num;
			rs = stmt.executeQuery( sql_c );
			int title_len = 100;
			/*int aid = start;*/
			
			while( rs.next() ){
				String aid1 = rs.getString(1);
				String name = rs.getString(2);
				String subject = rs.getString(3);
//				String date = rs.getString(4);
				Timestamp date = rs.getTimestamp(4);
				String hits = rs.getString(5);
				StringBuffer buf = new StringBuffer( rs.getString(6) );
				String jumsoo = rs.getString(7);
				
				String content = "";
				if( buf.length() > title_len ){
					buf.setLength( title_len ); 
					content = buf+"...";					
				}
				else {
					content = buf +"";
				}
				ListVO lv = new ListVO(aid1, name, subject, date, hits, content, jumsoo);
				array.add(lv);
				
				/*aid--; */
			}
			
		}catch( Exception e ) {
			System.out.println(e.toString());
		}
		
		return array;
		
	}
	
	
		
}
