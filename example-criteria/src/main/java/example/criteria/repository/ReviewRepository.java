package example.criteria.repository;

import example.common.entity.Product_;
import example.common.entity.Review;
import example.common.entity.Review_;
import example.common.entity.User_;
import java.util.List;
import java.util.Objects;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.QueryDsl;

public class ReviewRepository {
  private final QueryDsl queryDsl;

  public ReviewRepository(Config config) {
    Objects.requireNonNull(config);
    queryDsl = new QueryDsl(config);
  }

  public List<Review> selectAll() {
    Review_ r = new Review_();
    Product_ pr = new Product_();
    User_ u = new User_();

    return queryDsl
        .from(r)
        .leftJoin(pr, on -> on.eq(r.productId, pr.id))
        .leftJoin(u, on -> on.eq(r.userId, u.id))
        .associate(r, pr, (review, product) -> review.product = product)
        .associate(r, u, (review, user) -> review.user = user)
        .fetch();
  }
}
