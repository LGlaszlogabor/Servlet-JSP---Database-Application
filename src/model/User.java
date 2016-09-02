package model;

public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private String adress;
	private String level;
	public User(){
		this.firstName = "";
		this.lastName = "";
		this.userName = "";
		this.email = "";
		this.password = "";
		this.adress = "";
		this.level = "0";
	}
	
	public User(User u){
		this(u.getId(),u.getFirstName(),u.getLastName(),u.getUserName(),u.getEmail(),u.getPassword(),u.getAdress(),u.getLevel());
	}
	
	public User(String firstName,String lastName,String userName,String email,String password, String adress,String level){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.adress = adress;	
		this.level = level;
	}
	public User(Long id,String firstName,String lastName,String userName,String email,String password, String adress,String level){
		this(firstName,lastName,userName,email,password,adress,level);
		setId(id);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return level;
	}
}

