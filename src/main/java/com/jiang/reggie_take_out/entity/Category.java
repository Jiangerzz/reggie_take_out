package com.jiang.reggie_take_out.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private Integer type; //类型 1 菜品分类  2 套餐分类
    
    private String name;  //分类名称
    
    private Integer sort;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    
}
