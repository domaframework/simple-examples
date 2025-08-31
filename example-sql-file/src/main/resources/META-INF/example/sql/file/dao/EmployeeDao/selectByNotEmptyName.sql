SELECT /*%expand*/*
  FROM employee
 WHERE
       /*%if @isNotEmpty(name) */
       name = /* name */'hoge'
       /*%end*/
