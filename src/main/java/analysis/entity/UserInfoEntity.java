package analysis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @Description 用户信息实体
 * @Author      dayu
 * @Date        2019/12/6 17:23
 * @Version     v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEntity {
    private String id;          //序号
    private String username;    //用户名
    private String password;    //密码
    private String idcard;      //身份证号
    private String realname;    //实名
    private String phone;       //手机
    private String updateTime;  //更新时间
    private String createTime;  //创建时间

    public UserInfoEntity(String username){
        this.username = username;
    }
}
