package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String name;

    public int nextId;

    public Sequence() {
    }

    public Sequence(String name, int nextId) {
        this.name = name;
        this.nextId = nextId;
    }

}
