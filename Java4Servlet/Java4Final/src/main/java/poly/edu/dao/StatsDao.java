package poly.edu.dao;

import java.util.List;

import edu.poly.dto.VideoLikedInfo;




public interface StatsDao {

	List<VideoLikedInfo> findVideoLikedInfo();
	
}
