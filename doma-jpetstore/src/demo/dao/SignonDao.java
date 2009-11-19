package demo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;

import demo.config.AppConfig;
import demo.entity.Signon;

@Dao(config = AppConfig.class)
public interface SignonDao {

    @Insert
    int insertSignon(Signon signon);

    @Update
    int updateSignon(Signon signon);

}
