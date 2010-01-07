select * from employee where 
/*%if ages.size() > 0 */
  /*%for age : ages */
  age = /* age */30
    /*%if age_has_next */
  or
    /*%end */
  /*%end */
/*%end */