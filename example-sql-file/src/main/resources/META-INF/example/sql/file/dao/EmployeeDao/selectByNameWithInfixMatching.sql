SELECT /*%expand*/*
  FROM employee
 WHERE name LIKE /* @infix(inside) */'%X%' escape '$'
