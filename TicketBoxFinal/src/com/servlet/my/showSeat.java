package com.servlet.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import TicketDAO.TicketDAO;
import TicketVO.TicketVO;

/**
 * Servlet implementation class showSeat
 */
@WebServlet("/showSeat")
public class showSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showSeat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TicketDAO td1= null;
		TicketVO tv1 = null;
		
		try {
			td1 = new TicketDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//booking.js 에 있는 ajax 부분에서 data{}에 들어있는 MovieName과 일치,
		String MovieName = request.getParameter("MovieName");
		String MovieDate = request.getParameter("MovieDate");
		String MovieTime = request.getParameter("MovieTime");
		
		String p_id1 = MovieName.substring(0,2).toUpperCase();
		String [] imsi = MovieDate.split("-");
		String p_id = (p_id1 + imsi[1]+imsi[2]+MovieTime);
			 
		
		//System.out.println(p_id);  //p_id가 제대로 만들어 졌는지 확인
		List<Integer> aa = td1.showSeat(p_id);  //DAO에 있는 메소드 showSeat에 
												//showSeat.java(서블릿)에서 생성된
												//td1 (p_id) 를 넣고 굴림
		//System.out.println(aa); //List aa 확인 .
		
		response.setContentType("application/json");
		//response.setContentType("application;charset=json");
		PrintWriter pw = response.getWriter();
		JsonObject json = new JsonObject();
		
		json.addProperty("vip", aa.get(0));
		json.addProperty("r", aa.get(1));
		json.addProperty("s", aa.get(2));
		
		
		pw.print(json.toString());
		
		
		HttpSession session = request.getSession();
		session.setAttribute("p_id", p_id);
		
		session.setAttribute("vip_left", aa.get(0));
		session.setAttribute("r_left", aa.get(1));
		session.setAttribute("s_left", aa.get(2));
		//response.sendRedirect("../Seat_selection/Seat_main.jsp");
		
		//session.setAttribute("pname",pname);
		//request.setAttribute("p_id", p_id);
		
		//RequestDispatcher rd1 = request.getRequestDispatcher("../Seat_selection/Seat_main.jsp");
		//rd1.forward(request, response);

		
	}

}
