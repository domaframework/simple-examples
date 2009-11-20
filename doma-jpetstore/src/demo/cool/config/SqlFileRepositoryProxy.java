package demo.cool.config;

import org.seasar.doma.jdbc.AbstractSqlFileRepository;
import org.seasar.doma.jdbc.GreedyCacheSqlFileRepository;
import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFile;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.framework.container.hotdeploy.HotdeployUtil;

public class SqlFileRepositoryProxy extends AbstractSqlFileRepository {

    private final SqlFileRepository greedyCacheSqlFileRepository = new GreedyCacheSqlFileRepository();

    private final SqlFileRepository noCacheSqlFileRepository = new NoCacheSqlFileRepository();

    @Override
    protected SqlFile getSqlFileWithCacheControl(String path, Dialect dialect) {
        if (HotdeployUtil.isHotdeploy()) {
            return noCacheSqlFileRepository.getSqlFile(path, dialect);
        }
        return greedyCacheSqlFileRepository.getSqlFile(path, dialect);
    }

}
