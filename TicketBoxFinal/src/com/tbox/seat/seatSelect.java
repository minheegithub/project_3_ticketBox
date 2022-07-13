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
import seatInfoVO.SeatInfoVO;

/**
 * Servlet implementation class seatController
 */
@WebServlet("/seatSelect")
//@WebServlet("*.do") 
public class seatSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seatSelect() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
	
		String seats =  request.getParameter("seats");
		System.out.println("좌석들 "+ seats);
				
		String[] seatArr = {};
		
		if(seats.length() !=0) { //입력받은 값이 0이 아니라면 			
			seatArr = seats.split("  ");
			
			for(int i=0;i<seatArr.length;i++) {
				System.out.println(seatArr[i]);
			}
		}			
		
		else {
			//아무것도 선택하지 않고 다음을 눌렀을 때는 또 따로 예외처리 해야 한다.
			System.out.println("좌석 선택해야지!");
			response.setContentType("text/html; charset=UTF-8");

			
			PrintWriter writer = response.getWriter();
			
			String PageUrl = request.getContextPath()+"/Seat_selection/Seat_main.jsp";
			writer.println("<script>alert('좌석을 한 개 이상 선택해 주세요'); location.href='"+PageUrl+"';</script>"); 
			writer.close();
			
			response.sendRedirect(PageUrl);  //좌석을 선택하지 않고 다음화면으로 넘어가려고 하는 경우

		}
		
		System.out.println("좌석 배열 첫번째 값: "+ seatArr[0]); //잘 들어갔는지 테스트. -> 잘들어감.
		
		//DB와 연결하는 부분.//////////////////////////////////////**좌석 값을 순서대로 쓰는 함수를 dao에서 가져온다.
		SeatInfoDAO sidao = null;
		SeatInfoVO sv = null;
		
		
		/*
		 TelInfoDAO tidao2 = null;
				try {
					tidao2 = new TelInfoDAO();
				} catch(ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				tidao2.insert_nametel(sabun1,name1,tel1,date1); //여기서 dao의 insert 메서드를 호출한다. 즉 계산 작업을 수행하는 controller.
				str= "getAllInfo.jsp";
				
				break; 
		 */
		try {
			sidao = new SeatInfoDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = (String) session.getAttribute("id");
		String p_name = (String)session.getAttribute("pname");
		String p_id = (String)session.getAttribute("p_id");
		
		System.out.println("아이디, 공연이름, 공연아이디"+id+" "+p_name+" "+p_id);
		
		
		///////////////////////////////////////*************************************************
		
		
		
		if(seatArr.length==1) {
			String s1_id = seatArr[0]; //첫 번째 좌석.
			String s2_id ="";
			String s3_id ="";
			session.setAttribute("s1_id", s1_id);
			session.setAttribute("s2_id", ""); ///값이 없을 경우-> 한개, 두 개만 선택했을 경우, 남은 좌석 번호는 ""로 보낸다.
			session.setAttribute("s3_id", "");
			
			System.out.println("두번째, 세번쨰"+s2_id+" "+s3_id);
			sidao.insert_select_seat(id, s1_id, s2_id, s3_id, p_id, p_name); //아이디, 좌석123,p_id, 공연이름 삽입
		}
		else if(seatArr.length==2) {
			String s1_id = seatArr[0]; //첫 번째 좌석.
			String s2_id = seatArr[1]; //두 번째 좌석.
			String s3_id = "";
			session.setAttribute("s1_id", s1_id);
			session.setAttribute("s2_id", s2_id);
			session.setAttribute("s3_id", "");
			
			sidao.insert_select_seat(id, s1_id, s2_id, s3_id, p_id, p_name); //아이디, 좌석123,p_id, 공연이름 삽입
		}
		else if(seatArr.length==3) {
			String s1_id = seatArr[0]; //첫 번째 좌석.
			String s2_id = seatArr[1];
			String s3_id = seatArr[2];
			
			session.setAttribute("s1_id", s1_id);
			session.setAttribute("s2_id", s2_id);
			session.setAttribute("s3_id", s3_id);
			sidao.insert_select_seat(id, s1_id, s2_id, s3_id, p_id, p_name); //아이디, 좌석123,p_id, 공연이름 삽입
		}
		
		//////////////////////////////////금액 계산하는 부분
		
		int total_pay = 0;
		int vip = 0;
		int r= 0;
		int s=0;
		
		for(int i=0;i<seatArr.length;i++) {
			
			char[] arr = seatArr[i].toCharArray();	
			
			for(int j=0;j<arr.length;j++) { 
				if(arr[j] == 'V') {
				 total_pay+=150000;	
				 vip+=1;
				}
				else if(arr[j] == 'R') {
					total_pay +=130000;
					r+=1;
				}
				else if(arr[j] =='S') {
					total_pay += 100000;
					s+=1;
				}
			}
		}
		session.setAttribute("vip", vip);
		session.setAttribute("r",r);
		session.setAttribute("s", s);
		
		System.out.println("vip석 총 :"+vip+"석 선택");
		System.out.println("r석 총: "+r+"석 선택");
		System.out.println("s석 총: "+s+"석 선택");
		
		session.setAttribute("total_pay", total_pay); //총 지불 금액.(기본가)
		
		response.sendRedirect("Payment/total_payment.jsp");
		
		//RequestDispatcher rd1 = request.getRequestDispatcher("../Payment/total_payment.jsp");
		//rd1.forward(request, response);
		
	}

}
