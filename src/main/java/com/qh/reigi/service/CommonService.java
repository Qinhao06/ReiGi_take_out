package com.qh.reigi.service;

import com.qh.reigi.common.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

    R<String> uploadFile(MultipartFile file);

    void downloadFile(String name, HttpServletResponse response);
}
