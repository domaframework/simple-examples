package example.sql_annotation.domain;

import org.seasar.doma.DomainConverters;

@DomainConverters(AgeConverter.class)
public class DomainConverterProvider {}
