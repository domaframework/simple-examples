select /*%expand */*
from review r
     left outer join product pr on r.product_id = pr.id
     left outer join "user" u on r.user_id = u.id
order by r.id
