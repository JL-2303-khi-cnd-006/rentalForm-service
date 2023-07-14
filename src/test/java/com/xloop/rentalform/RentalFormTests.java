package com.xloop.rentalform;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class RentalFormTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private IRentalForm repo;

    @InjectMocks
    private RentalFormController rentalFormController;

    private JacksonTester<RentalForm> jsonform;
	private JacksonTester<List<RentalForm>> jsonforms; 

    @BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(rentalFormController).build();
	}

    @Test
    public void canAddtheForm() throws Exception {

        RentalForm form1 = new RentalForm(2,"javeria","ghar","012333333334","12345753344334",null,null, 239000);
        when(repo.save(form1)).thenReturn(form1);
        
        mvc.perform(MockMvcRequestBuilders
				.post("/rentalForm/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonform
						.write(form1)
						.getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
	public void canGetAllForm() throws Exception {
		RentalForm form1 = new RentalForm(2,"javeria","F C Area","012333333334","12345753344334",null,null, 239000);
		RentalForm form2 = new RentalForm(3,"javeria","F B Area","012333333334","12345753344334",null,null, 239000);

		List<RentalForm> formList = new ArrayList<>();
		formList.add(form1);
		formList.add(form2);

		when(repo.findAll()).thenReturn(formList);

		mvc.perform(MockMvcRequestBuilders
				.get("/rentalForm/getList")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonforms.write(formList).getJson()));
	}

    @Test 
	public void canGetFormById() throws Exception{
		RentalForm form1 = new RentalForm(2,"javeria","F C Area","012333333334","12345753344334",null,null, 239000);
		List<RentalForm> reportList = new ArrayList<>();
		reportList.add(form1);
		when(repo.findById(1L)).thenReturn(Optional.of(form1));


		mvc.perform(MockMvcRequestBuilders
				.get("/rentalForm/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonform.write(form1).getJson()));
	}


}
