package example.jpms_java;

import example.jpms_java.dao.EmployeeDaoImpl;
import example.jpms_java.dao.ScriptDaoImpl;
import example.jpms_java.repository.EmployeeRepository;

public class Main {

  public static void main(String[] args) {
    var dbContext = DbContextFactory.create();
    var scriptDao = new ScriptDaoImpl(dbContext.config);

    dbContext.transactionManager.required(
        () -> {
          scriptDao.create();

          var repository = new EmployeeRepository(dbContext.config);
          var employee1 = repository.selectById(1);
          if (employee1 == null) throw new IllegalStateException();
          System.out.println(employee1);

          var dao = new EmployeeDaoImpl(dbContext.config);
          var employee2 = dao.selectById(1);
          if (employee2 == null) throw new IllegalStateException();
          System.out.println(employee2);
        });
  }
}
