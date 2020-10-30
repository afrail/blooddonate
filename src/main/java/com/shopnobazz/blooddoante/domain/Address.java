package com.shopnobazz.blooddoante.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String division;
	private String distric;
    private String upazila;
    private String others;
    
   
    @ManyToOne
	@JoinColumn(name="user_id")
	private User user;
    
    public Address() {
    	
    }
	public Address(String division, String distric, String upazila, String others,String type,User user) {
		
		
		this.division = division;
		this.distric = distric;
		this.upazila = upazila;
		this.others = others;
		this.type = type;
		this.user=user;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDistric() {
		return distric;
	}
	public void setDistric(String distric) {
		this.distric = distric;
	}
	public String getUpazila() {
		return upazila;
	}
	public void setUpazila(String upazila) {
		this.upazila = upazila;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
    
    
    
}
