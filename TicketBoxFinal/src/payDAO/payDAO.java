package payDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import payDBConn.PayDBConn;
import payVO.ForpayVO;
import payVO.PayVO;


public class payDAO {

   private Connection con;
   PreparedStatement pstmt = null;
   Statement stmt = null;
   ResultSet rs = null;
   
   public payDAO() throws ClassNotFoundException, SQLException {
      con = new PayDBConn().getConnection();
   }
   
   public boolean insertInfo(String name, String email, String phone, String address, int pay, String book, String payway) {
      String sql = "insert into payment values (?,?,?,?,?,?,?)";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, name);
         pstmt.setString(2, email);
         pstmt.setString(3, phone);
         pstmt.setString(4, address);
         pstmt.setInt(5, pay);
         pstmt.setString(6, book);
         pstmt.setString(7, payway);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("insert Exception");
         return false;
      }
      return true;
   }
   
   public List<String> payInfo(String name) throws SQLException{
	   	List<String> tiarray = new ArrayList<String>();
         String sql = "select name, email, phone, address from member where name=?";
         /*ForpayVO pv1 = null;*/
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, name);
         rs = pstmt.executeQuery();    
         System.out.println("payinfo--");
         while(rs.next()) {
           System.out.println("db연동완료---");
/*            String name1 = rs.getString(1);
            String email = rs.getString(2);
            String phone = rs.getString(3);
            String address = rs.getString(4);
            
            pv1 = new ForpayVO(name1, email, phone, address);*/
           tiarray.add(rs.getString(1));
           tiarray.add(rs.getString(2));
           tiarray.add(rs.getString(3));
           tiarray.add(rs.getString(4));
            System.out.println(tiarray.toString());
         
         }
         System.out.println(tiarray + "pv값 출력");
         return tiarray;
      }
   
   
   
}