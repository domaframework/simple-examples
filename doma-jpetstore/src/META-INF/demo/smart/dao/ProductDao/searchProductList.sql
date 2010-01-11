SELECT
  PRODUCTID,
  NAME,
  DESCN,
  CATEGORY
FROM 
  PRODUCT
WHERE
/*%for keyword : keywords */
  lower(name) like /* @contain(keyword.toLowerCase()) */'a' 
  OR 
  lower(category) like /* @contain(keyword.toLowerCase()) */'a' 
  OR 
  lower(descn) like /* @contain(keyword.toLowerCase()) */'a'
  /*%if keyword_has_next */
  /*# "OR" */
  /*%end*/
/*%end*/
