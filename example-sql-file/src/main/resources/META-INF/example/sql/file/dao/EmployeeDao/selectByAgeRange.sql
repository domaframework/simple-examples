SELECT /*%expand*/*
  FROM employee
 WHERE
       /*%if min != null */
       age >= /* min */10
       /*%end */
   /*%if max != null */
   AND age <= /* max */70
   /*%end */
 ORDER BY age
