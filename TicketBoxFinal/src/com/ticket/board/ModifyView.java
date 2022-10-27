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
 * Servlet implementation class ModifyView
 */
@WebServlet("/board/ModifyView")
public class ModifyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String aid  = request.getParameter("aid");
				
		BoardDAO dao = null;
		try {
			dao = BoardDAO.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ViewVO v = dao.contentView(aid);
		String p_name = v.getSubject();//작품명 가져오기
		String image_name = dao.getImageName(p_name);
		
		request.setAttribute("image_name", image_name);
		request.setAttribute("view", v);
		
		RequestDispatcher dispat = request.getRequestDispatcher("boardModify.jsp");
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
