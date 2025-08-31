SELECT /*%expand*/*
  FROM employee
 WHERE
       /*%if name != null*/
       name = /* name */'hoge'
       /*%else */
       AND name IS NULL
       /*%end */
