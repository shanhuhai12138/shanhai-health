package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.Appointment;

/**
 * 预约记录Service接口
 *
 * @author ruoyi
 * @date 2026-07-11
 */
public interface IAppointmentService
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
     * 批量删除预约记录
     *
     * @param ids 需要删除的预约记录主键集合
     * @return 结果
     */
    public int deleteAppointmentByIds(Long[] ids);

    /**
     * 删除预约记录信息
     *
     * @param id 预约记录主键
     * @return 结果
     */
    public int deleteAppointmentById(Long id);

    /**
     * 取消预约
     *
     * @param id 预约记录主键
     * @return 结果
     */
    public int cancelAppointment(Long id);

    /**
     * 确认预约
     *
     * @param id 预约记录主键
     * @return 结果
     */
    public int confirmAppointment(Long id);

    /**
     * 完成预约
     *
     * @param id 预约记录主键
     * @return 结果
     */
    public int completeAppointment(Long id);

    /**
     * 查询用户预约列表
     *
     * @param userId 用户ID
     * @return 预约记录集合
     */
    public List<Appointment> selectAppointmentByUserId(Long userId);

    /**
     * 查询咨询师预约列表
     *
     * @param counselorId 咨询师ID
     * @return 预约记录集合
     */
    public List<Appointment> selectAppointmentByCounselorId(Long counselorId);
}
