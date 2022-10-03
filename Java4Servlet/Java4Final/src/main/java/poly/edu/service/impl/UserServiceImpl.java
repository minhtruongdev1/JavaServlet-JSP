package poly.edu.service.impl;

import java.util.List;

import edu.poly.dto.UserDto;
import poly.edu.dao.UserDao;
import poly.edu.dao.impl.UserDaoimpl;
import poly.edu.entity.User;
import poly.edu.service.UserService;

public class UserServiceImpl implements UserService {

private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoimpl();
	}

	@Override
	public User findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		
		return dao.findByEmail(email);
	}
	@Override
    public User create(User entity) {
        entity.setIsActive(Boolean.TRUE);
        entity.setIsAdmin(Boolean.TRUE);
        return dao.create(entity);
    }

	@Override
	public User findByUserName(String username) {
	
		return dao.findByUserName(username);
	}

	@Override
	public User login(String username, String password) {
		
		return dao.findByUserNameAndPassword(username, password);
	}


	@Override
	public User resetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User update(User entity) {
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		User user = dao.findByUserName(username);//Tim kiem theo ten
		user.setIsActive(Boolean.FALSE);
		return dao.update(user);
	}

	@Override
	public User register(String username, String password, String email) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public List<UserDto> findUsersLikedVideoByVideoHref(String href) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
