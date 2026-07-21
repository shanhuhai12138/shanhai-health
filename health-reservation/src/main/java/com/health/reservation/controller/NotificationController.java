package com.health.reservation.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.Notification;
import com.health.reservation.service.INotificationService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;
import com.health.common.utils.SecurityUtils;

/**
 * 消息通知Controller
 *
 * @author ruoyi
 * @date 2026-07-14
 */
@RestController
@RequestMapping("/reservation/notification")
public class NotificationController extends BaseController
{
    @Autowired
    private INotificationService notificationService;

    /**
     * 查询消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:notification:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notification notification)
    {
        startPage();
        Long userId = SecurityUtils.getUserId();
        List<Notification> list = notificationService.selectNotificationList(userId, notification.getIsRead());
        return getDataTable(list);
    }

    /**
     * 导出消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:notification:export')")
    @Log(title = "消息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notification notification)
    {
        Long userId = SecurityUtils.getUserId();
        List<Notification> list = notificationService.selectNotificationList(userId, notification.getIsRead());
        ExcelUtil<Notification> util = new ExcelUtil<Notification>(Notification.class);
        util.exportExcel(response, list, "消息通知数据");
    }

    /**
     * 获取消息通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:notification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(notificationService.selectNotificationById(id));
    }

    /**
     * 查询用户消息通知列表
     */
    @GetMapping("/user/list")
    public TableDataInfo userList(@RequestParam(required = false) String isRead)
    {
        startPage();
        Long userId = SecurityUtils.getUserId();
        List<Notification> list = notificationService.selectNotificationList(userId, isRead);
        return getDataTable(list);
    }

    /**
     * 查询用户未读消息数量
     */
    @GetMapping("/unreadCount")
    public AjaxResult getUnreadCount()
    {
        Long userId = SecurityUtils.getUserId();
        return success(notificationService.getUnreadCount(userId));
    }

    /**
     * 将消息标记为已读
     */
    @PutMapping("/read/{id}")
    public AjaxResult markAsRead(@PathVariable("id") Long id)
    {
        return toAjax(notificationService.markAsRead(id));
    }

    /**
     * 将所有消息标记为已读
     */
    @PutMapping("/readAll")
    public AjaxResult readAll()
    {
        Long userId = SecurityUtils.getUserId();
        return toAjax(notificationService.markAllAsRead(userId));
    }

    /**
     * 查询消息类型字典
     */
    @GetMapping("/types")
    public AjaxResult getTypes()
    {
        List<Map<String, Object>> types = Arrays.asList(
            Map.of("value", "appointment", "label", "预约通知"),
            Map.of("value", "report", "label", "报告通知"),
            Map.of("value", "assessment", "label", "测评通知"),
            Map.of("value", "system", "label", "系统通知")
        );
        return success(types);
    }
}
