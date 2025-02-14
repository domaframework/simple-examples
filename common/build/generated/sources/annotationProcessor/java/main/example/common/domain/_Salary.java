package example.common.domain;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:39:41.857+0900")
@org.seasar.doma.DomainTypeImplementation
public final class _Salary extends org.seasar.doma.jdbc.domain.AbstractDomainType<java.lang.Integer, example.common.domain.Salary> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private static final _Salary singleton = new _Salary();

    private _Salary() {
        super(org.seasar.doma.internal.wrapper.WrapperSuppliers.ofInteger());
    }

    @Override
    protected example.common.domain.Salary newDomain(java.lang.Integer value) {
        if (value == null) {
            return null;
        }
        return new example.common.domain.Salary(value);
    }

    @Override
    protected java.lang.Integer getBasicValue(example.common.domain.Salary domain) {
        if (domain == null) {
            return null;
        }
        return domain.value();
    }

    @Override
    public Class<?> getBasicClass() {
        return java.lang.Integer.class;
    }

    @Override
    public Class<example.common.domain.Salary> getDomainClass() {
        return example.common.domain.Salary.class;
    }

    /**
     * @return the singleton
     */
    public static _Salary getSingletonInternal() {
        return singleton;
    }

}
