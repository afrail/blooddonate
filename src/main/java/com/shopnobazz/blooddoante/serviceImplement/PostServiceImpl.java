package com.shopnobazz.blooddoante.serviceImplement;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnobazz.blooddoante.Repository.PostRepository;
import com.shopnobazz.blooddoante.domain.Post;
import com.shopnobazz.blooddoante.domain.User;
import com.shopnobazz.blooddoante.service.PostService;




public class PostServiceImpl implements PostService{

	@Override
	public Post givepost(String discription, String date, String photo, User user) {
		
		return null;
	}
}
