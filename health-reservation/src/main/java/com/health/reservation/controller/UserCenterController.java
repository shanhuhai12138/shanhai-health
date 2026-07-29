package com.health.reservation.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.utils.SecurityUtils;
import com.health.reservation.mapper.*;
import com.health.reservation.domain.*;
import com.health.reservation.service.INotificationService;

/**
 * 患者端（C端）统一入口 Controller
 */
@RestController
@RequestMapping("/reservation/user-center")
public class UserCenterController extends BaseController
{
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AssessmentResultMapper assessmentResultMapper;

    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private INotificationService notificationService;

    /**
     * 患者首页统计数据
     */
    @GetMapping("/dashboard-stats")
    public AjaxResult dashboardStats()
    {
        Long userId = SecurityUtils.getUserId();
        Map<String, Object> stats = new HashMap<>();

        Appointment query = new Appointment();
        query.setUserId(userId);
        List<Appointment> appointments = appointmentMapper.selectAppointmentList(query);
        long pending = appointments.stream().filter(a -> "0".equals(a.getStatus())).count();

        int unread = notificationService.getUnreadCount(userId);
        List<AssessmentResult> assessments = assessmentResultMapper.selectAssessmentHistoryWithNames(userId);

        HealthReport rq = new HealthReport();
        rq.setUserId(userId);
        List<HealthReport> reports = healthReportMapper.selectHealthReportList(rq);

        stats.put("pendingAppointments", pending);
        stats.put("unreadNotifications", unread);
        stats.put("completedAssessments", assessments != null ? assessments.size() : 0);
        stats.put("totalReports", reports != null ? reports.size() : 0);
        return success(stats);
    }

    /**
     * 用户通知列表
     */
    @GetMapping("/notifications")
    public AjaxResult notifications()
    {
        Long userId = SecurityUtils.getUserId();
        return success(notificationService.selectNotificationList(userId, null));
    }

    /**
     * 我的健康报告列表
     */
    @GetMapping("/health-reports")
    public AjaxResult myHealthReports()
    {
        Long userId = SecurityUtils.getUserId();
        HealthReport rq = new HealthReport();
        rq.setUserId(userId);
        List<HealthReport> reports = healthReportMapper.selectHealthReportList(rq);
        return success(reports);
    }

    /**
     * 标记已读
     */
    @PutMapping("/notification/{id}/read")
    public AjaxResult readNotification(@PathVariable Long id)
    {
        notificationService.markAsRead(id);
        return success();
    }

    /**
     * 全部已读
     */
    @PutMapping("/notification/read-all")
    public AjaxResult readAllNotifications()
    {
        Long userId = SecurityUtils.getUserId();
        notificationService.markAllAsRead(userId);
        return success();
    }
}
