package TicketVO;

import java.util.Date;


/*
 CREATE TABLE ticket1(
    Id NUMBER(5) not null,
    ticketName varchar2(30) not null,
    MovieDate varchar2(20) not null,
    MovieTime varchar2(20) not null,
    MovieSeat varchar2(20) not null  
      
); 
 
 */
public class TicketVO {
	
	private int id;
	private String ticketName;
	private String MovieDate;
	private String MovieTime;
	private String MovieSeat;
	private int vip;
	private int r;
	private int s;
	
	private String p_id; //공연 시리얼 번호
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	
	public TicketVO() {};
	
	public TicketVO(String p_id) {
		//super();
		this.p_id = p_id;
	}
	public TicketVO(int id, String ticketName, String movieDate, String movieTime, String movieSeat, int vip, int r,
			int s) {
		super();
		this.id = id;
		this.ticketName = ticketName;
		MovieDate = movieDate;
		MovieTime = movieTime;
		MovieSeat = movieSeat;
		this.vip = vip;
		this.r = r;
		this.s = s;
	}
	public TicketVO(int id2, String ticketName2, String movieDate2, String movieTime2, String movieSeat2) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getMovieDate() {
		return MovieDate;
	}
	public void setMovieDate(String movieDate) {
		MovieDate = movieDate;
	}
	public String getMovieTime() {
		return MovieTime;
	}
	public void setMovieTime(String movieTime) {
		MovieTime = movieTime;
	}
	public String getMovieSeat() {
		return MovieSeat;
	}
	public void setMovieSeat(String movieSeat) {
		MovieSeat = movieSeat;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	
	

}


