package __.example.common.domain;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:06:59.349+0900")
@org.seasar.doma.DomainTypeImplementation
public final class _Age extends org.seasar.doma.jdbc.domain.AbstractDomainType<java.lang.Integer, example.common.domain.Age> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private static final example.common.domain.AgeConverter converter = new example.common.domain.AgeConverter();

    private static final _Age singleton = new _Age();

    private _Age() {
        super(org.seasar.doma.internal.wrapper.WrapperSuppliers.ofInteger());
    }

    @Override
    protected example.common.domain.Age newDomain(java.lang.Integer value) {
        return converter.fromValueToDomain(value);
    }

    @Override
    protected java.lang.Integer getBasicValue(example.common.domain.Age domain) {
        if (domain == null) {
            return null;
        }
        return converter.fromDomainToValue(domain);
    }

    @Override
    public Class<?> getBasicClass() {
        return java.lang.Integer.class;
    }

    @Override
    public Class<example.common.domain.Age> getDomainClass() {
        return example.common.domain.Age.class;
    }

    /**
     * @return the singleton
     */
    public static _Age getSingletonInternal() {
        return singleton;
    }

}
