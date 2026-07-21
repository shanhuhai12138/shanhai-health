package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 套餐检查组关联对象 t_setmeal_checkgroup
 *
 * @author ruoyi
 * @date 2026-07-11
 */
public class TSetmealCheckgroup
{
    private static final long serialVersionUID = 1L;

    /** 套餐ID */
    private Long setmealId;

    /** 检查组ID */
    private Long checkgroupId;

    public void setSetmealId(Long setmealId)
    {
        this.setmealId = setmealId;
    }

    public Long getSetmealId()
    {
        return setmealId;
    }

    public void setCheckgroupId(Long checkgroupId)
    {
        this.checkgroupId = checkgroupId;
    }

    public Long getCheckgroupId()
    {
        return checkgroupId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("setmealId", getSetmealId())
                .append("checkgroupId", getCheckgroupId())
                .toString();
    }
}
