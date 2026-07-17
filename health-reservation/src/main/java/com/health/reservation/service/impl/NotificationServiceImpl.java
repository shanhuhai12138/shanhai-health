package com.health.reservation.service.impl;

import java.util.Date;
import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.NotificationMapper;
import com.health.reservation.domain.Notification;
import com.health.reservation.service.INotificationService;

/**
 * 消息通知Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-14
 */
@Service
public class NotificationServiceImpl implements INotificationService
{
    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 查询消息通知
     *
     * @param id 消息通知主键
     * @return 消息通知
     */
    @Override
    public Notification selectNotificationById(Long id)
    {
        return notificationMapper.selectNotificationById(id);
    }

    /**
     * 创建消息通知（工厂方法）
     *
     * @param userId 接收用户ID
     * @param messageType 消息类型
     * @param title 消息标题
     * @param content 消息内容
     * @param relatedId 关联业务ID
     * @param relatedType 关联业务类型
     * @return 结果
     */
    @Override
    public int createNotification(Long userId, String messageType, String title, String content, Long relatedId, String relatedType)
    {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessageType(messageType);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setIsRead("0");
        notification.setRelatedId(relatedId);
        notification.setRelatedType(relatedType);
        notification.setCreateTime(DateUtils.getNowDate());
        return notificationMapper.insertNotification(notification);
    }

    /**
     * 查询消息通知列表
     *
     * @param userId 用户ID
     * @param isRead 是否已读
     * @return 消息通知集合
     */
    @Override
    public List<Notification> selectNotificationList(Long userId, String isRead)
    {
        Notification notification = new Notification();
        notification.setUserId(userId);
        if (isRead != null && !isRead.isEmpty())
        {
            notification.setIsRead(isRead);
        }
        return notificationMapper.selectNotificationList(notification);
    }

    /**
     * 将消息标记为已读
     *
     * @param notificationId 消息ID
     * @return 结果
     */
    @Override
    public int markAsRead(Long notificationId)
    {
        return notificationMapper.updateReadStatus(notificationId);
    }

    /**
     * 将用户所有未读消息标记为已读
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int markAllAsRead(Long userId)
    {
        return notificationMapper.updateReadStatusByUserId(userId);
    }

    /**
     * 查询用户未读消息数量
     *
     * @param userId 用户ID
     * @return 未读消息数量
     */
    @Override
    public int getUnreadCount(Long userId)
    {
        return notificationMapper.selectUnreadCount(userId);
    }
}
