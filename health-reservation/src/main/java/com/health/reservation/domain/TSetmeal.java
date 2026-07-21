package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 套餐组对象 t_setmeal
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
public class TSetmeal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 助记码 */
    @Excel(name = "助记码")
    private String helpCode;

    /** 性别（0男 1女 2不限） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=不限")
    private String sex;

    /** 年龄范围 */
    @Excel(name = "年龄范围")
    private String age;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String attention;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String img;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setHelpCode(String helpCode) 
    {
        this.helpCode = helpCode;
    }

    public String getHelpCode() 
    {
        return helpCode;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }

    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }

    public void setAttention(String attention) 
    {
        this.attention = attention;
    }

    public String getAttention() 
    {
        return attention;
    }

    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("helpCode", getHelpCode())
            .append("sex", getSex())
            .append("age", getAge())
            .append("price", getPrice())
            .append("remark", getRemark())
            .append("attention", getAttention())
            .append("img", getImg())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
