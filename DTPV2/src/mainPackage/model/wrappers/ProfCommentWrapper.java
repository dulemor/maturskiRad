package mainPackage.model.wrappers;

public class ProfCommentWrapper {
	private String usernamePosted;
	private String comment;
	private float predGrade;
	private float profGrade;
	private float ocenGrade;
	private Long commentId;
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getUsernamePosted() {
		return usernamePosted;
	}
	public void setUsernamePosted(String usernamePosted) {
		this.usernamePosted = usernamePosted;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getPredGrade() {
		return predGrade;
	}
	public void setPredGrade(float predGrade) {
		this.predGrade = predGrade;
	}
	public float getProfGrade() {
		return profGrade;
	}
	public void setProfGrade(float profGrade) {
		this.profGrade = profGrade;
	}
	public float getOcenGrade() {
		return ocenGrade;
	}
	public void setOcenGrade(float ocenGrade) {
		this.ocenGrade = ocenGrade;
	}
}
