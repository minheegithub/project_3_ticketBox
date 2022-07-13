package com.ticket.pay;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payDAO.payDAO;
import seatInfoDAO.SeatInfoDAO;

/**
 * Servlet implementation class PayInsert
 */
@WebServlet("/PayInsert")
public class PayInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		
		payDAO pd1 = null;
		try {
			pd1 = new payDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String adr = request.getParameter("adr");
		String spay = request.getParameter("pay");
		String book = request.getParameter("book");
		String way = request.getParameter("payway");
		int pay = Integer.parseInt(spay);
		Boolean b = pd1.insertInfo(name, email, tel, adr, pay, book, way);
		if(b) {
			/*request.setAttribute("result1", "입력완료");*/
			request.setAttribute("name", name);
			request.setAttribute("email", email);
			request.setAttribute("tel", tel);
			request.setAttribute("adr", adr);
			request.setAttribute("pay", pay);
			request.setAttribute("book", book);
			request.setAttribute("payway", way);
		} else {
			request.setAttribute("result1", "입력오류");
		}
		
		String p_id = (String)session.getAttribute("p_id");
		int vip = (Integer)session.getAttribute("vip");
		int r = (Integer)session.getAttribute("r");
		int s = (Integer)session.getAttribute("s");
		
		System.out.println("vip,r,s"+ vip+" "+r+" "+s);
		
		SeatInfoDAO sidao = null;
		try {
			sidao= new SeatInfoDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sidao.update_seats(p_id,vip,r,s); // 결제가 완료되면 잔여석이 update 된다.
		
		//String sql = "update performance_each set VIP = VIP-? , R= R-?, S=S-? where p_id = ?"; update_seats
		
		RequestDispatcher rd1 = request.getRequestDispatcher("./Pay/pay.jsp");
		rd1.forward(request,  response);
		
		
	}

}
