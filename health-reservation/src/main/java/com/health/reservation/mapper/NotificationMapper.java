package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.Notification;

/**
 * 消息通知Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-14
 */
public interface NotificationMapper
{
    /**
     * 查询消息通知
     *
     * @param id 消息通知主键
     * @return 消息通知
     */
    public Notification selectNotificationById(Long id);

    /**
     * 查询消息通知列表
     *
     * @param notification 消息通知
     * @return 消息通知集合
     */
    public List<Notification> selectNotificationList(Notification notification);

    /**
     * 新增消息通知
     *
     * @param notification 消息通知
     * @return 结果
     */
    public int insertNotification(Notification notification);

    /**
     * 修改消息通知
     *
     * @param notification 消息通知
     * @return 结果
     */
    public int updateNotification(Notification notification);

    /**
     * 删除消息通知
     *
     * @param id 消息通知主键
     * @return 结果
     */
    public int deleteNotificationById(Long id);

    /**
     * 批量删除消息通知
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNotificationByIds(Long[] ids);

    /**
     * 更新消息已读状态
     *
     * @param id 消息ID
     * @return 结果
     */
    public int updateReadStatus(Long id);

    /**
     * 批量更新用户消息已读状态
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int updateReadStatusByUserId(Long userId);

    /**
     * 查询用户未读消息数量
     *
     * @param userId 用户ID
     * @return 未读消息数量
     */
    public int selectUnreadCount(Long userId);
}
