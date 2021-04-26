package api.model;

public class ClassSize {
	private String cCode;
	private int size;
	
	public ClassSize(String code, int size) {
		this.cCode = code;
		this.size = size;
	}
	
	public ClassSize() {
		this("",0);
	}	
	
	public String getClassCode() {
		return this.cCode;
	}
	
	public int getSize() {
		return this.size;
	}
}
