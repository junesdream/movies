package dev.rainbow.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.runtime.ObjectMethods;
@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
