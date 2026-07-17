package com.health.reservation.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TOrdersetting;

/**
 * 用户端预约Mapper接口
 */
public interface UserReservationMapper
{
    /**
     * 查询可预约套餐列表（已上架）
     */
    List<TSetmeal> selectAvailableSetmeals();

    /**
     * 查询套餐关联的检查组ID列表
     *
     * @param setmealId 套餐ID
     * @return 检查组ID列表
     */
    List<Long> selectCheckgroupIdsBySetmealId(@Param("setmealId") Long setmealId);

    /**
     * 查询检查组关联的检查项ID列表
     *
     * @param checkgroupId 检查组ID
     * @return 检查项ID列表
     */
    List<Long> selectCheckitemIdsByCheckgroupId(@Param("checkgroupId") Long checkgroupId);

    /**
     * 查询可预约日期列表（有预约设置且有余量的日期）
     *
     * @return 日期列表
     */
    List<Map<String, Object>> selectAvailableDates();

    /**
     * 查询指定日期的预约设置
     *
     * @param orderDate 预约日期
     * @return 预约设置
     */
    TOrdersetting selectOrdersettingByDate(@Param("orderDate") Date orderDate);

    /**
     * 乐观锁扣减库存：增加已预约数
     *
     * @param orderDate 预约日期
     * @return 影响行数（1=成功，0=超卖）
     */
    int increaseReservations(@Param("orderDate") Date orderDate);

    /**
     * 乐观锁释放库存：减少已预约数
     *
     * @param orderDate 预约日期
     * @return 影响行数
     */
    int decreaseReservations(@Param("orderDate") Date orderDate);
}
