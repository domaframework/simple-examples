package example.sql.annotation;

import example.common.entity.Product;
import example.common.entity.Review;
import example.common.entity.User;
import java.util.List;
import java.util.function.BiFunction;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface ReviewDao {
  @Sql(
      """
  select /*%expand */*
  from review r
  left outer join product pr on r.product_id = pr.id
  left outer join `user` u on r.user_id = u.id
  order by r.id
  """)
  @Select(aggregateStrategy = ReviewAggregateStrategy.class)
  List<Review> selectAll();
}

@AggregateStrategy(root = Review.class, tableAlias = "r")
interface ReviewAggregateStrategy {

  @AssociationLinker(propertyPath = "product", tableAlias = "pr")
  BiFunction<Review, Product, Review> product =
      (review, product) -> {
        review.product = product;
        return review;
      };

  @AssociationLinker(propertyPath = "user", tableAlias = "u")
  BiFunction<Review, User, Review> user =
      (review, user) -> {
        review.user = user;
        return review;
      };
}
