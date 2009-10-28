select
  PRODUCTID,
  NAME,
  DESCN as description,
  CATEGORY as categoryId
from PRODUCT
where
<dynamic prepend="WHERE">
  <iterate property="keywordList" open="" close="" conjunction="OR">
    lower(name) like #keywordList[]# OR lower(category) like #keywordList[]# OR lower(descn) like #keywordList[]#
  </iterate>
</dynamic>