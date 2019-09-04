package model;

public class MajorInfo {
	private String majorid;
	private String majorname;
	private String majorclass;
	private String schoolid;
	private String schoolname;
	public String getMajorid() {
		return majorid;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public void setMajorid(String majorid) {
		this.majorid=majorid;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname=majorname;
	}
	public String getMajorclass() {
		return majorclass;
	}
	public void setMajorclass(String majorclass) {
		this.majorclass=majorclass;
	}
	public String getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid=schoolid;
	}
}
