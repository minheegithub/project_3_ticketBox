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
import boardVO.ViewVO;

/**
 * Servlet implementation class BoardView
 */
@WebServlet("/board/BoardView")
public class BoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str_aid=request.getParameter("str_aid");//?현재 글번호 추정
		
		BoardDAO dao = null;
		try {
			dao = new BoardDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ViewVO v = dao.contentView(str_aid);
		String p_name = v.getSubject();//작품명 가져오기
		String image_name = dao.getImageName(p_name);//이미지 이름 가져오기
		
		request.setAttribute("image_name", image_name);
		request.setAttribute("view", v);
		
		RequestDispatcher dispat = request.getRequestDispatcher("boardView.jsp");
		dispat.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
