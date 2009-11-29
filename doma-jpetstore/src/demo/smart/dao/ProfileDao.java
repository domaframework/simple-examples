package demo.smart.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;

import demo.config.AppConfig;
import demo.smart.entity.Profile;

@Dao(config = AppConfig.class)
public interface ProfileDao {

    @Insert
    int insertProfile(Profile profile);

    @Update
    int updateProfile(Profile profile);

}
