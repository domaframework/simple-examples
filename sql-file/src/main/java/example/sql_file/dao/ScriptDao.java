package example.sql_file.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

@Dao
public interface ScriptDao {

  @Script
  void create();

  @Script
  void drop();
}
