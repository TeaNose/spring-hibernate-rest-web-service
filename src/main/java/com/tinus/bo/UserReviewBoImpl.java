package com.tinus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tinus.dao.UserReviewDao;
import com.tinus.entity.UserReview;

public class UserReviewBoImpl implements UserReviewBo {
	@Autowired
	private UserReviewDao userReviewDao;

	public UserReviewDao getUserReviewDao() {
		return userReviewDao;
	}

	public void setUserReviewDao(UserReviewDao userReviewDao) {
		this.userReviewDao = userReviewDao;
	}

	public void insert(UserReview userReview) {
		userReviewDao.insert(userReview);
	}

	public UserReview findUserReviewById(int id) {
		return userReviewDao.findUserReviewById(id);
	}

	public List<UserReview> getAllUserReviewList() {
		return userReviewDao.getAllUserReviewList();
	}

	public void update(UserReview userReview) {
		userReviewDao.update(userReview);
	}

	public void deleteAll() {
		userReviewDao.deleteAll();
	}

	public void delete(int id) {
		userReviewDao.delete(id);
	}
}
