package com.sistema.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLScalarConfiguration {
	
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
            .scalar(GraphQLScalarType.newScalar()
                .name("Long")
                .description("Long type")
                .coercing(new graphql.schema.Coercing<Long, Long>() {
                    @Override
                    public Long serialize(Object dataFetcherResult) {
                        return dataFetcherResult instanceof Long ? (Long) dataFetcherResult : null;
                    }

                    @Override
                    public Long parseValue(Object input) {
                        if (input instanceof Integer) {
                            return ((Integer) input).longValue();
                        }
                        return input instanceof Long ? (Long) input : null;
                    }

                    @Override
                    public Long parseLiteral(Object input) {
                        if (input instanceof graphql.language.IntValue) {
                            return ((graphql.language.IntValue) input).getValue().longValue();
                        }
                        return null;
                    }
                })
                .build());
    }

}
