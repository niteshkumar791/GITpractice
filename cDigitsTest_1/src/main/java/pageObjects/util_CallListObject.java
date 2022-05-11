package pageObjects;

public class util_CallListObject {

	public int recno;
	public String text;
	public String date;
	public String duration;
	
	public util_CallListObject(int recno, String text, String date, String duration) {
		super();
		this.recno = recno;
		this.text = text;
		this.date = date;
		this.duration = duration;
	}
	
	public util_CallListObject(int recno, String text, String date) {
		super();
		this.recno = recno;
		this.text = text;
		this.date = date;
	}
	
	public util_CallListObject(int recno, String text) {
		super();
		this.recno = recno;
		this.text = text;
	}
	
	public util_CallListObject(String text) {
		super();
		
		this.text = text;
	}

	public int getRecno() {
		return recno;
	}

	public void setRecno(int recno) {
		this.recno = recno;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
}
