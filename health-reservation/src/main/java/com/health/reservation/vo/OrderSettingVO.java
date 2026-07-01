package com.health.reservation.vo;

/**
 * 预约设置视图对象
 *
 * @author ruoyi
 * @date 2026-06-30
 */
public class OrderSettingVO
{
    /** 预约设置ID */
    private Long id;

    /** 日期（1~31） */
    private Integer date;

    /** 可预约人数 */
    private Integer number;

    /** 已预约人数 */
    private Integer reservations;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getDate()
    {
        return date;
    }

    public void setDate(Integer date)
    {
        this.date = date;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Integer getReservations()
    {
        return reservations;
    }

    public void setReservations(Integer reservations)
    {
        this.reservations = reservations;
    }
}
