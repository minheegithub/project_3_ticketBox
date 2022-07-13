package FrontController.ticket.board;

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
 * Servlet implementation class BoardModify
 */
@WebServlet("/board/BoardModify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModify() {
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
		String aid = request.getParameter("aid");
		String dbmemo = request.getParameter("content");
		String jumsoo = request.getParameter("jumsoo");
		
		try {
			board = new BoardDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Boolean b =board.modify_board(dbsubject, aid, dbmemo, jumsoo);
	
		String gogo = null;
		if(b) {
			gogo = "result.jsp";			
		}else {
			gogo = "BoardList";
		}
		
		RequestDispatcher rd1 = request.getRequestDispatcher(gogo);
		rd1.forward(request, response);
	}

}
