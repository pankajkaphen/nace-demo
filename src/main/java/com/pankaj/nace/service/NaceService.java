package com.pankaj.nace.service;

import java.util.List;

import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.exception.NaceAlreadyExistsException;
import com.pankaj.nace.exception.NaceNotFoundException;

public interface NaceService {
    
    Nace createNace(Nace nace) throws NaceAlreadyExistsException;

    boolean deleteNace(String naceId) throws NaceNotFoundException;

    Nace updateNace(Nace nace, String naceId) throws NaceNotFoundException;

    Nace getNaceById(String naceId) throws NaceNotFoundException;

    List<Nace> getAllNace();
}
