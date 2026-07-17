package com.health.reservation.service.impl;

import java.util.Date;
import java.util.List;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.AppointmentMapper;
import com.health.reservation.domain.Appointment;
import com.health.reservation.service.IAppointmentService;
import com.health.reservation.service.INotificationService;

/**
 * 预约记录Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-11
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService
{
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private INotificationService notificationService;

    /**
     * 查询预约记录
     *
     * @param id 预约记录主键
     * @return 预约记录
     */
    @Override
    public Appointment selectAppointmentById(Long id)
    {
        return appointmentMapper.selectAppointmentById(id);
    }

    /**
     * 查询预约记录列表
     *
     * @param appointment 预约记录
     * @return 预约记录
     */
    @Override
    public List<Appointment> selectAppointmentList(Appointment appointment)
    {
        return appointmentMapper.selectAppointmentList(appointment);
    }

    /**
     * 新增预约记录
     *
     * @param appointment 预约记录
     * @return 结果
     */
    @Override
    public int insertAppointment(Appointment appointment)
    {
        appointment.setCreateTime(DateUtils.getNowDate());
        appointment.setCreateBy(SecurityUtils.getUsername());
        appointment.setStatus("0"); // 默认待确认
        return appointmentMapper.insertAppointment(appointment);
    }

    /**
     * 修改预约记录
     *
     * @param appointment 预约记录
     * @return 结果
     */
    @Override
    public int updateAppointment(Appointment appointment)
    {
        appointment.setUpdateTime(DateUtils.getNowDate());
        appointment.setUpdateBy(SecurityUtils.getUsername());
        return appointmentMapper.updateAppointment(appointment);
    }

    /**
     * 批量删除预约记录
     *
     * @param ids 需要删除的预约记录主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentByIds(Long[] ids)
    {
        return appointmentMapper.deleteAppointmentByIds(ids);
    }

    /**
     * 删除预约记录信息
     *
     * @param id 预约记录主键
     * @return 结果
     */
    @Override
    public int deleteAppointmentById(Long id)
    {
        return appointmentMapper.deleteAppointmentById(id);
    }

    /**
     * 取消预约
     *
     * @param id 预约记录主键
     * @return 结果
     */
    @Override
    public int cancelAppointment(Long id)
    {
        Appointment appointment = appointmentMapper.selectAppointmentById(id);
        if (appointment == null)
        {
            return 0;
        }
        Appointment update = new Appointment();
        update.setId(id);
        update.setStatus("3"); // 已取消
        update.setUpdateTime(DateUtils.getNowDate());
        int rows = appointmentMapper.updateAppointment(update);
        // 取消预约后发送通知给用户
        if (rows > 0 && appointment.getUserId() != null)
        {
            notificationService.createNotification(
                appointment.getUserId(),
                "appointment",
                "预约已取消",
                "您的预约（" + (appointment.getAppointmentTime() != null ? appointment.getAppointmentTime().toString() : "") + "）已被取消",
                id,
                "appointment"
            );
        }
        return rows;
    }

    /**
     * 确认预约
     *
     * @param id 预约记录主键
     * @return 结果
     */
    @Override
    public int confirmAppointment(Long id)
    {
        Appointment appointment = appointmentMapper.selectAppointmentById(id);
        if (appointment == null)
        {
            return 0;
        }
        Appointment update = new Appointment();
        update.setId(id);
        update.setStatus("1"); // 已确认
        update.setUpdateTime(DateUtils.getNowDate());
        int rows = appointmentMapper.updateAppointment(update);
        // 确认预约后发送通知给用户
        if (rows > 0 && appointment.getUserId() != null)
        {
            notificationService.createNotification(
                appointment.getUserId(),
                "appointment",
                "预约已确认",
                "您的预约已确认，请按时前往咨询",
                id,
                "appointment"
            );
        }
        return rows;
    }

    /**
     * 完成预约
     * 状态变更为"2"（已完成），触发通知逻辑（预留扩展点）
     *
     * @param id 预约记录主键
     * @return 结果
     */
    @Override
    public int completeAppointment(Long id)
    {
        Appointment appointment = appointmentMapper.selectAppointmentById(id);
        if (appointment == null)
        {
            return 0;
        }
        Appointment update = new Appointment();
        update.setId(id);
        update.setStatus("2"); // 已完成
        update.setUpdateTime(DateUtils.getNowDate());
        int rows = appointmentMapper.updateAppointment(update);
        // 预约完成后发送通知给用户
        if (rows > 0 && appointment.getUserId() != null)
        {
            notificationService.createNotification(
                appointment.getUserId(),
                "appointment",
                "预约已完成",
                "您的预约已完成，感谢您的使用",
                id,
                "appointment"
            );
        }
        return rows;
    }

    /**
     * 查询用户预约列表
     *
     * @param userId 用户ID
     * @return 预约记录集合
     */
    @Override
    public List<Appointment> selectAppointmentByUserId(Long userId)
    {
        Appointment appointment = new Appointment();
        appointment.setUserId(userId);
        return appointmentMapper.selectAppointmentList(appointment);
    }

    /**
     * 查询咨询师预约列表
     *
     * @param counselorId 咨询师ID
     * @return 预约记录集合
     */
    @Override
    public List<Appointment> selectAppointmentByCounselorId(Long counselorId)
    {
        Appointment appointment = new Appointment();
        appointment.setCounselorId(counselorId);
        return appointmentMapper.selectAppointmentList(appointment);
    }
}
