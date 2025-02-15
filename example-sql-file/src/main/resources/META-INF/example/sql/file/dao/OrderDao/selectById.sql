select /*%expand */*
from "order" o
     left outer join payment pa on o.id = pa.order_id
     left outer join "user" u on o.user_id = u.id
     left outer join order_item oi on o.id = oi.order_id
     left outer join product pr on oi.product_id = pr.id
     left outer join product_category pc on pr.id = pc.product_id
     left outer join category c on pc.category_id = c.id
where o.id = /* id */0
