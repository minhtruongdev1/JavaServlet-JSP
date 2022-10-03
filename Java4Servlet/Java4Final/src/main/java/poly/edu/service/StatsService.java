package poly.edu.service;

import java.util.List;

import edu.poly.dto.VideoLikedInfo;



public interface StatsService {

	List<VideoLikedInfo> findVideoLikedInfo();
}
