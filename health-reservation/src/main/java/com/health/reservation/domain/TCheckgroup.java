package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 检查组管理对象 t_checkgroup
 *
 * @author ruoyi
 * @date 2026-06-27
 */
public class TCheckgroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查组ID */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 助记码 */
    @Excel(name = "助记码")
    private String helpCode;

    /** 性别（0男 1女 2不限） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=不限")
    private String sex;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String attention;

    /** 适用年龄范围 */
    @Excel(name = "适用年龄")
    private String age;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 关联的检查项ID列表（非数据库字段，仅用于前端回显） */
    private Long[] checkItemIds;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
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

    public void setAttention(String attention)
    {
        this.attention = attention;
    }

    public String getAttention()
    {
        return attention;
    }

    public void setCheckItemIds(Long[] checkItemIds)
    {
        this.checkItemIds = checkItemIds;
    }

    public Long[] getCheckItemIds()
    {
        return checkItemIds;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAge()
    {
        return age;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("helpCode", getHelpCode())
            .append("sex", getSex())
            .append("remark", getRemark())
            .append("attention", getAttention())
            .append("age", getAge())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
