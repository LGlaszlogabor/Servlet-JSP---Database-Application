package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.User;
import repository.RepositoryException;
import repository.UserDAO;


public class JdbcUserDAO implements UserDAO {
	private final ConnectionManager cm;
	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
	public JdbcUserDAO() {
		cm = ConnectionManager.getInstance ();
	}
	@Override 
	public List<User> getAllUsers () throws RepositoryException { 
		final List<User> userList = new ArrayList<User> (); 
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement ();
			ResultSet rs = stmt.executeQuery ("select * from User"); 
			while (rs.next ()) {
				userList.add(new User(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
		} 
		catch (final SQLException e) { 
			LOG.error("User selection error!!");
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
		return userList;
	}
	
	@Override
	public User getUserById(Long id) throws RepositoryException {
		User user = null;
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement ();
			ResultSet rs = stmt.executeQuery ("select * from User where ID = "+id); 
			user = new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
		} 
		catch (final SQLException e) { 
			LOG.error("User selection error!!",e);
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
		return user;
	}
	@Override
	public List<User> getUserByFilter(String pattern) throws RepositoryException {
		final List<User> userList = new ArrayList<User> (); 
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement ();
			ResultSet rs = stmt.executeQuery ("select * from User where `First Name` like '%"+pattern+"%' or `Last Name` like '%"+pattern+"%' or `User Name` like '%"+pattern+"%'");
			while (rs.next ()) {
				userList.add(new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
		} 
		catch (final SQLException e) { 
			LOG.error("User filter selection error!!",e);
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
		return userList;
	}
	@Override
	public void insertUser(User user) throws RepositoryException { //uuid
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement ();
			stmt.execute("insert into user (`First Name`, `Last Name`, `User Name`, `Email`, `Password`, `Adress`, `Login Level`) VALUES ('"+user.getFirstName()+"','"+user.getLastName()+"','"
														+user.getUserName()+"','"+user.getEmail()+"','"
														+user.getPassword()+"','"+user.getAdress()+"', '0')"); 
		} 
		catch (final SQLException e) { 
			LOG.error("User insert error!!",e);
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
	}
	@Override
	public void updateUser(User user) throws RepositoryException {
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement ();
			stmt.execute("update user set `First Name`='"+user.getFirstName()+"', `Last Name`='"+user.getLastName()
												+"', `User Name`='"+user.getUserName()+"', `Email`='"+user.getEmail()
												+"', `Password`='"+user.getPassword()+"', `Adress`='"+user.getAdress()+
												"' where id = " +user.getId());
		} 
		catch (final SQLException e) { 
			LOG.error("User update error!!",e);
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
	}
	@Override
	public void deleteUser(User user) throws RepositoryException {
		Connection con = null; 
		try {
			con = cm.getConnection ();
			Statement stmt = con.createStatement();
			stmt.execute("delete from user where id = " +user.getId());
		} 
		catch (final SQLException e) { 
			LOG.error("User delete error!!",e);
			throw new RepositoryException();
		}
		finally { 
			if (con != null) { 
				cm.returnConnection (con); 
			} 
		} 
	}
}

