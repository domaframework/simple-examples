package example.jpms;

import example.jpms.dao.EmployeeDaoImpl;
import example.jpms.dao.ScriptDaoImpl;
import example.jpms.repository.EmployeeRepository;

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
