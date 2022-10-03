package poly.edu.dao.impl;

import java.util.List;

import poly.edu.dao.AbstractDao;
import poly.edu.dao.Videodao;
import poly.edu.entity.Video;

public class VideoDaoImpl extends AbstractDao<Video> implements Videodao {

	@Override
	public Video findById(Integer id) {
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String sql = "SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}
	
}
