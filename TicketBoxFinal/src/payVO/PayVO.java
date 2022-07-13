package payVO;

import java.sql.Date;

public class PayVO {

	private String name;
	private String email;
	private String tel;
	private String adr;
	private int pay;
	private String book;
	private String payway;
	
	public PayVO() {}
	
	public PayVO(String name, String email, String tel, String adr, int pay, String book, String payway) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.adr = adr;
		this.pay = pay;
		this.book = book;
		this.payway = payway;

	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

}