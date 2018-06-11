package bupt.com.travelandroid.Acvivity.IView;

import java.util.Map;

/**
 * Created by Administrator on 2018/6/11 0011.
 * 添加父母页面对应的View信息
 */
public interface IaddParentView {
    //获取关系类型 relationType 以及对方手机号码Phone
    public Map<String, Object> getRelation();
}
