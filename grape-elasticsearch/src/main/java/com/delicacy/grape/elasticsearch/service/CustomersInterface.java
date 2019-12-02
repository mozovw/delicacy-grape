package com.delicacy.grape.elasticsearch.service;


import com.delicacy.grape.elasticsearch.model.Customer;

import java.util.List;

public interface CustomersInterface {

    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
