package com.example.videoparse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.videoparse.common.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础解析路径
 * </p>
 *
 * @author cc 
    qq: 3570632401  
     淘宝链接:[https://m.tb.cn/h.ewA2rg5?sm=60123d]
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_video_parse_url")
@ApiModel(value="VideoParseUrl对象", description="基础解析路径")
public class VideoParseUrl extends Request {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "解析id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "解析地址")
    @TableField(value = "parse_url",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String parseUrl;

    @ApiModelProperty(value = "状态 0有效 1失效过度 2失效")
    @TableField(value = "status",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String status;

    @ApiModelProperty(value = "备注")
    @TableField(value = "mark",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String mark;

    @ApiModelProperty(value = "失败权重")
    @TableField("fail_weight")
    private Integer failWeight;


}
