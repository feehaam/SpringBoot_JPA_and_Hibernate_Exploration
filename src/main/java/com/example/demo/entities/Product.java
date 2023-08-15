package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Variation> variations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();
    
    public Product() {
    	
    }
	
	public Product(int id, String name, String description, List<Variation> variations, List<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.variations = variations;
		this.reviews = reviews;
	}
	
	public static Product getDemo() {
		List<Variation> vars = new ArrayList<>();
		vars.add(new Variation(10, "16/512", 200000));
		vars.add(new Variation(20, "32/1024", 240000));
		vars.add(new Variation(30, "48/2048", 280000));
		
		List<Review> revs = new ArrayList<>();
		revs.add(new Review("Jessica sphynx", "OMG! It blows my expectation. Like literaly.", 10));
		revs.add(new Review("Barry alen", "Slow as sloth", 20));
		revs.add(new Review("Tony stark", "20 years old Stark phone as much better, LMSAO", 30));
		
		return new Product(10, "Iphone 15 elite", 
				"This is the ultimate edition of iphone 15"
				,vars, revs);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Variation> getVariations() {
		return variations;
	}

	public void setVariations(List<Variation> variations) {
		this.variations = variations;
	}
	
	public void addVariation(Variation variation) {
		if(variations == null) {
			variations = new ArrayList<>();
		}
		variations.add(variation);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(review);
	}
}
