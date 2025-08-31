SELECT /*%expand */*
  FROM review r
       LEFT OUTER JOIN product pr
                    ON r.product_id = pr.id
       LEFT OUTER JOIN "user" u
                    ON r.user_id = u.id
 ORDER BY r.id
