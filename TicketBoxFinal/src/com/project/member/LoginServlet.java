package com.project.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memDAO.MemberDAO;
import memDbconn.DBConnection;
import memVO.MemberVO;

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
		System.out.println("세션");
		String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        
        MemberVO vo = new MemberVO();
		try {
		    vo = MemberDAO.getInstance().login(id, pw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("id", vo.getId());
		session.setAttribute("pw", vo.getPw());
		session.setAttribute("name", vo.getName());
		session.setAttribute("phone", vo.getPhone());
		session.setAttribute("gender", vo.getGender());
		session.setAttribute("address", vo.getAddress());
		session.setAttribute("email", vo.getEmail());
        
        response.sendRedirect("../main/ticketMain.jsp");
  
	}
       
}
    
