package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.Appointment;

/**
 * 预约记录Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface AppointmentMapper
{
    /**
     * 查询预约记录
     *
     * @param id 预约记录主键
     * @return 预约记录
     */
    public Appointment selectAppointmentById(Long id);

    /**
     * 查询预约记录列表
     *
     * @param appointment 预约记录
     * @return 预约记录集合
     */
    public List<Appointment> selectAppointmentList(Appointment appointment);

    /**
     * 新增预约记录
     *
     * @param appointment 预约记录
     * @return 结果
     */
    public int insertAppointment(Appointment appointment);

    /**
     * 修改预约记录
     *
     * @param appointment 预约记录
     * @return 结果
     */
    public int updateAppointment(Appointment appointment);

    /**
     * 删除预约记录
     *
     * @param id 预约记录主键
     * @return 结果
     */
    public int deleteAppointmentById(Long id);

    /**
     * 批量删除预约记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppointmentByIds(Long[] ids);
}
