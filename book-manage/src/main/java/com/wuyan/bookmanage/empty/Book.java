package com.wuyan.bookmanage.empty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer bid;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String desc;

    /**
     * 书籍封面图
     */
    private String coverImage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}