package mainPackage.model.wrappers;

import java.util.ArrayList;

import mainPackage.model.CommentModel;
import mainPackage.model.TeacherModel;

public class ProfAndCommentsWrapper {
	private TeacherModel prof;
	private ArrayList<CommentModel> comments;
	private ArrayList<ProfCommentWrapper> commentWrappers;
	
	public ArrayList<ProfCommentWrapper> getCommentWrappers() {
		return commentWrappers;
	}


	public void setCommentWrappers(ArrayList<ProfCommentWrapper> commentWrappers) {
		this.commentWrappers = commentWrappers;
	}


	public TeacherModel getProf() {
		return prof;
	}


	public void setProf(TeacherModel prof) {
		this.prof = prof;
	}


	public ArrayList<CommentModel> getComments() {
		return comments;
	}


	public void setComments(ArrayList<CommentModel> comments) {
		this.comments = comments;
	}


	public ProfAndCommentsWrapper()
	{
		this.comments = new ArrayList<>();
		this.commentWrappers = new ArrayList<>();
	}
}
