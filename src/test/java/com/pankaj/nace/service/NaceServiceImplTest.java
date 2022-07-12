package com.pankaj.nace.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.exception.NaceAlreadyExistsException;
import com.pankaj.nace.exception.NaceNotFoundException;
import com.pankaj.nace.repository.NaceRepository;

public class NaceServiceImplTest {

	@Mock
	private NaceRepository naceRepository;

	private Nace nace;

	@InjectMocks
	private NaceServiceImpl naceService;

	private List<Nace> list = new ArrayList<>();
	
	private Optional<Nace> options;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
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
		options = Optional.of(nace);
	}

	@Test
	public void createNaceSuccess() throws NaceAlreadyExistsException {
		when(naceRepository.save((Nace) any())).thenReturn(nace);
		Nace naceObj = naceService.createNace(nace);
		Assert.assertEquals(nace, naceObj);
	}

	@Test(expected = NaceAlreadyExistsException.class)
	public void createNaceFailure() throws NaceAlreadyExistsException {
		when(naceRepository.save((Nace) any())).thenReturn(null);
		Nace naceSaved = naceService.createNace(nace);
		Assert.assertEquals(nace, naceSaved);
	}

	@Test
	public void getNaceByIdSuccess() throws NaceNotFoundException {
		when(naceRepository.findById(nace.getOrderId())).thenReturn(options);
		Nace fetchedNace = naceService.getNaceById(nace.getOrderId());
		Assert.assertEquals(nace, fetchedNace);
	}

	@Test
	public void getAllNace() {
		when(naceRepository.findAll()).thenReturn(list);
		List<Nace> naceListdata = naceService.getAllNace();
		Assert.assertEquals(list, naceListdata);
	}
}