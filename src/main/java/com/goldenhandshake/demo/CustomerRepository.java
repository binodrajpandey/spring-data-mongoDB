/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenhandshake.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author binod
 */
public interface CustomerRepository extends MongoRepository<Customer,String>{
    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
    
}
