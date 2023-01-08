package com.jiang.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.reggie_take_out.entity.Setmeal;
import com.jiang.reggie_take_out.mapper.SetmealMapper;
import com.jiang.reggie_take_out.service.SetmealService;
import org.springframework.stereotype.Service;


@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
