package demo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import demo.config.AppConfig;
import demo.entity.Account;

@Dao(config = AppConfig.class)
public interface AccountDao {

    @Select
    Account getAccountByUsername(String username);

    @Select
    Account getAccountByUsernameAndPassword(String username, String password);

    @Insert(sqlFile = true)
    int insertAccount(Account account);

    @Insert(sqlFile = true)
    int insertProfile(Account account);

    @Insert(sqlFile = true)
    int insertSignon(Account account);

    @Update(sqlFile = true)
    int updateAccount(Account account);

    @Update(sqlFile = true)
    int updateProfile(Account account);

    @Update(sqlFile = true)
    int updateSignon(Account account);

}
