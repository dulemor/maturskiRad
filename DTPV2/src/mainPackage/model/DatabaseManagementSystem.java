package mainPackage.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import mainPackage.model.wrappers.ProfAndCommentsWrapper;
import mainPackage.model.wrappers.ProfCommentWrapper;

public class DatabaseManagementSystem {
	
	/**
	 * ###### UTILS
	 * */
	private static void CheckFileExistance(String filename)
	{
		File f = new File(filename);
		if( !f.exists() )
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void deleteFileContents(File f)
	{
		try {
			PrintStream out = new PrintStream(new FileOutputStream(f, false));
			out.print("");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ###### USERS
	 * */
	
	public static void SaveUser(BaseUser userToSave)
	{
		File f = new File(CONSTANTS._USERS_FILE_PATH);
		try {
			PrintStream out = new PrintStream(new FileOutputStream(f, true), true, "UTF-8");
			out.println(userToSave.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveLotsOfUsers(HashMap<String, BaseUser> lotsOfUsers)
	{
		CheckFileExistance(CONSTANTS._USERS_FILE_PATH);
		deleteFileContents(new File(CONSTANTS._USERS_FILE_PATH));
		
		for (BaseUser user : lotsOfUsers.values())
		{
			SaveUser(user);
		}
	}
	
	public static BaseUser ReadOneUserByUsername(String username)
	{
		HashMap<String, BaseUser> allUsers = ReadAllUsersFromDB();
		
		for(BaseUser user : allUsers.values())
		{
			if ( user.getUsername().equals(username))
			{
				return user;
			}
		}
		
		return null;
	}
	
	public static HashMap<String, BaseUser> ReadAllUsersFromDB()
	{
		HashMap<String, BaseUser> allUsers = new HashMap<>();
		
		CheckFileExistance(CONSTANTS._USERS_FILE_PATH);
		File f = new File(CONSTANTS._USERS_FILE_PATH);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
			String line = "";
			while ( (line = reader.readLine()) != null )
			{
				String[] splits = line.split(CONSTANTS._DATA_SEPARATOR);

				String username = splits[0];
				String password = splits[1];
				String email = splits[2];
				int role = Integer.parseInt(splits[3].trim());
				
				BaseUser newUser = new BaseUser(username, password, email, role);
				allUsers.put(newUser.getUsername(), newUser);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allUsers;
	}
	
	/**
	 * ###### PROFESORS
	 * */
	
	public static void SaveProfesor(TeacherModel profesorToSave)
	{
		File f = new File(CONSTANTS._PROFS_FILE_PATH);
		try {
			PrintStream out = new PrintStream(new FileOutputStream(f, true), true, "UTF-8");
			out.println(profesorToSave.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveLotsOfProfesors(HashMap<String, TeacherModel> lotsOfProfesors)
	{
		CheckFileExistance(CONSTANTS._PROFS_FILE_PATH);
		deleteFileContents(new File(CONSTANTS._PROFS_FILE_PATH));
		
		for (TeacherModel teacher : lotsOfProfesors.values())
		{
			SaveProfesor(teacher);
		}
	}
	
	public static HashMap<Long, TeacherModel> ReadAllProfesorsFromDB()
	{
		HashMap<Long, TeacherModel> allProfesors = new HashMap<>();
		CheckFileExistance(CONSTANTS._PROFS_FILE_PATH);
		File f = new File(CONSTANTS._PROFS_FILE_PATH);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
			String line = "";
			while ( (line = reader.readLine()) != null )
			{
				String[] splits = line.split(CONSTANTS._DATA_SEPARATOR);

				String name = splits[0];
				String surname = splits[1];
				String subject = splits[2];
				Long id = Long.parseLong(splits[3].trim());
				String image = splits[4];
				
				TeacherModel teacher = new TeacherModel();
				teacher.setName(name);
				teacher.setSurname(surname);
				teacher.setSubject(subject);
				teacher.setId(id);
				teacher.setImageBase64(image);
				
				allProfesors.put(teacher.getId(), teacher);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allProfesors;
	}
	
	public static TeacherModel ReadOneProfesorById(Long id)
	{
		HashMap<Long, TeacherModel> allProfesors = ReadAllProfesorsFromDB();
		for(TeacherModel user : allProfesors.values())
		{
			if ( user.getId().equals(id) )
			{
				return user;
			}
		}
		
		return null;
	}
	
	
	/**
	 * ###### COMMENTS
	 * */
	
	public static void SaveComment(CommentModel commentToSave)
	{
		File f = new File(CONSTANTS._COMMS_FILE_PATH);
		try {
			PrintStream out = new PrintStream(new FileOutputStream(f, true), true, "UTF-8");
			out.println(commentToSave.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveLotsOfComments(HashMap<Long, CommentModel> lotsOfComments)
	{
		CheckFileExistance(CONSTANTS._COMMS_FILE_PATH);
		
		deleteFileContents(new File(CONSTANTS._COMMS_FILE_PATH));
		
		for (CommentModel comment : lotsOfComments.values())
		{
			SaveComment(comment);
		}
	}

	public static HashMap<Long, CommentModel> ReadAllCommentsFromDB()
	{
		HashMap<Long, CommentModel> allComments = new HashMap<>();
		CheckFileExistance(CONSTANTS._COMMS_FILE_PATH);
		File f = new File(CONSTANTS._COMMS_FILE_PATH);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
			String line = "";
			while ( (line = reader.readLine()) != null )
			{
				String[] splits = line.split(CONSTANTS._DATA_SEPARATOR);

				String usernamePosted = splits[0];
				Long profId = Long.parseLong(splits[1].trim());
				String textMessage = splits[2];
				float prof_grade = Float.parseFloat(splits[5].trim());
				float pred_grade = Float.parseFloat(splits[4].trim());
				float ocen_grade = Float.parseFloat(splits[6].trim());
				Long id = Long.parseLong(splits[3].trim());
				
				CommentModel comment = new CommentModel();
				comment.setUsernamePosted(usernamePosted);
				comment.setProfId(profId);
				comment.setTextMessage(textMessage);
				comment.setOcen_grade(ocen_grade);
				comment.setPred_grade(pred_grade);
				comment.setProf_grade(prof_grade);
				
				comment.setId(id);
				
				allComments.put(comment.getId(), comment);
				
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allComments;
	}
	
	public static CommentModel ReadOneCommentByID(Long id)
	{
		HashMap<Long, CommentModel> allComments = ReadAllCommentsFromDB();
		
		for(CommentModel comment : allComments.values())
		{
			if ( comment.getId()==id)
			{
				return comment;
			}
		}
		
		return null;
	}
	
	public static ProfAndCommentsWrapper getActiveCommentsForProf(Long profId)
	{
		ProfAndCommentsWrapper wrapper = new ProfAndCommentsWrapper();
		wrapper.setProf(DatabaseManagementSystem.ReadOneProfesorById(profId));
		
		HashMap<Long, CommentModel> comments = DatabaseManagementSystem.ReadAllCommentsFromDB();
		for(CommentModel comm : comments.values())
		{
			if ( profId.equals(comm.getProfId()))
			{
				wrapper.getComments().add(comm);
				ProfCommentWrapper pc_wrapper = new ProfCommentWrapper();
				pc_wrapper.setUsernamePosted(comm.getUsernamePosted());
				pc_wrapper.setComment(comm.getTextMessage());
				pc_wrapper.setOcenGrade(comm.getOcen_grade());
				pc_wrapper.setPredGrade(comm.getPred_grade());
				pc_wrapper.setProfGrade(comm.getProf_grade());
				pc_wrapper.setCommentId(comm.getId());
				wrapper.getCommentWrappers().add(pc_wrapper);
			}
		}
		
		return wrapper;
	}

}
