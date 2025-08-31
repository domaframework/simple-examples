SELECT /*%expand*/*
  FROM employee
 WHERE name LIKE /* @prefix(prefix) */'X%' escape '$'
