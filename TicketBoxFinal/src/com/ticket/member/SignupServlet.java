package com.ticket.member;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import memDAO.MemberDAO;

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

        MemberDAO dao = null;
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone1") +"-"+ request.getParameter("phone2") +"-"+ request.getParameter("phone3");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String email = request.getParameter("mail1") + "@" + request.getParameter("mail2");
        
        try {
			dao = MemberDAO.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        boolean isSuccess = dao.join(id, pw, name, phone, gender, address, email);

        if (isSuccess) {
        	response.sendRedirect("index.jsp");
        }else {
        	response.sendRedirect("../main/ticketMain.jsp");
        }
    }
}