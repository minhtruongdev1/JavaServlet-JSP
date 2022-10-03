package poly.edu.dao;

import java.util.List;

import poly.edu.entity.History;

public interface HistoryDao {
	List<History> findByUser(String username);
	List<History> findByUserAndisLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(History entity);
	History update(History entity);
	 
}
