package com.delicacy.grape.elasticsearch.service.impl;


import com.delicacy.grape.elasticsearch.model.Customer;
import com.delicacy.grape.elasticsearch.repository.CustomerRepository;
import com.delicacy.grape.elasticsearch.service.CustomersInterface;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomersInterfaceImpl implements CustomersInterface {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // Function Score Query
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("cityname", searchContent)),
//        ScoreFunctionBuilders.weightFactorFunction(1000)
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(1000))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100));

        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)/*
                .withQuery(functionScoreQueryBuilder)*/.build();
        log.info("searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());
        Page<Customer> searchPageResults = customerRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
