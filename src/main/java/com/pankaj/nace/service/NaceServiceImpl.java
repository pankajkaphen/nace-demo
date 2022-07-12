package com.pankaj.nace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.exception.NaceAlreadyExistsException;
import com.pankaj.nace.exception.NaceNotFoundException;
import com.pankaj.nace.repository.NaceRepository;

@Service
public class NaceServiceImpl implements NaceService {

	private NaceRepository naceRepository;

	@Autowired
	public NaceServiceImpl(NaceRepository naceRepository) {
		this.naceRepository = naceRepository;
	}

	@Override
	public Nace createNace(Nace nace) throws NaceAlreadyExistsException {
		nace = naceRepository.save(nace);
		if(nace == null) {
			throw new NaceAlreadyExistsException("Nace already exist");
		} else {
			return nace;
		}
	}

	@Override
	public boolean deleteNace(String naceId) throws NaceNotFoundException {
		try {
			naceRepository.deleteById(naceId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Nace updateNace(Nace nace, String naceId) throws NaceNotFoundException {
		naceRepository.save(nace);
		
		return naceRepository.findById(naceId).get();
	}

	@Override
	public Nace getNaceById(String naceId) throws NaceNotFoundException {
		return naceRepository.findById(naceId).get();

	}

	@Override
	public List<Nace> getAllNace() {
		return naceRepository.findAll();
	}
}
