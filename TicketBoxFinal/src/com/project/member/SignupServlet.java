package com.project.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memDbconn.DBConnection;

/**
 * Servlet implementation class ServletSignup
 */
@WebServlet("/login_signup/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.sendRedirect("signup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		Connection conn = null;
        PreparedStatement pst = null;
        String query = "INSERT INTO MEMBER(id, pw, name, phone, gender, address, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone1") +"-"+ request.getParameter("phone2") +"-"+ request.getParameter("phone3");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String email = request.getParameter("mail1") + "@" + request.getParameter("mail2");
        
        boolean isSuccess = true;

        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement(query);

            pst.setString(1, id);
            pst.setString(2, pw);
            pst.setString(3, name);
            pst.setString(4, phone);
            pst.setString(5, gender);
            pst.setString(6, address);
            pst.setString(7, email);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
            isSuccess = false;
        } finally {
            try {
                if (pst != null)
                    pst.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (isSuccess) {
           /* request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();

            writer.println("회원가입에 성공했습니다.<br>");
            writer.println("<a href='index.jsp'>로그인</a>");//자바스크립트로 바꾸기?
            */ 

        	
        	response.sendRedirect("index.jsp");
        }
    }
}