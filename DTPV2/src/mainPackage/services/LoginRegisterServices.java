package mainPackage.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mainPackage.model.BaseUser;
import mainPackage.model.DatabaseManagementSystem;

@Path("/login-register-services")
public class LoginRegisterServices {
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BaseUser tryLogIn(LoginPackage pack)
	{
		HashMap<String, BaseUser> sviKorisnici = DatabaseManagementSystem.ReadAllUsersFromDB();
		
		if ( sviKorisnici.containsKey(pack.getUsername()))
		{
			BaseUser user = sviKorisnici.get(pack.getUsername());
			if ( user.getPassword().equals(pack.getPassword()))
			{
				return user;
			}
		}
		
		return new BaseUser(null, null, null, 0);
	}
	
	@Path("/get-all-usernames-and-emails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RegisterDataWrapper getInitialRegistrationData()
	{
		RegisterDataWrapper wrapper = new RegisterDataWrapper();
		
		HashMap<String, BaseUser> all_users = DatabaseManagementSystem.ReadAllUsersFromDB();
		
		for(BaseUser user : all_users.values())
		{
			wrapper.getEmails().add(user.getEmail());
			wrapper.getUsernames().add(user.getUsername());
		}
		
		return wrapper;
	}
	
	@Path("/register-user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void tryRegisterUser(BaseUser user)
	{
		DatabaseManagementSystem.SaveUser(user);
	}
	
}

class RegisterDataWrapper
{
	private ArrayList<String> usernames;
	private ArrayList<String> emails;
	public ArrayList<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(ArrayList<String> usernames) {
		this.usernames = usernames;
	}
	public ArrayList<String> getEmails() {
		return emails;
	}
	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}
	public RegisterDataWrapper()
	{
		this.usernames = new ArrayList<>();
		this.emails = new ArrayList<>();
	}
	
}

class LoginPackage
{
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginPackage()
	{
		
	}
	public LoginPackage(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
}