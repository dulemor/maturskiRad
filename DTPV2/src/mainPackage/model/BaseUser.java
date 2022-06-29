package mainPackage.model;

public class BaseUser {
	private String username;
	private String password;
	private String email;
	private int user_role;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public BaseUser(){}
	public BaseUser(String username, String password, String email, int user_role)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.user_role = user_role;
	}
	
	public String toString()
	{
		String str = "";
		str += this.username + CONSTANTS._DATA_SEPARATOR +
				this.password + CONSTANTS._DATA_SEPARATOR +
				this.email  + CONSTANTS._DATA_SEPARATOR +
				this.user_role;
		
		return str;
	}
}
