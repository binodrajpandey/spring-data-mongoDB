/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenhandshake.demo;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author binod
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongodbExampleApplication.class})
public class ItCustomerRepository {
    @Autowired
	private CustomerRepository repository;
    
    @After
    public void after(){
        repository.deleteAll();
    }
    @Test
    public void saveDocument(){
        Customer customer =new Customer("Alice", "Smith");
        repository.save(customer);
      List<Customer> customers=  repository.findByLastName("Smith");
        assertThat(customers.get(0).getFirstName(),is(equalTo(customer.getFirstName())));
        
    }
    @Test
    public void updateDocument(){
        //save Document
        Customer customer =new Customer("Binod", "Smith");
        repository.save(customer);
        //update document
        Customer toBeUpdatedCustomer=repository.findByFirstName(customer.getFirstName());
        customer.setLastName("Pandey");
        repository.save(toBeUpdatedCustomer);
        
        //now verify verify
        Customer updatedCustomer=repository.findByFirstName(customer.getFirstName());
        assertThat(updatedCustomer.getLastName(),is(equalTo(toBeUpdatedCustomer.getLastName())));
        
    }
    
    @Test
    public void deleteDocument(){
         //save Document
        Customer customer =new Customer("Binod", "Smith");
        repository.save(customer);
        
        //delete document
        Customer toBeDeletedCustomer=repository.findByFirstName(customer.getFirstName());
        repository.delete(toBeDeletedCustomer);
        
        //now verify the result
        Customer deletedCustomer=repository.findByFirstName(customer.getFirstName());
        Assert.assertNull(deletedCustomer);
        
    }
}
