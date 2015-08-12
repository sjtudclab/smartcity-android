package sjtu.dclab.smartcity.transfer;

/**
 *2015年4月2日 下午3:39:07
 *@author changyi yuan
 */
public class GroupTransfer {
    private long groupId;
    private String name;
    private int count;
    private long ownerId;

    public GroupTransfer(){

    }

    public GroupTransfer(long groupId, String name, int count, long ownerId) {
        super();
        this.groupId = groupId;
        this.name = name;
        this.count = count;
        this.ownerId = ownerId;
    }
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

}