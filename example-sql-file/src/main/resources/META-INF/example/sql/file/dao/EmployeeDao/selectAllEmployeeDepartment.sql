SELECT /*%expand */*
  FROM employee e
       LEFT OUTER JOIN department d
                    ON e.department_id = d.id
 ORDER BY e.id
