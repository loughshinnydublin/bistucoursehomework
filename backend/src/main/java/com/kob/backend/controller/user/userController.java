package com.kob.backend.controller.user;


import cn.hutool.core.lang.Console;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.captcha.*;
//import sun.security.util.Password;


//调试用
@RestController
public class userController {

    @Autowired  //用数据库中的mapper
    UserMapper userMapper;  //该接口由mybatisplus实现

    @Autowired
    ProductMapper productMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/api/user/all/") //映射get类型请求
    public List<User> getAll() {
        return userMapper.selectList(null); //查询所有
    }

    @GetMapping("/api/user/{userId}/")
    public User getUser(@PathVariable int userId) { //注解作用为获取getmapping大括号中的内容
        return userMapper.selectById(userId);
    }

    @GetMapping("/api/user/add/{username}/{password}/{phone}")   //插入user, 适用post
    public String addUser(
//            @PathVariable int userId,
            @PathVariable String username, @PathVariable String password, @PathVariable String phone) {
        Date date = new Date();
//        User user = new User(null, username, password, "client",phone, date);
//        userMapper.insert(user);
        return "Add user successfully";
    }

    @PostMapping("/api/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "delete user successfully";
    }

    LineCaptcha lineCaptcha = new LineCaptcha(200, 100);

    @GetMapping("/api/captcha")
    public void getimage(HttpServletResponse response) throws IOException {

        BufferedImage image = (BufferedImage) lineCaptcha.createImage(lineCaptcha.getCode());
        Console.log(lineCaptcha.getCode());
//        lineCaptcha.write("d:/line.png");
//        System.out.println(lineCaptcha.verify("1234"));


        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/api/captcha/refresh")
    public void refresh(HttpServletResponse response) throws IOException {
        lineCaptcha.createCode();
        this.getimage(response);
    }

    @PostMapping("/api/captcha/verify")
    public Map<String, String> verifyCaptcha(@RequestBody Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        if(lineCaptcha.verify(data.get("captcha"))) {
            map.put("error_message", "success");
        } else {
            map.put("error_message", "验证码错误");
        }

        return map;
    }

    @GetMapping("api/testRespo")
    //测试查询productimage
    public Map<String, String> testRespo() {
        System.out.println(productMapper.getProductImagesByProductId(BigInteger.valueOf(1)));
        System.out.println(productMapper.selectById(1));

        Map<String, String> map = new HashMap<>();
        map.put("productId", "1");
        map.put("image_url", productMapper.getProductImagesByProductId(BigInteger.valueOf(1)).get(0).getImageUrl());


        return map;

    }

    @GetMapping("api/testRegister")
    public String testpasswordEncoder() {
        String password = "test";

        String Encoded_Password = passwordEncoder.encode(password);
        System.out.println(password);
        System.out.println(Encoded_Password);
        return Encoded_Password;
    }

}
