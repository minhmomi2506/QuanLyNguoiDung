package com.example.QuanLyDanhSachNguoiDung.config.scalarConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;

@Configuration
public class ScalarConfig {
    @Bean
    public GraphQLScalarType date() {
        RuntimeWiring.newRuntimeWiring().scalar(ExtendedScalars.Date);
        return ExtendedScalars.Date;
    }
}
