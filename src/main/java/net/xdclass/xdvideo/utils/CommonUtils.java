package net.xdclass.xdvideo.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommonUtils {
    static public Object formatPage(Page page, String key) {
        HashMap<String, Object> newPage = new HashMap<String, Object>();
        HashMap<String, Object> paginate = new HashMap<String, Object>();
        newPage.put(key, page.getRecords());
        paginate.put("current_page", page.getCurrent());
        paginate.put("last_page", page.getPages());
        paginate.put("per_page", page.getSize());
        paginate.put("total", page.getTotal());
        newPage.put("paginate", paginate);
        return newPage;
    }

    /**
     * 获取加密后的数据
     * @param password
     * @return
     */
    static public String getHashString (String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    /**
     * 密码验证
     * @param password
     * @param hashPassword
     * @return
     */
    static public boolean checkPassword (String password, String hashPassword) {
        BCrypt.Verifyer verifyer =  BCrypt.verifyer();
        BCrypt.Result res = verifyer.verify(password.toCharArray(), hashPassword.toCharArray());
        return res.verified;
    }
}
