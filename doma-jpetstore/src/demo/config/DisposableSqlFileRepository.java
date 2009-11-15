package demo.config;

import org.seasar.doma.jdbc.CachedSqlFileRepository;
import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;

public class DisposableSqlFileRepository extends CachedSqlFileRepository
        implements Disposable {

    public DisposableSqlFileRepository() {
        DisposableUtil.add(this);
    }

    @Override
    public void dispose() {
        sqlFileMap.clear();
    }

}
