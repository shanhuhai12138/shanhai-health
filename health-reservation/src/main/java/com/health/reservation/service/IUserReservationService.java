package com.health.reservation.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TOrdersetting;

/**
 * 用户端预约Service接口
 */
public interface IUserReservationService
{
    /**
     * 查询可预约套餐列表（支持性别/年龄过滤）
     *
     * @param sex 性别筛选（0男 1女 2不限），可选
     * @param age 年龄筛选，可选
     * @return 套餐列表
     */
    List<TSetmeal> getAvailableSetmeals(String sex, String age);

    /**
     * 查询套餐详情（含检查组和检查项嵌套）
     *
     * @param setmealId 套餐ID
     * @return 套餐详情Map，包含 setmeal、checkgroups（每个含 checkitems）
     */
    Map<String, Object> getSetmealDetails(Long setmealId);

    /**
     * 查询可预约日期列表（有剩余号源的日期）
     *
     * @param setmealId 套餐ID（可选，用于过滤）
     * @return 日期列表
     */
    List<Map<String, Object>> getAvailableDates(Long setmealId);

    /**
     * 查询指定日期的预约设置
     *
     * @param orderDate 预约日期
     * @return 预约设置
     */
    TOrdersetting getOrdersettingByDate(Date orderDate);

    /**
     * 创建预约订单（含库存扣减与报告生成）
     *
     * @param userId 用户ID（可为null，匿名预约）
     * @param setmealId 套餐ID
     * @param orderDate 预约日期
     * @param checkgroupIds 检查组ID数组（可选）
     * @param memberName 体检人姓名
     * @param memberPhone 体检人手机号
     * @param memberIdcard 体检人身份证号
     * @return 报告编号
     */
    String createOrder(Long userId, Long setmealId, Date orderDate, Long[] checkgroupIds,
                       String memberName, String memberPhone, String memberIdcard);

    /**
     * 取消预约（释放库存）
     *
     * @param reportNo 报告编号
     */
    void cancelOrder(String reportNo);
}
