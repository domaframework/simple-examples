UPDATE Employee
   SET NAME = /* employee.name */'test'
       , AGE = /* employee.age */10
       , DEPARTMENT_ID = /* employee.departmentId */1
       , HIREDATE = /* employee.hiredate */'2010-01-01'
       , JOB_TYPE = /* employee.jobType */'SALESMAN'
       , SALARY = /* employee.salary */300
       , UPDATE_TIMESTAMP = /* employee.updateTimestamp */'2010-01-01 12:34:56'
       , VERSION = /* employee.version */1
 WHERE ID = /* employee.id */1
