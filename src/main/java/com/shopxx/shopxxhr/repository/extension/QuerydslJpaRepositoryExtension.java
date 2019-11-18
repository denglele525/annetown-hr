package com.shopxx.shopxxhr.repository.extension;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface QuerydslJpaRepositoryExtension<T, ID extends Serializable> extends QuerydslPredicateExecutor<T> {

}