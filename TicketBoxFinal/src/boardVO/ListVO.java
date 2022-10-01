package boardVO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ListVO {

	private String aid;
	private String name;
	private String subject;
	private Timestamp date;
	private String hits;
	private String content;
	private String jumsoo;
	
	public ListVO(String aid, String name, String subject, Timestamp date, String hits, String content, String jumsoo) {
		this.aid = aid;
		this.name = name;
		this.subject = subject;
		this.date = date;
		this.hits = hits;
		this.content = content;
		this.jumsoo = jumsoo;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		
		this.date = date;
	}
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getJumsoo() {
		return jumsoo;
	}
	public void setJumsoo(String jumsoo) {
		this.jumsoo = jumsoo;
	}
	@Override
	public String toString() {
		return "ListVO [aid=" + aid + ", name=" + name + ", subject=" + subject + ", date=" + date + ", hits=" + hits
				+ ", content=" + content + ", jumsoo=" + jumsoo + "]";
	}
	
	
	
	

	
}
