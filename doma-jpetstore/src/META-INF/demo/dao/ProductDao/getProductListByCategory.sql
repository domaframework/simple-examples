SELECT
  PRODUCTID,
  NAME,
  DESCN as description,
  CATEGORY as categoryId
FROM PRODUCT
WHERE CATEGORY = /*categoryId*/'DOGS'