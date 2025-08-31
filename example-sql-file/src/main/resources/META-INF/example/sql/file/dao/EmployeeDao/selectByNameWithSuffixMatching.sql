SELECT /*%expand*/*
  FROM employee
 WHERE name LIKE /* @suffix(suffix) */'%X' escape '$'
