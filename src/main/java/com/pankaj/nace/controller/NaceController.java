package com.pankaj.nace.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.nace.dto.Nace;
import com.pankaj.nace.exception.NaceAlreadyExistsException;
import com.pankaj.nace.model.NaceBean;
import com.pankaj.nace.service.NaceService;

import io.swagger.annotations.ApiOperation;

@RestController
public class NaceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NaceController.class);

	private NaceService naceService;

	@Autowired
	public NaceController(NaceService naceService) {
		this.naceService = naceService;
	}

	@GetMapping("/api/v1/nace/{id}")
	@ApiOperation(value = "Get Nace using url: /api/v1/nace/id")
	@Cacheable(cacheNames = "naceCache", key = "#id")
	public ResponseEntity<NaceBean> getNaceDetail(@PathVariable String id) {
		LOGGER.info("Request received for GET /api/v1/nace/id: {}", id);

		try {
			Nace nace = naceService.getNaceById(id);
			if (nace == null) {
				return new ResponseEntity<NaceBean>(HttpStatus.NOT_FOUND);
			} else {
				NaceBean naceBean= new NaceBean();
				BeanUtils.copyProperties(naceBean, nace);
				return new ResponseEntity<NaceBean>(naceBean, HttpStatus.OK);
			}

		} catch (Exception e) {
			LOGGER.error("Error occurred {}", e.getMessage());
			return new ResponseEntity<NaceBean>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/v1/nace")
	@ApiOperation(value = "Get all nace using url: /api/v1/nace")
	public ResponseEntity<List<Nace>> getNaceDetail() {
		LOGGER.info("Request received for GET /api/v1/nace");

		try {
			return new ResponseEntity<List<Nace>>(naceService.getAllNace(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error occurred {}", e.getMessage());
			return new ResponseEntity<List<Nace>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/api/v1/nace")
	@ApiOperation(value = "Create Nace using url: /api/v1/nace")
	public ResponseEntity<Nace> putNaceDetail(@RequestBody Nace nace) {
		try {
			naceService.createNace(nace);
			return new ResponseEntity<Nace>(nace, HttpStatus.CREATED);

		} catch (NaceAlreadyExistsException e) {
			LOGGER.error("Error occurred {}", e.getMessage());
			return new ResponseEntity<Nace>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public void swaggerUi(HttpServletResponse response) throws IOException {
		 response.sendRedirect("swagger-ui.html");
	}
}