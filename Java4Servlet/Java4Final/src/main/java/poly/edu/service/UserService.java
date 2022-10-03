package poly.edu.service;

import java.util.List;

import edu.poly.dto.UserDto;
import poly.edu.entity.User;

public interface UserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUserName(String username);
	User login(String username, String password);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber,int pageSize);
	User update(User entity);
	User delete(String username);
	User register(String username, String password, String email);
	List<UserDto> findUsersLikedVideoByVideoHref(String href);
	User create(User entity);
}
