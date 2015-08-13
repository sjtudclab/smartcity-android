package sjtu.dclab.smartcity.model;

/**
 * Building
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class Building {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
