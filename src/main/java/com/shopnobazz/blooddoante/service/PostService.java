package com.shopnobazz.blooddoante.service;

import javax.xml.crypto.Data;

import com.shopnobazz.blooddoante.domain.Post;
import com.shopnobazz.blooddoante.domain.User;

public interface PostService {
	
 public Post givepost(String discription, String date, String photo, User user);
}
