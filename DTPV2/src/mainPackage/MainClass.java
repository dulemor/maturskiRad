package mainPackage;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mainPackage.model.BaseUser;
import mainPackage.model.CONSTANTS;
import mainPackage.model.DatabaseManagementSystem;

@Path("/main")
public class MainClass {
	
	@Path("/test-hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testHello()
	{
		return "Hello!!!";
	}
	
	@Path("/get-user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BaseUser getUser()
	{
		BaseUser korisnik = new BaseUser("marko123", "123tajna123", "marko@gmail.com", CONSTANTS._USER_ROLE_ADMINISTRATOR);
		return korisnik;
	}
	
	@Path("/try-saving-user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveUser(BaseUser user)
	{
		DatabaseManagementSystem.SaveUser(user);
	}
	
	
	@Path("/get-all-users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, BaseUser> GetAllUsers()
	{
		return DatabaseManagementSystem.ReadAllUsersFromDB();
	}
	
}




