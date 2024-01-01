package com.SpringBootEx.RESTfulApp;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.SpringBootEx.RESTfulApp.datamodels.RequestData;
import com.SpringBootEx.RESTfulApp.datamodels.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ResTfulAppApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getGreetingNoName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}

	@Test
	public void getGreetingWithName() throws Exception {
		String name = "Sans";

		mvc.perform(MockMvcRequestBuilders.get("/greeting?name=" + name).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, " + name)));
	}

	@Test
	public void postResponseAndFavoriteNumberSuccess() throws Exception {
		String response = "Hi! I'm Sans, my favor";
		int favoriteNumber = 42;
		RequestData requestData = new RequestData(response, favoriteNumber);

		mvc.perform(MockMvcRequestBuilders.post("/greeting")
						.content(asJsonString(requestData))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Hi! I'm Sans, my favor, my favorite number is 42\"}"));
	}

	@Test
	public void postResponseAndFavoriteNumberFailure() throws Exception {
		String response = null;
		int favoriteNumber = 42;
		RequestData requestData = new RequestData(response, favoriteNumber);

		mvc.perform(MockMvcRequestBuilders.post("/greeting")
						.content(asJsonString(requestData))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Please enter your response\"}"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
