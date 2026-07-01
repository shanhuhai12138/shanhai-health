package com.health.reservation.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.vo.OrderSettingVO;

/**
 * 预约设置Service接口
 *
 * @author ruoyi
 * @date 2026-06-30
 */
public interface ITOrdersettingService
{
    /**
     * 查询预约设置
     *
     * @param id 预约设置主键
     * @return 预约设置
     */
    public TOrdersetting selectTOrdersettingById(Long id);

    /**
     * 查询预约设置列表
     *
     * @param tOrdersetting 预约设置
     * @return 预约设置集合
     */
    public List<TOrdersetting> selectTOrdersettingList(TOrdersetting tOrdersetting);

    /**
     * 新增预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    public int insertTOrdersetting(TOrdersetting tOrdersetting);

    /**
     * 修改预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    public int updateTOrdersetting(TOrdersetting tOrdersetting);

    /**
     * 批量删除预约设置
     *
     * @param ids 需要删除的预约设置主键集合
     * @return 结果
     */
    public int deleteTOrdersettingByIds(Long[] ids);

    /**
     * 删除预约设置信息
     *
     * @param id 预约设置主键
     * @return 结果
     */
    public int deleteTOrdersettingById(Long id);

    /**
     * 按年月获取预约设置（返回视图对象）
     *
     * @param month 月份，格式 YYYY-MM
     * @return 预约设置VO列表
     */
    public List<OrderSettingVO> getOrderSettingByMonth(String month);

    /**
     * 导入预约设置数据
     *
     * @param file 导入的文件
     * @return 导入结果信息
     */
    public AjaxResult importOrderSetting(MultipartFile file) throws Exception;
}
