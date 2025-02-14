package example.common.entity;

/** */
@javax.annotation.processing.Generated(value = { "Doma", "3.4.0" }, date = "2025-02-15T08:44:03.566+0900")
@org.seasar.doma.EntityTypeImplementation
public final class _Department extends org.seasar.doma.jdbc.entity.AbstractEntityType<example.common.entity.Department> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("3.4.0");
    }

    private static final _Department __singleton = new _Department();

    private final org.seasar.doma.jdbc.entity.NamingType __namingType = null;

    private final java.util.function.Supplier<org.seasar.doma.jdbc.entity.NullEntityListener<example.common.entity.Department>> __listenerSupplier;

    private final boolean __immutable;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final boolean __isQuoteRequired;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __entityPropertyTypeMap;

    @SuppressWarnings("unused")
    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Department, ?>> __embeddedPropertyTypeMap;

    private final java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> __associationPropertyTypes;

    private _Department() {
        __listenerSupplier = org.seasar.doma.internal.jdbc.entity.NullEntityListenerSuppliers.of();
        __immutable = false;
        __name = "Department";
        __catalogName = "";
        __schemaName = "";
        __tableName = "";
        __isQuoteRequired = false;
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __idList = new java.util.ArrayList<>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __list = new java.util.ArrayList<>(3);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __map = new java.util.LinkedHashMap<>(3);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Department, ?>> __embeddedMap = new java.util.LinkedHashMap<>(3);
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

    private void initializeMaps(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __map, java.util.Map<String, org.seasar.doma.jdbc.entity.EmbeddedPropertyType<example.common.entity.Department, ?>> __embeddedMap) {
        __map.put("id", new org.seasar.doma.jdbc.entity.AssignedIdPropertyType<example.common.entity.Department, java.lang.Integer, java.lang.Integer>(example.common.entity.Department.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "id", "", __namingType, false));
        __map.put("name", new org.seasar.doma.jdbc.entity.DefaultPropertyType<example.common.entity.Department, java.lang.String, java.lang.String>(example.common.entity.Department.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofString(), "name", "", __namingType, true, true, false));
        __map.put("version", new org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Department, java.lang.Integer, java.lang.Integer>(example.common.entity.Department.class, org.seasar.doma.internal.jdbc.scalar.BasicScalarSuppliers.ofInteger(), "version", "", __namingType, false));
    }

    private void initializeIdList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __idList) {
        __idList.add(__map.get("id"));
    }

    private void initializeList(java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __map, java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> __list) {
        __list.addAll(__map.values());
    }

    private void initializeAssociationList(java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> __associationList) {
        __associationList.add(new org.seasar.doma.jdbc.entity.DefaultAssociationPropertyType("employees"));
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
    public void preInsert(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PreInsertContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preUpdate(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PreUpdateContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void preDelete(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PreDeleteContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.preDelete(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postInsert(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PostInsertContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postInsert(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postUpdate(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PostUpdateContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postUpdate(entity, context);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postDelete(example.common.entity.Department entity, org.seasar.doma.jdbc.entity.PostDeleteContext<example.common.entity.Department> context) {
        Class __listenerClass = org.seasar.doma.jdbc.entity.NullEntityListener.class;
        org.seasar.doma.jdbc.entity.NullEntityListener __listener = context.getConfig().getEntityListenerProvider().get(__listenerClass, __listenerSupplier);
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.AssociationPropertyType> getAssociationPropertyTypes() {
        return __associationPropertyTypes;
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<example.common.entity.Department, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<example.common.entity.Department, ?, ?> getGeneratedIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<example.common.entity.Department, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Department, ?, ?> getVersionPropertyType() {
        return (org.seasar.doma.jdbc.entity.VersionPropertyType<example.common.entity.Department, ?, ?>)__entityPropertyTypeMap.get("version");
    }

    @Override
    public org.seasar.doma.jdbc.entity.TenantIdPropertyType<example.common.entity.Department, ?, ?> getTenantIdPropertyType() {
        return (org.seasar.doma.jdbc.entity.TenantIdPropertyType<example.common.entity.Department, ?, ?>)__entityPropertyTypeMap.get("null");
    }

    @Override
    public example.common.entity.Department newEntity(java.util.Map<String, org.seasar.doma.jdbc.entity.Property<example.common.entity.Department, ?>> __args) {
        example.common.entity.Department entity = new example.common.entity.Department();
        if (__args.get("id") != null) __args.get("id").save(entity);
        if (__args.get("name") != null) __args.get("name").save(entity);
        if (__args.get("version") != null) __args.get("version").save(entity);
        return entity;
    }

    @Override
    public Class<example.common.entity.Department> getEntityClass() {
        return example.common.entity.Department.class;
    }

    @Override
    public example.common.entity.Department getOriginalStates(example.common.entity.Department __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(example.common.entity.Department __entity) {
    }

    /**
     * @return the singleton
     */
    public static _Department getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _Department newInstance() {
        return new _Department();
    }

}
