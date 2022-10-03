package poly.edu.service;

import java.util.List;

import poly.edu.entity.History;
import poly.edu.entity.User;
import poly.edu.entity.Video;

public interface HistoryService {
	List<History> findByUser(String username); 
	List<History> findByUserAndIsLiked(String username); 
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user, Video video);
	boolean updateLikeOrUnlike(User user, String videoHref);
}
