package springfive.cms.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import springfive.cms.domain.models.Category;

import java.util.List;

@Service
public interface CategoryRepository extends MongoRepository<Category, String> {

}