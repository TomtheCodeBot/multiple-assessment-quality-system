package api.model;

public class Classes {
	private String AYName;
	private String FName;
	private String LName;
	private String MName;
	private String SName;
	private String CName;
	private String PName;
	
	public Classes(String AYName,String FName,String LName, String MName, String SName, String CName, String PName) {
		this.AYName=AYName;
		this.CName=CName;
		this.LName=LName;
		this.MName=MName;
		this.SName=SName;
		this.CName=CName;
		this.PName=PName;
	}

	public String getAYName() {
		return AYName;
	}

	public void setAYName(String aYName) {
		AYName = aYName;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getMName() {
		return MName;
	}

	public void setMName(String mName) {
		MName = mName;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}
}
