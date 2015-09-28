package sjtu.dclab.smartcity.model;

/**
 * Apartment
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class Apartment {
    private int id, owner, building;
    private String name, serialNumber, area;

    @Override
    public String toString() {
        return "{" + "id=" + id + ", serialNumber=" + serialNumber +
                ", name=" + name + ", area=" + area + ", owner=" + owner + ", building=" + building + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
