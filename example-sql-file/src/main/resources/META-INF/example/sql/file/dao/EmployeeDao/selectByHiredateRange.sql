SELECT /*%expand*/*
  FROM employee
 WHERE hiredate >= /* @roundDownTimePart(from) */'2001-01-01 12:34:56'
   AND hiredate < /* @roundUpTimePart(to) */'2001-01-01 12:34:56'
