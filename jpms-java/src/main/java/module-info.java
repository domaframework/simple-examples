module jpms_java {
  requires org.seasar.doma.core;
  requires org.seasar.doma.slf4j;

  opens example.jpms_java.entity;
}
