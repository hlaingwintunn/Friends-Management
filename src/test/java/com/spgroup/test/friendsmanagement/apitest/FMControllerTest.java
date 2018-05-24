package com.spgroup.test.friendsmanagement.apitest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.spgroup.test.friendsmanagement.common.request.RequestFriends;
import com.spgroup.test.friendsmanagement.common.response.ResponseFriends;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FMControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(FMControllerTest.class);
	
	  @Autowired
	  private TestRestTemplate restTemplate;
	  
	  @Autowired
	  private MessageSource messageSource;

	  private final String emailValidationMsg = "not a well-formed email address";
	  
	  @Test
	  public void friend() throws Exception {
		  List<String> emails = new ArrayList<>();
		  emails.add("aa@gmail.com");
		  emails.add("bb@gmail.com");
		  
	    final RequestFriends friendReq = new RequestFriends()
	    		.setEmails(emails);
	    
	    ResponseEntity<ResponseFriends> respEntity = restTemplate.postForEntity("/friends", friendReq, ResponseFriends.class);
	    
	    assertThat(respEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	    assertThat(respEntity.getBody()).isNotNull()
        .hasFieldOrPropertyWithValue("success", true);
	  } 
	  
}
