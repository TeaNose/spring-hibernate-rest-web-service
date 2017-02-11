package com.tinus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tinus.bo.UserReviewBo;
import com.tinus.entity.UserReview;

@RestController
public class UserReviewController {
	private static final Logger logger = Logger.getLogger(UserReviewController.class);

	@Autowired
	private UserReviewBo userReviewBo;

	@RequestMapping(value = "/getAllUserReviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
	public ResponseEntity<List<UserReview>> listAllUserReview() {
		logger.info("Get all user review list");
		List<UserReview> userReviewList = userReviewBo.getAllUserReviewList();
		logger.info("User review list result: " + userReviewList);
		if (userReviewList.isEmpty()) {
			return new ResponseEntity<List<UserReview>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserReview>>(userReviewList, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findUserReview/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
	public ResponseEntity<UserReview> findUserReviewById(@PathVariable("id") int id) {
		UserReview userReview = userReviewBo.findUserReviewById(id);

		if (userReview == null) {
			return new ResponseEntity<UserReview>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserReview>(userReview, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addUserReview", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> insertUserReview(@RequestBody UserReview userReview,
			UriComponentsBuilder uriComponentsBuilder) {
		UserReview userRev = new UserReview(userReview.getId(), userReview.getOrderId(), userReview.getProductId(),
				userReview.getUserId(), userReview.getRating(), userReview.getReview(), userReview.getCreatedAt(),
				userReview.getUpdatedAt());

		try {
			userReviewBo.insert(userRev);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(
					uriComponentsBuilder.path("list-userReview/{id}").buildAndExpand(userRev.getId()).toUri());

			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/updateUserReview", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserReview> updateUserReview(@RequestBody UserReview userReview) {
//		UserReview userRev = userReviewBo.findUserReviewById(userReview.getId());
//
//		if (userRev == null) {
//			return new ResponseEntity<UserReview>(HttpStatus.NOT_FOUND);
//		}

//		logger.info("User Review: " + userRev.getId()+", Review: "+userRev.getReview());
		userReviewBo.update(userReview);
		return new ResponseEntity<UserReview>(userReview, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteAllUserReviews", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<UserReview> deleteAllUserReview() {
		userReviewBo.deleteAll();
		return new ResponseEntity<UserReview>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/deleteUserReview/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<UserReview> deleteUserReviewById(@PathVariable("id") int id) {
		UserReview userRev = userReviewBo.findUserReviewById(id);

		if (userRev == null) {
			return new ResponseEntity<UserReview>(HttpStatus.NOT_FOUND);
		}
		userReviewBo.delete(id);
		return new ResponseEntity<UserReview>(HttpStatus.NO_CONTENT);
	}
}
