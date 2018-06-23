package bupt.com.travelandroid.Bean;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class User {
    Integer uid;
    String account;
    String password;
    String roleType;
    //当前用户登录时的未读消息数
    Integer unReadMsg;
    UserInfo info;

    public Integer getUnReadMsg() {
        return unReadMsg;
    }

    public void setUnReadMsg(Integer unReadMsg) {
        this.unReadMsg = unReadMsg;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
