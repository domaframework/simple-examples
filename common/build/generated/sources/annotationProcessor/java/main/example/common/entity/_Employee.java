package example.common.entity;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:44:03.596+0900")
@org.seasar.doma.EntityTypeImplementation
public final class _Employee extends org.seasar.doma.jdbc.entity.AbstractEntityType<example.common.entity.Employee> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private static final _Employee __singleton = new _Employee();

    private static final org.seasar.doma.jdbc.entity.OriginalStatesAccessor<example.common.entity.Employee> __originalStatesAccessor = new org.seasar.doma.jdbc.entity.OriginalStatesAccessor<>(example.common.entity.Employee.class, "originalStates");

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final org.seasar.doma.jdbc.id.BuiltinSequenceIdGenerator __idGenerator = new org.seasar.doma.jdbc.id.BuiltinSequenceIdGenerator();
    {
        __idGenerator.setQualifiedSequenceName("EMPLOYEE_SEQ");
        __idGenerator.setInitialValue(1);
        __idGenerator.setAllocationSize(1);
        __idGenerator.initialize();
    }

    private final java.util.function.Supplier<example.common.entity.EmployeeListener<example.common.entity.Employee>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __entityPropertyTypeMap;

    @SuppressWarnings("unused")
    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Employee, ?>> __embeddedPropertyTypeMap;

    private final java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> __associationPropertyTypes;

    private _Employee() {
        __listenerSupplier = new java.util.function.Supplier<example.common.entity.EmployeeListener<example.common.entity.Employee>>() { @Override public example.common.entity.EmployeeListener<example.common.entity.Employee> get() { return ListenerHolder.listener; } };
        __immutable = false;
        __name = "Employee";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __list = new java.util.ArrayList<>(10);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __map = new java.util.LinkedHashMap<>(10);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Employee, ?>> __embeddedMap = new java.util.LinkedHashMap<>(10);
        initializeMaps(__map, __embeddedMap);
        initializeIdList(__map, __idList);
        initializeList(__map, __list);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
        __embeddedPropertyTypeMap = java.util.Collections.unmodifiableMap(__embeddedMap);
        java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> __associationList = new java.util.ArrayList<>(1);
        initializeAssociationList(__associationList);
        __associationPropertyTypes = java.util.Collections.unmodifiableList(__associationList);
    }

    private void initializeMaps(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __map, java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Employee, ?>> __embeddedMap) {
        __map.put("id", new org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<example.common.entity.Employee, java.lang.Integer, java.lang.Integer>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "id", "", __namingType, false, __idGenerator));
        __map.put("name", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.lang.String, java.lang.String>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "name", "", __namingType, true, true, false));
        __map.put("age", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.lang.Integer, example.common.domain.Age>(example.common.entity.Employee.class, __.example.common.domain._Age.getSingletonInternal().createScalarSupplier(), "age", "", __namingType, true, true, false));
        __map.put("salary", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.lang.Integer, example.common.domain.Salary>(example.common.entity.Employee.class, example.common.domain._Salary.getSingletonInternal().createScalarSupplier(), "salary", "", __namingType, true, true, false));
        __map.put("jobType", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, example.common.entity.JobType, example.common.entity.JobType>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofEnum(example.common.entity.JobType.class), "jobType", "JOB_TYPE", __namingType, true, true, false));
        __map.put("hiredate", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.time.LocalDate, java.time.LocalDate>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofLocalDate(), "hiredate", "", __namingType, true, true, false));
        __map.put("departmentId", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.lang.Integer, java.lang.Integer>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "departmentId", "DEPARTMENT_ID", __namingType, true, true, false));
        __map.put("version", new org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Employee, java.lang.Integer, java.lang.Integer>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "version", "VERSION", __namingType, false));
        __map.put("insertTimestamp", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.time.LocalDateTime, java.time.LocalDateTime>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofLocalDateTime(), "insertTimestamp", "", __namingType, true, true, false));
        __map.put("updateTimestamp", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Employee, java.time.LocalDateTime, java.time.LocalDateTime>(example.common.entity.Employee.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofLocalDateTime(), "updateTimestamp", "", __namingType, true, true, false));
    }

    private void initializeIdList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __idList) {
        __idList.add(__map.get("id"));
    }

    private void initializeList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> __list) {
        __list.addAll(__map.values());
    }

    private void initializeAssociationList(java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> __associationList) {
        __associationList.add(new org.seasar.doma.jdbc.entity.DefaultAssociationPropertyType("department"));
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public boolean isImmutable() {
        return __immutable;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    public String getTableName(java.util.function.BiFunction<org.seasar.doma.jdbc.entity.NamingType, String, String> namingFunction) {
        if (__tableName.isEmpty()) {
            return namingFunction.apply(__namingType, __name);
        }
        return __tableName;
    }

    @Override
    public boolean isQuoteRequired() {
        return __isQuoteRequired;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preInsert(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PreInsertContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PreUpdateContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PreDeleteContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PostInsertContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PostUpdateContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(example.common.entity.Employee entity, org.seasar.doma.jdbc.entity.PostDeleteContext<example.common.entity.Employee> context) {
        Class __listenerClass = example.common.entity.EmployeeListener.class;
        example.common.entity.EmployeeListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> getAssociationPropertyTypes() {
        return __associationPropertyTypes;
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Employee, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<example.common.entity.Employee, ?, ?> getGeneratedIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<example.common.entity.Employee, ?, ?>)__entityPropertyTypeMap.get("id");
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Employee, ?, ?> getVersionPropertyType() {
        return (org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Employee, ?, ?>)__entityPropertyTypeMap.get("version");
    }

    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<example.common.entity.Employee, ?, ?> getTenantIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.TenantIdPropertyType<example.common.entity.Employee, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @Override
    public example.common.entity.Employee newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<example.common.entity.Employee, ?>> __args) {
        example.common.entity.Employee entity = new example.common.entity.Employee();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("name") != null) __args.get("name").save(entity);
        if (__args.get("age") != null) __args.get("age").save(entity);
        if (__args.get("salary") != null) __args.get("salary").save(entity);
        if (__args.get("jobType") != null) __args.get("jobType").save(entity);
        if (__args.get("hiredate") != null) __args.get("hiredate").save(entity);
        if (__args.get("departmentId") != null) __args.get("departmentId").save(entity);
        if (__args.get("version") != null) __args.get("version").save(entity);
        if (__args.get("insertTimestamp") != null) __args.get("insertTimestamp").save(entity);
        if (__args.get("updateTimestamp") != null) __args.get("updateTimestamp").save(entity);
        return entity;
    }

    @Override
    public Class<example.common.entity.Employee> getEntityClass() {
        return example.common.entity.Employee.class;
    }

    @Override
    public example.common.entity.Employee getOriginalStates(example.common.entity.Employee __entity) {
        return __originalStatesAccessor.get(__entity);
    }

    @Override
    public void saveCurrentStates(example.common.entity.Employee __entity) {
        example.common.entity.Employee __currentStates = new example.common.entity.Employee();
        (__entityPropertyTypeMap.get("id")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("name")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("age")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("salary")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("jobType")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("hiredate")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("departmentId")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("version")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("insertTimestamp")).copy(__currentStates, __entity);
        (__entityPropertyTypeMap.get("updateTimestamp")).copy(__currentStates, __entity);
        __originalStatesAccessor.set(__entity, __currentStates);
    }

    /**
     * @return the singleton
     */
    public static _Employee getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _Employee newInstance() {
        return new _Employee();
    }

    private static class ListenerHolder {
        private static example.common.entity.EmployeeListener<example.common.entity.Employee> listener = new example.common.entity.EmployeeListener<>();
    }

}
