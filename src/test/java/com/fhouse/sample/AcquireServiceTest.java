package com.fhouse.sample;

import com.fhouse.sample.model.Acquirer;
import com.fhouse.sample.model.dto.AcquirerDTO;
import com.fhouse.sample.service.AcquirerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Tests for the account service
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class AcquireServiceTest {

    @Autowired
    private AcquirerService acquirerService;


    @Before
    public void setUp(){
        // Nothing yet
    }

    @After
    public void tearDown(){
        // Nothing yet
    }


    @Test
    public void testGetAllAccounts(){


        // Get all accounts from database
        @SuppressWarnings("unused")
        List<AcquirerDTO> accountCollection = acquirerService.findAll();

        // Accounts should be null
        Assert.assertNotNull("failure - Accounts collection should not be null");
    }




}
