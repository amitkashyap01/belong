package com.telecom.belong.integration;

import com.telecom.belong.BelongApplication;
import com.telecom.belong.exception.BelongAPIErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BelongApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BelongApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testGetAllPhoneNumbers(){
		final String endpoint = "/v1/phone/phoneNumber";

		ResponseEntity<List> responseEntity = testRestTemplate
				.getForEntity(getCompleteURI(endpoint), List.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5, responseEntity.getBody().size());
	}

	@Test
	public void testGetAllPhoneNumbersForACustomer(){
		final String endpoint = "/v1/phone/phoneNumber/cust001";

		ResponseEntity<List> responseEntity = testRestTemplate
				.getForEntity(getCompleteURI(endpoint), List.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, responseEntity.getBody().size());
	}

	@Test
	public void testGetAllPhoneNumbersForAUnknownCustomer(){
		final String endpoint = "/v1/phone/phoneNumber/cust004";

		ResponseEntity<BelongAPIErrorResponse> responseEntity = testRestTemplate
				.getForEntity(getCompleteURI(endpoint), BelongAPIErrorResponse.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("No phone numbers found for customer cust004", responseEntity.getBody().getMessage());
	}


	@Test
	public void testActivateAPhoneNumber(){
		final String endpoint = "/v1/phone/phoneNumber/046310000001";

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<Boolean> responseEntity = testRestTemplate
				.exchange(getCompleteURI(endpoint), HttpMethod.PUT, entity, Boolean.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(true, responseEntity.getBody());
	}

	@Test
	public void testActivateUnknowPhoneNumber(){
		final String endpoint = "/v1/phone/phoneNumber/04631000009";

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<Boolean> responseEntity = testRestTemplate
				.exchange(getCompleteURI(endpoint), HttpMethod.PUT, entity, Boolean.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(false, responseEntity.getBody());
	}

	@Test
	public void testGetAllPhoneDetails(){
		final String endpoint = "/v1/phone/";

		ResponseEntity<List> responseEntity = testRestTemplate
				.getForEntity(getCompleteURI(endpoint), List.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5, responseEntity.getBody().size());
	}

	private String getCompleteURI(final String endpoint){
		return "http://localhost:"+port+endpoint;
	}
}
