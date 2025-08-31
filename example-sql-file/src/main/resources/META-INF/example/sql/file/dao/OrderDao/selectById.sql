SELECT /*%expand */*
  FROM "order" o
       LEFT OUTER JOIN payment pa
                    ON o.id = pa.order_id
       LEFT OUTER JOIN "user" u
                    ON o.user_id = u.id
       LEFT OUTER JOIN order_item oi
                    ON o.id = oi.order_id
       LEFT OUTER JOIN product pr
                    ON oi.product_id = pr.id
       LEFT OUTER JOIN product_category pc
                    ON pr.id = pc.product_id
       LEFT OUTER JOIN category c
                    ON pc.category_id = c.id
 WHERE o.id = /* id */0
