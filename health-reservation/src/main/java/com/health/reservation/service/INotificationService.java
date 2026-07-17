package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.Notification;

/**
 * 消息通知Service接口
 *
 * @author ruoyi
 * @date 2026-07-14
 */
public interface INotificationService
{
    /**
     * 查询消息通知
     *
     * @param id 消息通知主键
     * @return 消息通知
     */
    public Notification selectNotificationById(Long id);

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
    public int createNotification(Long userId, String messageType, String title, String content, Long relatedId, String relatedType);

    /**
     * 查询消息通知列表
     *
     * @param userId 用户ID
     * @param isRead 是否已读
     * @return 消息通知集合
     */
    public List<Notification> selectNotificationList(Long userId, String isRead);

    /**
     * 将消息标记为已读
     *
     * @param notificationId 消息ID
     * @return 结果
     */
    public int markAsRead(Long notificationId);

    /**
     * 将用户所有未读消息标记为已读
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int markAllAsRead(Long userId);

    /**
     * 查询用户未读消息数量
     *
     * @param userId 用户ID
     * @return 未读消息数量
     */
    public int getUnreadCount(Long userId);
}
