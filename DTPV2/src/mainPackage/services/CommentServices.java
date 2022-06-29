package mainPackage.services;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mainPackage.model.CommentModel;
import mainPackage.model.DatabaseManagementSystem;
import mainPackage.model.wrappers.ProfAndCommentsWrapper;

@Path("/comments")
public class CommentServices {
	
	@Path("/post-new-comment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfAndCommentsWrapper recieveAndReturnCommentsForProf(CommentModel comment){
		
		HashMap<Long,CommentModel> comments = DatabaseManagementSystem.ReadAllCommentsFromDB();
		comments.put(comment.getId(), comment);
		DatabaseManagementSystem.SaveLotsOfComments(comments);
		
		Long profId = comment.getProfId();
		
		return DatabaseManagementSystem.getActiveCommentsForProf(profId);
	}
	
	@Path("/delete-comment/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ProfAndCommentsWrapper deleteAndReturnCommentsForProf(@PathParam("id") Long commentId)
	{
		HashMap<Long, CommentModel> comments = DatabaseManagementSystem.ReadAllCommentsFromDB();
		CommentModel comment = comments.get(commentId);
		comments.remove(commentId);
		DatabaseManagementSystem.SaveLotsOfComments(comments);
		
		return DatabaseManagementSystem.getActiveCommentsForProf(comment.getProfId());
		
	}
	
	
	
}
