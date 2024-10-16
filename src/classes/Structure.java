package classes;

import javax.swing.ImageIcon;

public class Structure {
	
	private Integer id;
	private String title;
	private String country;
	private String address;
	private String price;
	private Integer rating_average;
	private ImageIcon picture;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getRating_average() {
		return rating_average;
	}
	public void setRating_average(Integer rating_average) {
		this.rating_average = rating_average;
	}
	
	public ImageIcon getPicture() {
		return picture;
	}
	public void setPicture(ImageIcon image) {
		this.picture = image;
	}
	
	public Structure(Integer id, String title, String country, String address, String price,
			Integer rating_average, ImageIcon picture) {
		
		this.setId(id);
		this.setTitle(title);
		this.setCountry(country);
		this.setAddress(address);
		this.setPrice(price);
		this.setRating_average(rating_average);
		this.setPicture(picture);
		
	}
	
	
}
