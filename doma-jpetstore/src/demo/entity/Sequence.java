package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import demo.config.NullNamingConvention;

@Entity(namingConvention = NullNamingConvention.class)
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    String name;
    int nextId;

    public Sequence() {
    }

    public Sequence(String name, int nextId) {
        this.name = name;
        this.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

}
