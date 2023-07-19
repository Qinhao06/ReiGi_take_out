package com.qh.reigi.service.impl;

import com.qh.reigi.common.R;
import com.qh.reigi.service.CommonService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {

    @Value("${reigi.fileLocation}")
    String basePath;


    @Override
    public R<String> uploadFile(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            file.transferTo(new File(basePath + uuid + suffix));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            return R.error("上传失败" + e.getMessage());
        }
        return new R<String>(1, "上传成功",  uuid + suffix , null);
    }

    @Override
    public void downloadFile(String name, HttpServletResponse response) {


        try (FileInputStream fileInputStream = new FileInputStream(new File(basePath + name))) {
            ServletOutputStream servletOutputStream =  response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                servletOutputStream.write(bytes, 0, len);
                servletOutputStream.flush();
            }

            servletOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
