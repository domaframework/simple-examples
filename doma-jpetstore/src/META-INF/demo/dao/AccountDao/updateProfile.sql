UPDATE PROFILE 
SET
  LANGPREF =/*account.languagePreference*/'JAPANESE',
  FAVCATEGORY =/*account.favouriteCategoryId*/'DOGS',
  MYLISTOPT =/*account.listOption*/0,
  BANNEROPT =/*account.bannerOption*/0
WHERE 
  USERID =/*account.username*/'username'