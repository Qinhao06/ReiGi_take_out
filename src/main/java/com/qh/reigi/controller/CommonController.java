package com.qh.reigi.controller;


import com.qh.reigi.common.R;

import com.qh.reigi.service.CommonService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Autowired
    CommonService commonService;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

        return commonService.uploadFile(file);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
         commonService.downloadFile(name, response);
    }

}
