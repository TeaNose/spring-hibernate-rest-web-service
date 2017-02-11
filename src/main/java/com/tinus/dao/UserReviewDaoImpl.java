package com.tinus.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinus.entity.UserReview;

@Repository
public class UserReviewDaoImpl implements UserReviewDao {
	private static final Logger logger = Logger.getLogger(UserReviewDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public UserReview findUserReviewById(int id) {
		List<UserReview> userReviewList = this.sessionFactory.getCurrentSession()
				.createQuery("FROM UserReview WHERE id = :id").setParameter("id", id).list();
		if (userReviewList.isEmpty()) {
			return null;
		} else {
			return userReviewList.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserReview> getAllUserReviewList() {
		logger.info("Get user review list in user review dao impl");
		List<UserReview> userReviewList = this.sessionFactory.getCurrentSession().createQuery("FROM UserReview").list();
		logger.info("User review result list dao impl: " + userReviewList);
		return userReviewList;
	}

	@Transactional
	public void update(UserReview userReview) {
		this.sessionFactory.getCurrentSession().update(userReview);
	}

	@Transactional
	public void deleteAll() {
		Query query = this.sessionFactory.getCurrentSession().createQuery("DELETE FROM UserReview");
		query.executeUpdate();
	}

	@Transactional
	public void delete(int id) {
		UserReview userReview = findUserReviewById(id);
		if (userReview != null) {
			this.sessionFactory.getCurrentSession().delete(userReview);
		}

	}

	@Transactional
	public void insert(UserReview userReview) {
		this.sessionFactory.getCurrentSession().save(userReview);
	}

}
