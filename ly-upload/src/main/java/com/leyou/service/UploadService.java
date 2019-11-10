package com.leyou.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    // 支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

    public String uploadFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (!suffixes.contains(contentType)) {
                logger.info("上传失败，文件类型不匹配：{}", contentType);
                return null;
            }

            System.out.println("file.getName()=======" + file.getName());
            System.out.println("file.getOriginalFilename()=======" + file.getOriginalFilename());


            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }

            File dir = new File("F:\\LeyouImage\\upload");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            file.transferTo(new File(dir, file.getOriginalFilename()));

            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();
            return url;
        } catch (IOException e) {
            return null;
        }
    }
}
