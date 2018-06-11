package bupt.com.travelandroid.Bean.response;

public class RelationInfo {
    //手机号码
    public  String phone;
    //用户名称
    public  String name;
    //头像信息
    public  String avator;

    public Integer  relationType;

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "RelationInfo{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", avator='" + avator + '\'' +
                '}';
    }
}
