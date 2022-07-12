package com.pankaj.nace.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.pankaj.nace.dto.Nace;

public class NaceItemProcessor implements ItemProcessor<Nace, Nace> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NaceItemProcessor.class);

	@Override
	public Nace process(final Nace nace) throws Exception {
		LOGGER.info("Converting ({}) into ({})", nace, nace);
		
		return nace;
	}

}
