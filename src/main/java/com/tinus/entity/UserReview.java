package com.tinus.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_review")
public class UserReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299742764639974281L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "rating")
	private float rating;

	@Column(name = "review", length = 255)
	private String review;

	@Column(name = "created_at", length = 50)
	private String createdAt;

	@Column(name = "updated_at", length = 50)
	private String updatedAt;

	public UserReview(int id, int orderId, int productId, int userId, float rating, String review, String createdAt,
			String updatedAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.rating = rating;
		this.review = review;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserReview() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
