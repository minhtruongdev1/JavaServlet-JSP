package poly.edu.dao.impl;

import java.util.List;

import poly.edu.dao.AbstractDao;
import poly.edu.dao.UserDao;
import poly.edu.entity.User;

public class UserDaoimpl extends AbstractDao<User> implements UserDao {

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUserName(String username) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUserNameAndPassword(String username, String password) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0 and o.password = ?1";
		return super.findOne(User.class, sql, username, password);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true);
	}
	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true,pageNumber, pageSize);
	}
	

}
