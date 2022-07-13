package com.tbox.seat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seatInfoDAO.SeatInfoDAO;

/**
 * Servlet implementation class performSelect
 */
@WebServlet("/performSelect")
public class performSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public performSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
				
		HttpSession session = request.getSession();
			
		//
		SeatInfoDAO sidao = null;
		try {
			sidao= new SeatInfoDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = (String)session.getAttribute("id");
		String p_id = (String)session.getAttribute("p_id");
		System.out.println("아이디"+id);
		System.out.println("공연회차 아이디"+p_id);
		sidao.delete_seat(id,p_id);	
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String PageUrl = request.getContextPath()+"/Seat_selection/Seat_main.jsp";
		//String PageUrl = request.getContextPath()+"/Payment/total_payment.jsp";
		writer.println("<script>alert('선택한 좌석이 삭제됩니다'); location.href='"+PageUrl+"';</script>"); 		
		writer.close();	
		
		response.sendRedirect("../Seat_selection/Seat_main.jsp");
	}

}
