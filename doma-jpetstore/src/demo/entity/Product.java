package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import demo.config.NullNamingConvention;

@Entity(namingConvention = NullNamingConvention.class)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    String productId;
    String categoryId;
    String name;
    String description;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }

}
