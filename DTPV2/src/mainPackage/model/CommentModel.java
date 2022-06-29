package mainPackage.model;

public class CommentModel {
	private String usernamePosted;
	private Long profId;
	private String textMessage;
	private float prof_grade;
	private float pred_grade;
	private float ocen_grade;
	public float getProf_grade() {
		return prof_grade;
	}
	public void setProf_grade(float prof_grade) {
		this.prof_grade = prof_grade;
	}
	public float getPred_grade() {
		return pred_grade;
	}
	public void setPred_grade(float pred_grade) {
		this.pred_grade = pred_grade;
	}
	public float getOcen_grade() {
		return ocen_grade;
	}
	public void setOcen_grade(float ocen_grade) {
		this.ocen_grade = ocen_grade;
	}

	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsernamePosted() {
		return usernamePosted;
	}
	public void setUsernamePosted(String usernamePosted) {
		this.usernamePosted = usernamePosted;
	}
	public Long getProfId() {
		return profId;
	}
	public void setProfId(Long profId) {
		this.profId = profId;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += this.usernamePosted + CONSTANTS._DATA_SEPARATOR +
				this.profId + CONSTANTS._DATA_SEPARATOR +
				this.textMessage  + CONSTANTS._DATA_SEPARATOR +
				this.id  + CONSTANTS._DATA_SEPARATOR +
				this.pred_grade + CONSTANTS._DATA_SEPARATOR + 
				this.prof_grade + CONSTANTS._DATA_SEPARATOR +
				this.ocen_grade;
		return str;	
	}
}
