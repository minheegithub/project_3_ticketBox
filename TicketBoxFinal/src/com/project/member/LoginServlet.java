package com.project.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memDbconn.DBConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login_signup/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        
        String query = "SELECT * FROM MEMBER WHERE ID=? AND PW=?";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        HttpSession session = request.getSession();

        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, pw);
            rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");             
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String email = rs.getString("email");
                
                session.setAttribute("id", id);
                session.setAttribute("pw", pw);
                session.setAttribute("name", name);
                session.setAttribute("phone", phone);
                session.setAttribute("gender", gender);
                session.setAttribute("address", address);
                session.setAttribute("email", email);
                
                
                response.sendRedirect("memInfo.jsp");
                
             
            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        
	}
       
}
    
