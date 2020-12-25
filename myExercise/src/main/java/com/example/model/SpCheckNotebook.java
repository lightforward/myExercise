package com.example.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wns
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SpCheckNotebook extends Model<SpCheckNotebook> {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 单位id
     */
    private String unitId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifyDate;

    /**
     * 数据状态(0.已删除 1.正常)
     */
    private Integer type;

    private String modifier;

    /**
     * 备用字段
     */
    private String spareA;

    /**
     * 备用字段
     */
    private String spareB;

    /**
     * 备用字段
     */
    private String spareC;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
