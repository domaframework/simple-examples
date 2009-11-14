select
  PRODUCTID,
  NAME,
  DESCN as description,
  CATEGORY as categoryId
from PRODUCT
where
/*%if keywords.size() > 0 */
  /*%for keyword : keywords */
lower(name) like /* @contain(keyword.toLowerCase()) */'a' OR lower(category) like /* @contain(keyword.toLowerCase()) */'a' OR lower(descn) like /* @contain(keyword.toLowerCase()) */'a'--hasNext " or "--
  /*%end*/
/*%end*/