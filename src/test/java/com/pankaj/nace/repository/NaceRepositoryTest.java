package com.pankaj.nace.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pankaj.nace.dto.Nace;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NaceRepositoryTest {

    @Autowired
    private NaceRepository naceRepository;

    private Nace nace;

    @Before
    public void setUp() throws Exception {
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
    }

    @After
    public void tearDown() throws Exception {
        naceRepository.deleteAll();
    }

    @Test
    public void saveNaceSuccess() {
        naceRepository.save(nace);
        Nace object = naceRepository.findById(nace.getOrderId()).get();
        Assert.assertEquals(nace.getOrderId(), object.getOrderId());
    }
}
