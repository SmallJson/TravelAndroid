package bupt.com.travelandroid.Bean;

public class UserInfo {
    Integer uid;
    //用户头像的url地址
    String avator;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", avator='" + avator + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
