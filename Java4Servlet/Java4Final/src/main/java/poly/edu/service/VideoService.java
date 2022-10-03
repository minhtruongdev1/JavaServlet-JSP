package poly.edu.service;

import java.util.List;

import poly.edu.entity.Video;

public interface VideoService {
	Video findById(Integer Id);
	Video findByHref(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber, int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(String href);
	
}
