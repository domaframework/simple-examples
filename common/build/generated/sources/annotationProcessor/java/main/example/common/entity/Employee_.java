package example.common.entity;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:44:03.603+0900")
public final class Employee_ implements org.seasar.doma.jdbc.criteria.metamodel.EntityMetamodel<example.common.entity.Employee> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private final String __qualifiedTableName;

    private final example.common.entity._Employee __entityType = example.common.entity._Employee.getSingletonInternal();

    private final java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __allPropertyMetamodels;

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> id = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "id");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.String> name = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.String>(java.lang.String.class, __entityType, "name");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<example.common.domain.Age> age = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<example.common.domain.Age>(example.common.domain.Age.class, __entityType, "age");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<example.common.domain.Salary> salary = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<example.common.domain.Salary>(example.common.domain.Salary.class, __entityType, "salary");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<example.common.entity.JobType> jobType = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<example.common.entity.JobType>(example.common.entity.JobType.class, __entityType, "jobType");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.time.LocalDate> hiredate = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.time.LocalDate>(java.time.LocalDate.class, __entityType, "hiredate");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> departmentId = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "departmentId");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.lang.Integer> version = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.lang.Integer>(java.lang.Integer.class, __entityType, "version");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.time.LocalDateTime> insertTimestamp = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.time.LocalDateTime>(java.time.LocalDateTime.class, __entityType, "insertTimestamp");

    public final org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<java.time.LocalDateTime> updateTimestamp = new org.seasar.doma.jdbc.criteria.metamodel.DefaultPropertyMetamodel<java.time.LocalDateTime>(java.time.LocalDateTime.class, __entityType, "updateTimestamp");

    public Employee_() {
        this("");
    }

    public Employee_(String qualifiedTableName) {
        this.__qualifiedTableName = java.util.Objects.requireNonNull(qualifiedTableName);
        java.util.ArrayList<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> __list = new java.util.ArrayList<>(10);
        __list.add(id);
        __list.add(name);
        __list.add(age);
        __list.add(salary);
        __list.add(jobType);
        __list.add(hiredate);
        __list.add(departmentId);
        __list.add(version);
        __list.add(insertTimestamp);
        __list.add(updateTimestamp);
        __allPropertyMetamodels = java.util.Collections.unmodifiableList(__list);
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityType<example.common.entity.Employee> asType() {
        return __qualifiedTableName.isEmpty() ? __entityType : new org.seasar.doma.jdbc.criteria.metamodel.EntityTypeProxy<>(__entityType, __qualifiedTableName);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.criteria.metamodel.PropertyMetamodel<?>> allPropertyMetamodels() {
        return __allPropertyMetamodels;
    }

}
