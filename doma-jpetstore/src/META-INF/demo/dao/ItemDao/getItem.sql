select
  I.ITEMID,
  I.LISTPRICE,
  I.UNITCOST,
  I.SUPPLIER AS supplierId,
  I.PRODUCTID AS "product.productId",
  NAME AS "product.name",
  DESCN AS "product.description",
  CATEGORY AS "product.categoryId",
  STATUS,
  ATTR1 AS attribute1,
  ATTR2 AS attribute2,
  ATTR3 AS attribute3,
  ATTR4 AS attribute4,
  ATTR5 AS attribute5,
  QTY AS quantity
from ITEM I, INVENTORY V, PRODUCT P
where P.PRODUCTID = I.PRODUCTID
  and I.ITEMID = V.ITEMID
  and I.ITEMID = /*itemId*/'EST-2'