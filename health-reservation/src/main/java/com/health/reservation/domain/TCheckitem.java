package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 检查项管理对象 t_checkitem
 * 
 * @author ruoyi
 * @date 2026-06-26
 */
public class TCheckitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查项ID */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 性别（0男 1女 2不限） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=不限")
    private String sex;

    /** 年龄范围 */
    @Excel(name = "年龄范围")
    private String age;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 类型（1检查 2检验） */
    @Excel(name = "类型", readConverterExp = "1=检查,2=检验")
    private String type;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String attention;

    /** 检查科室 */
    @Excel(name = "检查科室")
    private String checkDept;

    /** 助记码 */
    @Excel(name = "助记码")
    private String helpCode;

    /** 检查类别 */
    @Excel(name = "检查类别")
    private String cate;

    /** 关联检查组ID */
    private Long checkgroupId;

    /** 注意事项（简版） */
    @Excel(name = "注意事项")
    private String notice;

    /** 检查摘要 */
    @Excel(name = "检查摘要")
    private String abstractInfo;

    /** 是否加项（0否 1是） */
    @Excel(name = "是否加项", readConverterExp = "0=否,1=是")
    private String isAddin;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 参考范围 */
    @Excel(name = "参考范围")
    private String normalRange;

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

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setAttention(String attention) 
    {
        this.attention = attention;
    }

    public String getAttention() 
    {
        return attention;
    }
    public void setCheckDept(String checkDept)
    {
        this.checkDept = checkDept;
    }

    public String getCheckDept()
    {
        return checkDept;
    }

    public void setHelpCode(String helpCode)
    {
        this.helpCode = helpCode;
    }

    public String getHelpCode()
    {
        return helpCode;
    }

    public void setCate(String cate)
    {
        this.cate = cate;
    }

    public String getCate()
    {
        return cate;
    }

    public void setCheckgroupId(Long checkgroupId)
    {
        this.checkgroupId = checkgroupId;
    }

    public Long getCheckgroupId()
    {
        return checkgroupId;
    }

    public void setNotice(String notice)
    {
        this.notice = notice;
    }

    public String getNotice()
    {
        return notice;
    }

    public void setAbstractInfo(String abstractInfo)
    {
        this.abstractInfo = abstractInfo;
    }

    public String getAbstractInfo()
    {
        return abstractInfo;
    }

    public void setIsAddin(String isAddin)
    {
        this.isAddin = isAddin;
    }

    public String getIsAddin()
    {
        return isAddin;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setNormalRange(String normalRange)
    {
        this.normalRange = normalRange;
    }

    public String getNormalRange()
    {
        return normalRange;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("price", getPrice())
            .append("type", getType())
            .append("attention", getAttention())
            .append("checkDept", getCheckDept())
            .append("helpCode", getHelpCode())
            .append("cate", getCate())
            .append("checkgroupId", getCheckgroupId())
            .append("notice", getNotice())
            .append("abstractInfo", getAbstractInfo())
            .append("isAddin", getIsAddin())
            .append("sort", getSort())
            .append("delFlag", getDelFlag())
            .append("unit", getUnit())
            .append("normalRange", getNormalRange())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

