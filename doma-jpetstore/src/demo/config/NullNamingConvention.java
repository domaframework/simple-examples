package demo.config;

import org.seasar.doma.jdbc.entity.NamingConvention;

public class NullNamingConvention implements NamingConvention {

    @Override
    public String fromEntityToTable(String entityName) {
        return entityName;
    }

    @Override
    public String fromPropertyToColumn(String propertyName) {
        return propertyName;
    }

}
