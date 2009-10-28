package demo.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import demo.config.AppConfig;
import demo.entity.Sequence;

@Dao(config = AppConfig.class)
public interface SequenceDao {

    @Select
    Sequence getSequence(String name);

    @Update(sqlFile = true)
    int updateSequence(Sequence sequence);

}
