package com.project.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memDbconn.DBConnection;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/login_signup/modify")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
	        String id = (String) session.getAttribute("id");
	        String pw = request.getParameter("pw");
	        Connection conn = null;
	        PreparedStatement pst = null;
	        String query = null;
	        String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");

	        if (request.getParameter("pwmodify") != null) {
	            query = "UPDATE MEMBER SET NAME=?, PW=?, PHONE=?, GENDER=? WHERE ID=? AND PW=?";
	        } else {
	            query = "UPDATE MEMBER SET NAME=?, PHONE=?, GENDER=? WHERE ID=? AND PW=?";
	        }

	        try {
	            conn = DBConnection.connect();
	            pst = conn.prepareStatement(query);
	            if (request.getParameter("pwmodify") != null) {
	                pst.setString(1, request.getParameter("name"));
	                pst.setString(2, request.getParameter("pwmodify"));
	                pst.setString(3, phone);
	                pst.setString(4, request.getParameter("gender"));
	                pst.setString(5, id);
	                pst.setString(6, pw);
	            } else {
	                pst.setString(1, request.getParameter("name"));
	                pst.setString(2, phone);
	                pst.setString(3, request.getParameter("gender"));
	                pst.setString(4, id);
	                pst.setString(5, pw);
	            }
	            pst.executeUpdate();
	        } catch (Exception e) {
	            System.out.println(e);
	        } finally {
	            try {
	                if (pst != null)
	                    pst.close();

	                if (conn != null)
	                    conn.close();

	                session.invalidate();
	                
	                
	            } catch (Exception e) {
	                System.out.println(e);
	            }
	        }

	        response.sendRedirect("index.jsp");
	    }
}