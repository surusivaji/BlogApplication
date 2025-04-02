package org.siva.blog.entities;

import java.sql.Date;

public class Post {
	
	private int pid;
	private String ptitle;
	private String pcontent;
	private String pcode;
	private String ppic;
	private Date date;
	private int cid;
	private int userid;
	
	public Post() {
		
	}

	public Post(int pid, String ptitle, String pcontent, String ppic, Date date, int cid, String pcode, int userid) {
		this.pid = pid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.ppic = ppic;
		this.date = date;
		this.cid = cid;
		this.pcode = pcode;
		this.userid = userid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public String getPpic() {
		return ppic;
	}

	public void setPpic(String ppic) {
		this.ppic = ppic;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Post [pid=" + pid + ", ptitle=" + ptitle + ", pcontent=" + pcontent + ", pcode=" + pcode + ", ppic="
				+ ppic + ", date=" + date + ", cid=" + cid + ", userid=" + userid + "]";
	}

}
