package com.ticket.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardDAO.BoardDAO;
import boardVO.ListVO;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/board/BoardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
		  
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		 String gogo = null;
		 String str_c_page = request.getParameter( "str_c_page" );  		
		 if( str_c_page == null ) 
				str_c_page = "1";
		 int c_page = Integer.parseInt( str_c_page );//넘길값
		 
		 String dbsearch = request.getParameter( "dbsearch" );
		 if( dbsearch == null || dbsearch.trim( ).length( ) == 0 ) {
				dbsearch = "%";
		 }
		 	 
		 BoardDAO dao = null;
		 
		 try {
			 dao = new BoardDAO();
		 } catch (ClassNotFoundException | SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 
		 int total_cnt =dao.getTotalCnt(dbsearch);
		 	 
		 int list_num = 10;
		 int start = 0;
	
		 start = total_cnt - ( c_page - 1 ) * list_num;
		 
		 ArrayList<ListVO> allist = null;
		 		 
		allist = dao.getAllList(dbsearch, start, list_num);
		
		
		request.setAttribute("allist", allist);
		request.setAttribute("total_cnt", total_cnt);
		request.setAttribute("str_c_page", str_c_page);
		
		gogo = "boardList.jsp";
				
		RequestDispatcher rd1 = request.getRequestDispatcher(gogo);
		rd1.forward(request, response);
				
	}

}
