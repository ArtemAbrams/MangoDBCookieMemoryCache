package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Aggregation("{ '$group': { '_id': null, 'maxPrice': { '$max': '$price' }}}")
    Double topProductPrice();
    @Query(value = "{'$and' : [{'name' : {'$eq' : ?0}}, {'price' : {'$gt' : ?1}}]}")
    Optional<Product> findByNameAndPriceMoreThenQuery(String name, double price);
    @Aggregation({
            "{ '$group': { '_id': null, 'avgPrice': { '$avg': '$price' } } }",
            "{ '$project': { 'roundedAvgPrice': { '$round': [ '$avgPrice', 2 ] } } }"
              })
    double averagePrice();
    @Query(value = "{}")
    List<Product> findAllProductsQuery();
    @Aggregation(pipeline = {
            "{ $match: { 'name': { $regex: ?0 } } }",
            "{ $project: { 'price': 1} }"
    })
    List<Double> matchAndProject(String value);

    Double countProductByNameLike(String name);

}
