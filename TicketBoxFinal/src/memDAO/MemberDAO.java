package memDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import boardDbconn.DBConn;

public class MemberDAO {
	
	private Connection con;

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	public MemberDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();

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
	            
	            if(rs.next()) x= true; //해당 아이디 존재
	            
	            
	            
	        } catch (Exception sqle) {
	            throw new RuntimeException(sqle.getMessage());
	        } finally {
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( con != null ){ con.close(); con=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	        
	        return x;
	    } // end duplicateIdCheck()



}
