package server.dao;

import server.model.Book;

public interface BookDAO{
	boolean insertReview(String title, String review);
	 boolean deleteReview();
	 boolean updateReview();
}
