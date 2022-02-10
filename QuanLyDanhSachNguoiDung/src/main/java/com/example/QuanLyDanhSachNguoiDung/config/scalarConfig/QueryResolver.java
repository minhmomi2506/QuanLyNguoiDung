package com.example.QuanLyDanhSachNguoiDung.config.scalarConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    @Autowired
    private UserRepo userRepo;
    
    @Value("classpath:user.graphqls")
    private Resource schemaResource;

    @PostConstruct
    public GraphQL loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return graphQL;
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<User>> dataFetcher = data -> {
            return userRepo.findAll();
        };
        DataFetcher<User> dataFetcher2 = data -> {
            return userRepo.findUserById(data.getArgument("id"));
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting -> typeWriting.dataFetcher("getAllUsers", dataFetcher).dataFetcher("findUserById",
                                        dataFetcher2))
                        .build();
    }
    
    /* GET ALL USERS */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    /* FIND USER BY ID */
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }
}
