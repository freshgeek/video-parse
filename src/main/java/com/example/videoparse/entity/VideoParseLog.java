package com.example.videoparse.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 日志
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
@TableName("t_video_parse_log")
@ApiModel(value="VideoParseLog对象", description="日志")
public class VideoParseLog extends Request {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "解析id")
    @TableField("parse_id")
    private Integer parseId;

    @ApiModelProperty(value = "url")
    @TableField(value = "url",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String url;

    @ApiModelProperty(value = "日志内容")
    @TableField(value = "content",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String content;

    @ApiModelProperty(value = "时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "远程IP")
    @TableField(value = "ip",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String ip;


}
