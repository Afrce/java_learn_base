package net.xdclass.xdvideo;

import at.favre.lib.crypto.bcrypt.BCrypt;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.model.Do.AdminsDo;
import net.xdclass.xdvideo.model.Do.PermissionsDo;
import net.xdclass.xdvideo.service.PermissionsService;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class jwtTest {
    @Autowired
    private PermissionsService permissionsService;

    @Test
    public void testGetJwt() {
        AdminsDo user = new AdminsDo();
        user.setId(123);
        user.setName("TEST");
        user.setEmail("1231313");
        String jwt = JwtUtils.createJwtToken(user);
        System.out.println(jwt);
    }


    @Test
    public  void testGetJsonData() {
        PermissionsDo permissionsDo =  permissionsService.getById("02ed7392201e47789146d2da7ab4a58f");
        System.out.println(permissionsDo);
    }

    @Test
    public void testBCrypt() {
        BCrypt.Verifyer verifyer =  BCrypt.verifyer();
        String password = "yuxuan3507";
        String checkPassword = "$2y$10$xNwu15z0eENstMfktbK3UemkwzoL65cS5DhJ4H0sPlikYPbzeKrj2";
        String hashPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
        System.out.println(hashPassword);
        BCrypt.Result res = verifyer.verify(password.toCharArray(), checkPassword.toCharArray());

        if (res.verified) {
            System.out.println("密码正确");
        } else {
            System.out.println("密码错误");
        }
    }
}
