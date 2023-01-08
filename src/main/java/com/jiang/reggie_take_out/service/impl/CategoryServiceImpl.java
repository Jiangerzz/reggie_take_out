package com.jiang.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.reggie_take_out.common.CustomException;
import com.jiang.reggie_take_out.entity.Category;
import com.jiang.reggie_take_out.entity.Dish;
import com.jiang.reggie_take_out.entity.Setmeal;
import com.jiang.reggie_take_out.mapper.CategoryMapper;
import com.jiang.reggie_take_out.service.CategoryService;
import com.jiang.reggie_take_out.service.DishService;
import com.jiang.reggie_take_out.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    
    private DishService dishService;
    private SetmealService setmealService;
    @Autowired
    public CategoryServiceImpl(DishService dishService,SetmealService setmealService){
        this.dishService = dishService;
        this.setmealService = setmealService;
    }
    
    

    @Override
    public Page CategoryPage(int page, int pageSize) {
        
        Page<Category> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        page(pageInfo,wrapper);
        return pageInfo;
    }

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishWrapper);
        //查询当前分类是否关联菜品
        if(count1 > 0){
            throw new CustomException("当前分类关联了菜品，不能删除");
        }
        //查询当前分类是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        setmealWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealWrapper);
        if(count2 > 0){
            throw new CustomException("当前分类关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
