package com.shopxx.shopxxhr.repository.extension;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import java.io.Serializable;

public class QuerydslJpaRepositoryExtensionImpl<T, ID extends Serializable> extends QuerydslJpaPredicateExecutor<T> implements QuerydslJpaRepositoryExtension<T, ID> {

    /**
     * 初始化resolver
     */
    private static final EntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;

    @Nonnull
    private final EntityPath<T> path;
    @Nonnull
    private final JPAQueryFactory jpaQueryFactory;

    public QuerydslJpaRepositoryExtensionImpl(@Nonnull JpaEntityInformation<T, ?> entityInformation, @Nonnull EntityManager entityManager, @Nonnull JPAQueryFactory jpaQueryFactory) {
        super(entityInformation, entityManager, resolver, null);

        this.path = resolver.createPath(entityInformation.getJavaType());
        this.jpaQueryFactory = jpaQueryFactory;
    }

}