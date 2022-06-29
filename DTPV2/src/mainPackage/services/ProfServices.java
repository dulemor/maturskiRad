package mainPackage.services;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mainPackage.model.DatabaseManagementSystem;
import mainPackage.model.TeacherModel;
import mainPackage.model.wrappers.ProfAndCommentsWrapper;

@Path("/profs")
public class ProfServices {
	
	@Path("/get-all-prof-names")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<Long, String> getAllProfNames()
	{
		HashMap<Long, TeacherModel> allProfesors = DatabaseManagementSystem.ReadAllProfesorsFromDB();
		HashMap<Long, String> allProfNames = new HashMap<>();
		
		for ( TeacherModel teacher : allProfesors.values())
		{
			allProfNames.put(teacher.getId(), teacher.getName() + " " + teacher.getSurname());
		}
		
		return allProfNames;
	}
	
	@Path("/add-profesor")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void tryAddProfesor(TeacherModel newTeacher)
	{
		DatabaseManagementSystem.SaveProfesor(newTeacher);
	}
	
	@Path("/get-prof-by-id/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ProfAndCommentsWrapper getProfesorById(@PathParam("id") Long profId)
	{
		return DatabaseManagementSystem.getActiveCommentsForProf(profId);
	}
	
}
