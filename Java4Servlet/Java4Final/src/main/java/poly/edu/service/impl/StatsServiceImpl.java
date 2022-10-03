package poly.edu.service.impl;

import java.util.List;

import edu.poly.dto.VideoLikedInfo;
import poly.edu.dao.StatsDao;
import poly.edu.dao.impl.StatsDaoImpl;

import poly.edu.service.StatsService;

public class StatsServiceImpl implements StatsService{

	private StatsDao statsDao;
	
	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
	}
	
	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsDao.findVideoLikedInfo();
	}

	
}
