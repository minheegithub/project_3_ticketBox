package seatInfoVO;

import java.sql.Date;
public class SeatInfoVO {

	//
	private String u_id; //로그인 시 값을 입력받는다. 유저 아이디	
	private String s_id; //좌석번호 ---> 안쓰는 변수*
	private String p_id; //공연 시리얼 번호
	private String p_name; //공연이름
	
	private String s1_id; //좌석번호1
	private String s2_id; //좌석번호2
	private String s3_id; //좌석번호3
	
	
	//좌석 한 번에 받을 것이라서 3개씩 넣어야. null이 되는 경우를 따로 고려해야 한다. 넣는 자리는 3개인데 두개를 선택한 경우의 수도 고려------------------생성자 새로 만들기
	//선택한 좌석 수 칼럼 count로 몇 석인지 구할 수 있음 --> s1,s2,s3중에서 null이 되는 칼럼을 count 세야 한다.
	public SeatInfoVO(String u_id, String s1_id, String s2_id, String s3_id, String p_id, String p_name) {
		//super();
		this.u_id = u_id;	
		this.s1_id = s1_id;
		this.s2_id = s2_id;
		this.s3_id = s3_id;
		this.p_id = p_id;
		this.p_name = p_name;
		
	}
	
	//sv = new SeatInfoVO(id,s1_id,s2_id, s3_id,p_id,p_name);

	public String getS1_id() {
		return s1_id;
	}

	public void setS1_id(String s1_id) {
		this.s1_id = s1_id;
	}

	public String getS2_id() {
		return s2_id;
	}

	public void setS2_id(String s2_id) {
		this.s2_id = s2_id;
	}

	public String getS3_id() {
		return s3_id;
	}

	public void setS3_id(String s3_id) {
		this.s3_id = s3_id;
	}

	//안쓴다. 좌석 한 개씩만 넣을 때 이야기.
	public SeatInfoVO(String u_id, String s_id, String p_id, String p_name) {
		super();
		this.u_id = u_id;
		this.s_id = s_id;
		this.p_id = p_id;
		this.p_name = p_name;
	}	
	
	
	//+좌석 등급. 현재 목표하는 저장 형식이 ex)1층A구역09열10번 VIP. 
	//여기서 등급이 시작하는 13번째 인덱스 부터 끝까지가 class 이름이다. 문자열 자르기 (java에서 아님 sql에서)
	
	public SeatInfoVO() {}
	
	//선택된 좌석 정보만 가져오기 위한(잔여석 계산 관련) 생성자 
	public SeatInfoVO(String s_id) {
		this.s_id = s_id;
	}	
	
	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public SeatInfoVO(String u_id, String s_id, String  p_name) {
		//super();
		this.u_id = u_id;
		this.s_id = s_id;
		this. p_name=  p_name;
	}
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	
	
	
}
