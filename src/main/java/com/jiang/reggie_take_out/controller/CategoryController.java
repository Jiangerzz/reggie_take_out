package com.jiang.reggie_take_out.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiang.reggie_take_out.common.R;
import com.jiang.reggie_take_out.entity.Category;
import com.jiang.reggie_take_out.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("新增分类成功");
    }
    
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        return R.success(categoryService.CategoryPage(page,pageSize));
    }
    
    @DeleteMapping
    public R<String> delete(Long ids){
        categoryService.remove(ids);
        return R.success("分类信息删除成功");
    }
    
    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }
    
}
