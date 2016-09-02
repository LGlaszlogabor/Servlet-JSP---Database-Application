package util;

import java.util.List;

import common.PasswordEncrypter;
import model.User;
import service.DatabaseService;

public class Validator{
    public static User getUser(String name,String pass) {
		List<User> userList = DatabaseService.getInstance().getAllUsers();
		for(User u:userList){
			if(u.getPassword().equals(PasswordEncrypter.generateHashedPassword(pass, "")) && u.getUserName().equals(name)){
				return u;
			}
		}        
		return null;
	}   
}