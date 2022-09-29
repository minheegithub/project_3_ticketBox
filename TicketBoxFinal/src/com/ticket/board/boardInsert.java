package com.ticket.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardDAO.BoardDAO;

/**
 * Servlet implementation class boardInsert
 */
@WebServlet("/board/boardInsert")
public class boardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardDAO board = null;
		
		String dbsubject = request.getParameter("subject");
		String password = request.getParameter("pwd");
		String dbname = request.getParameter("name");
		String dbmemo = request.getParameter("content");
		String jumsoo = request.getParameter("jumsoo");
	
		try {
			board = new BoardDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Boolean b =board.insert_board(dbsubject, password,dbname, dbmemo, jumsoo);
		String gogo = null;
		if(b) {//세션을 새로 주는 것인가?
			/*request.setAttribute("result1", "입력완료");*/
			gogo = "result.jsp";
		}else {
			/*request.setAttribute("result1", "입력오류");*/
			gogo = "BoardList";
		}
		
		RequestDispatcher rd1 = request.getRequestDispatcher("BoardList");
		rd1.forward(request, response);
	}

}
