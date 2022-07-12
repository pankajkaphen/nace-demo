package com.pankaj.nace.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.exception.NaceAlreadyExistsException;
import com.pankaj.nace.exception.NaceNotFoundException;
import com.pankaj.nace.service.NaceService;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest
public class NaceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private Nace nace;

	@MockBean
	private NaceService naceService;

	@InjectMocks
	private NaceController naceController;

	private List<Nace> list = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(naceController).build();
		nace = new Nace();
		nace.setCode("code");
		nace.setDescription("Description ...");
		nace.setLevel("level");
		nace.setOrderId("orderId");
		nace.setParent("parent");
		nace.setReferenceToIsicRev4("referenceToIsicRev4");
		nace.setRulings("rulings");
		nace.setThisItemAlsoIncludes("thisItemAlsoIncludes");
		nace.setThisItemExcludes("thisItemExcludes");
		nace.setThisItemIncludes("thisItemIncludes");

		list.add(nace);
	}

	@Test
	public void putNaceDetailSuccess() throws Exception {
		when(naceService.createNace(any())).thenReturn(nace);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/nace").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nace))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void putNaceDetailFailure() throws Exception {
		when(naceService.createNace(any())).thenThrow(NaceAlreadyExistsException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/nace").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nace))).andExpect(MockMvcResultMatchers.status().isConflict())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getNaceByIdSucccess() throws Exception {
		when(naceService.getNaceById(nace.getOrderId())).thenReturn(nace);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/nace/orderId").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nace))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getNaceByIdFailure() throws Exception {
		when(naceService.getNaceById(nace.getOrderId())).thenThrow(NaceNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/nace/test002").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nace))).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getAllNace() throws Exception {
		when(naceService.getAllNace()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/nace").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(list))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}