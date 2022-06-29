package mainPackage.model;

public class TeacherModel {
	private String name;
	private String surname;
	private String subject;
	private Long id;
	private String imageBase64;
	
	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += this.name + CONSTANTS._DATA_SEPARATOR +
				this.surname + CONSTANTS._DATA_SEPARATOR +
				this.subject  + CONSTANTS._DATA_SEPARATOR +
				this.id + CONSTANTS._DATA_SEPARATOR + 
				this.imageBase64;
		return str;
	}
}
