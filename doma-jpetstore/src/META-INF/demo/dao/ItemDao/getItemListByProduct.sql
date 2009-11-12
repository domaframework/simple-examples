SELECT
  ITEMID,
  LISTPRICE,
  UNITCOST,
  SUPPLIER AS supplierId,
  I.PRODUCTID AS productId,
  NAME AS productName,
  DESCN AS productDescription,
  CATEGORY AS productCategoryId,
  STATUS,
  ATTR1 AS attribute1,
  ATTR2 AS attribute2,
  ATTR3 AS attribute3,
  ATTR4 AS attribute4,
  ATTR5 AS attribute5
FROM ITEM I, PRODUCT P
WHERE P.PRODUCTID = I.PRODUCTID
AND I.PRODUCTID = /*productId*/'FI-FW-02'