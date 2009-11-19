SELECT
  PRODUCTID,
  NAME,
  DESCN,
  CATEGORY
FROM 
  PRODUCT
WHERE
/*%if keywords.size() > 0 */
  /*%for keyword : keywords */
  lower(name) like /* @contain(keyword.toLowerCase()) */'a' 
  OR 
  lower(category) like /* @contain(keyword.toLowerCase()) */'a' 
  OR 
  lower(descn) like /* @contain(keyword.toLowerCase()) */'a'
/*%hasNext "  OR"*/
  /*%end*/
/*%end*/
