package example.sql.file.dao;

import example.common.entity.Product;
import example.common.entity.Review;
import example.common.entity.User;
import java.util.List;
import java.util.function.BiConsumer;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;

@Dao
public interface ReviewDao {
  @Select(aggregateStrategy = ReviewAggregateStrategy.class)
  List<Review> selectAll();
}

@AggregateStrategy(root = Review.class, tableAlias = "r")
interface ReviewAggregateStrategy {

  @AssociationLinker(propertyPath = "product", tableAlias = "pr")
  BiConsumer<Review, Product> product =
      (review, product) -> {
        review.product = product;
      };

  @AssociationLinker(propertyPath = "user", tableAlias = "u")
  BiConsumer<Review, User> user =
      (review, user) -> {
        review.user = user;
      };
}
