package classes;


import dao_classes.ReviewDAO;
import exceptions.OperationFailedException;


public class Review {
	
	private Integer id;
	private Integer rating;
	private String body;
	private Boolean is_public;
	private String reason;
	
	private User writer;
	private Integer structure_reference;
	private User moderator;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getStructure_reference() {
		return structure_reference;
	}
	public void setStructure_reference(Integer structure_reference) {
		this.structure_reference = structure_reference;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {

		this.writer = writer;
	}

	public Boolean getIs_public() {
		return is_public;
	}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public User getModerator() {
		return moderator;
	}
	public void setModerator(User moderator) {
		this.moderator = moderator;
	}

	
	public Review(Integer rating, String body, User writer, Integer structure_reference){
		
		this.setRating(rating);
		this.setBody(body);
		this.setWriter(writer);
		this.setStructure_reference(structure_reference);

	}
	
	public Review(Integer id, Integer rating, String body, Boolean is_public, String reason, User writer, Integer structure_reference, User moderator){
		
		this.setId(id);
		this.setRating(rating);
		this.setBody(body);
		this.setIs_public(is_public);
		this.setReason(reason);
		this.setWriter(writer);
		this.setStructure_reference(structure_reference);
		this.setModerator(moderator);

	}
	
	public void addReview() throws OperationFailedException {
		
		ReviewDAO db_instance = new ReviewDAO();	
		db_instance.addNewReviewToDB(this);
		
	}	
	
	public void ApproveReview(Integer review_id, Integer moderator_id) throws OperationFailedException {
		
		ReviewDAO db_instance = new ReviewDAO();	
		
		db_instance.approveReview(review_id, moderator_id);
		
	}	
	
	public void RejectReview(Integer review_id, Integer moderator_id, String reason) throws OperationFailedException {
		
		ReviewDAO db_instance = new ReviewDAO();	

		db_instance.rejectReview(review_id, moderator_id, reason);
		
	}	
	
	public Boolean checkIfExistsAReviewByUserId(Integer structure_id, Integer user_id) throws OperationFailedException{
		
		ReviewDAO db_instance = new ReviewDAO();	
		
		return db_instance.checkIfExistsAReviewByUserId(structure_id, user_id);
		
	}
	
}