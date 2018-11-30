package management;

import java.util.Date;

public class Member{

  private String name, address, phone, eMail;
  private String cpr;
  private Date birth;
  private double rating, ELO;
  private MemberType memberType;
  private int memberID;

  public Member(){

  }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCpr() {
		return this.cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getELO() {
		return this.ELO;
	}

	public void setELO(double ELO) {
		this.ELO = ELO;
	}

	public MemberType getMemberType() {
		return this.memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public int getMemberID() {
		return this.memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

}
