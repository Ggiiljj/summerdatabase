package model;

import java.sql.Date;

public class SignupPlan {
	private String schoolid;
	private String schoolname;
	private int signcondclass;
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public int getSigncondclass() {
		return signcondclass;
	}
	public void setSigncondclass(int signcondclass) {
		this.signcondclass = signcondclass;
	}
	private int signcond1;
	private int signcond2;
	private Date begintime;
	private Date endtime;
	private String signupway;
	public String getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid=schoolid;
	}
	public int getSigncond1() {
		return signcond1;
	}
	public void setSigncond1(int signcond1) {
		this.signcond1=signcond1;
	}
	public int getSigncond2() {
		return signcond2;
	}
	public void setSigncond2(int  signcond2) {
		this.signcond2=signcond2;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime=begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime=endtime;
	}
	public String getSignupway() {
		return signupway;
	}
	public void setSignupway(String signupway) {
		this.signupway=signupway;
	}
}
