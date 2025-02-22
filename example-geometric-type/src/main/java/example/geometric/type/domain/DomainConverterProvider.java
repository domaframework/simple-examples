package example.geometric.type.domain;

import org.seasar.doma.DomainConverters;

@DomainConverters({CircleTypeProvider.class, PointTypeProvider.class})
public class DomainConverterProvider {}
