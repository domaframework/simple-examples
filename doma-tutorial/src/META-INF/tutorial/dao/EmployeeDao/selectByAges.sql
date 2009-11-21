select * from employee where 
/*%if ages.size() > 0 */
  /*%for age : ages */
  age = /* age */30
/*%hasNext "  or"*/
  /*%end */
/*%end */