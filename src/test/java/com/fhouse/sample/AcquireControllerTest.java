package com.fhouse.sample;

import com.fhouse.sample.resource.AcquirerResource;
import com.fhouse.sample.resource.AuthResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "hibernateTransactionManager")
@TestExecutionListeners({})
@RunWith(SpringJUnit4ClassRunner.class)
public class AcquireControllerTest extends AbstractControllerTest{

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new AuthResource()).build();
	}


	@Test
	public void testGetAllAcquireList() throws Exception {
		
		String uri = "/duet/all/1";

        HttpHeaders httpHeaders = getHeadersForJson();
		
		MvcResult mvcResult = mockMvc.perform(
		          MockMvcRequestBuilders.get(uri).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		        ).andReturn();
		

		        // Collect the data
		
		int status = mvcResult.getResponse().getStatus();
		
		Assert.assertEquals("Status 200 expected", 200, status);

		uri = "/duet/all/0";

		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get(uri).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		).andReturn();


		// Collect the data

		status = mvcResult.getResponse().getStatus();

		Assert.assertEquals("Status 200 expected", 200, status);
		        
	}



}
