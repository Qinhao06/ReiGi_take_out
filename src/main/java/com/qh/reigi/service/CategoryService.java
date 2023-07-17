package com.qh.reigi.service;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.Category;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    R<String> deleteCategory(Long id);

    R<String> editCategory(HttpServletRequest request, Category category);

    R<String> addCategory(HttpServletRequest request, Category category);

    List<Map<String, Object>> listCategory(Integer type);
}
