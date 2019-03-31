package com.fhouse.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Helps the controller testings
 */
@WebAppConfiguration
@ContextConfiguration
public class AbstractControllerTest{

    protected MockMvc mvc;
    

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }
    
    @Test
    public void test() {
    	
    }
    
    protected HttpHeaders getHeadersForJson() throws Exception {
		String token = getAuthorizationToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer "+token);
        
        return httpHeaders;
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected void setUp(BaseController controller){
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    protected String getAuthorizationToken() throws Exception {
    		String pass = "Q4star7oke";
    		String cs = "123456";
    		String url = "/oauth/token?password="+pass+"&username=selimoruc&grant_type=password&scope(0)=read&scope(1)=write&client_secret="+cs+"&client_id=clientapp";
    	
    		HttpHeaders httpHeaders = new HttpHeaders();
    		httpHeaders.add("Authorization", "Basic Y2xpZW50YXBwOjEyMzQ1Ng==");
    		
    		httpHeaders.add("Accept", "application/json");
    		
    		MvcResult mvcResult = mvc.perform(
    		          MockMvcRequestBuilders.post(url).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("")
    		        ).andReturn();
    		
    		JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(mvcResult.getResponse().getContentAsString()).get("access_token").toString();
    }
    

}
