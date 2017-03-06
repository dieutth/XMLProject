package server.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Book implements Serializable  {
	private static final long serialVersionUID = -8809324453272440314L;

	private String title;
	
//	@XmlElement
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	private String goodreadReviews;
	
	
	private List<Review> reviews;
	
	@XmlElement
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	public void setGoodReadReviews(String reviews) {
		this.goodreadReviews = reviews;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public String getTitle(){
		return title;
	}
	
}
