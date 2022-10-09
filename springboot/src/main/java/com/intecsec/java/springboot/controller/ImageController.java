package com.intecsec.java.springboot.controller;

import com.intecsec.java.util.AliyunSts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Peter.Peng
 * Created on 2022/10/9 17:57
 */
@RestController()
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/get")
    public Object selectPage(@RequestParam(value = "imageName", required = false, defaultValue = "") String imageName,
                             HttpServletResponse resp
                             ) {
        URL imageURL = null;
        try {
            imageURL = AliyunSts.getSTSURL(imageName);

            BufferedImage big = ImageIO.read(imageURL);

            // 去掉这行设置header的代码，前端访问可以直接展示
            // resp.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(imageName,"UTF-8") );
            // 为了保证大图背景不变色，formatName必须为"png"
            ImageIO.write(big, "png", resp.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageURL.toString();
    }

}
