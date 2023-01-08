package com.jiang.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.reggie_take_out.entity.Category;
import org.springframework.stereotype.Service;



public interface CategoryService extends IService<Category> {
    
    Page CategoryPage(int page, int pageSize);
    
    void remove(Long id);
}
