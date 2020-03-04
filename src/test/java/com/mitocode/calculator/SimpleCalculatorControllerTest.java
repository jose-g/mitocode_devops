package com.mitocode.calculator;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleCalculatorController.class)
public class SimpleCalculatorControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnMessageWhoAmI() throws Exception {
		String hostname = System.getenv().getOrDefault("HOSTNAME", "Unknown");
		
        this.mockMvc.perform(get("/api/whoami/")).andExpect(status().isOk())
                        .andExpect(content().string(containsString(String.format("Hi %s", hostname)))).andDo(MockMvcResultHandlers.print());  		
	}
	@Test
	public void shouldReturnAddResult() throws Exception {
        this.mockMvc.perform(get("/api/add/12/23/")).andExpect(status().isOk())
                        .andExpect(content().string(containsString(String.format("12 + 23 = 35")))).andDo(MockMvcResultHandlers.print());  		
	}	
	@Test
	public void shouldReturnSubResult() throws Exception {
        this.mockMvc.perform(get("/api/sub/100/17/")).andExpect(status().isOk())
                        .andExpect(content().string(containsString(String.format("100 - 17 = 83")))).andDo(MockMvcResultHandlers.print());  		
	}	
	@Test
	public void shouldReturnMulResult() throws Exception {
        this.mockMvc.perform(get("/api/mul/15/154/")).andExpect(status().isOk())
                        .andExpect(content().string(containsString(String.format("15 x 154 = 2310")))).andDo(MockMvcResultHandlers.print());  		
	}	
	@Test
	public void shouldReturnDivResult() throws Exception {
        this.mockMvc.perform(get("/api/div/90/18/")).andExpect(status().isOk())
                        .andExpect(content().string(containsString(String.format("90 / 18 = 5")))).andDo(MockMvcResultHandlers.print());  		
	}	
}
