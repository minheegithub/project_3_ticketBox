package memDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import boardLoginDbconn.DBConn;
import memVO.MemberVO;

public class MemberDAO {
	
	private Connection con;
	private static MemberDAO dao;

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private MemberDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public static MemberDAO getInstance() throws ClassNotFoundException, SQLException {
		if(dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	public boolean duplicateIdCheck(String id){
	    
        boolean x= false;
        
        try {
            // 쿼리
            StringBuffer query = new StringBuffer();
            query.append("SELECT ID FROM MEMBER WHERE ID=?");
           
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()) x = true; //해당 아이디 존재
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        }
        return x;
    } // end duplicateIdCheck()
	
	//회원가입
	public boolean join(String id, String pw, String name, String phone, String gender, String address, String email) {
		boolean isSuccess = true;
		String query = "INSERT INTO MEMBER(id, pw, name, phone, gender, address, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.setString(5, gender);
            pstmt.setString(6, address);
            pstmt.setString(7, email);
            pstmt.execute();
		} catch (SQLException e) {
			 System.out.println(e);
	         isSuccess = false;
		} 
		return isSuccess;
	}
	
	//로그인 아이디 비번 체크
	public int loginCheck(String id, String pw) {
		
		int isSuccess = 0;
		String query = "SELECT COUNT(*) FROM MEMBER WHERE ID=? AND PW=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isSuccess = rs.getInt(1);
			}
			System.out.println(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//일치하는게 있으면 1 없으면 0
		return isSuccess;
	}
	
	//로그인
	public MemberVO login(String id, String pw) {
		MemberVO vo = new MemberVO();
		String query = "SELECT * FROM MEMBER WHERE ID=? AND PW=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString("name");             
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String email = rs.getString("email");
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setGender(gender);
				vo.setAddress(address);
				vo.setEmail(email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	

	
	
}
