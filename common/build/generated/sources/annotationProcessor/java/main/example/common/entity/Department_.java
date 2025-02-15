package example.common.entity;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:44:03.573+0900")
public final class Department_ implements org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel<example.common.entity.Department> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private final String __qualifiedTableName;

    private final example.common.entity._Department __entityType = example.common.entity._Department.getSingletonInternal();

    private final java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __allPropertyMetamodels;

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> id = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "id");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.String> name = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.String>(java.lang.String.class, __entityType, "name");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> version = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "version");

    public Department_() {
        this("");
    }

    public Department_(String qualifiedTableName) {
        this.__qualifiedTableName = java.util.Objects.requireNonNull(qualifiedTableName);
        java.util.ArrayList<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __list = new java.util.ArrayList<>(3);
        __list.add(id);
        __list.add(name);
        __list.add(version);
        __allPropertyMetamodels = java.util.Collections.unmodifiableList(__list);
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityType<example.common.entity.Department> asType() {
        return __qualifiedTableName.isEmpty() ? __entityType : new org.seasar.doma.jdbc.criteria.metamodel.EntityTypeProxy<>(__entityType, __qualifiedTableName);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> allPropertyMetamodels() {
        return __allPropertyMetamodels;
    }

}
