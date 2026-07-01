package com.health.reservation.service.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.health.common.core.domain.AjaxResult;
import com.health.reservation.vo.OrderSettingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.health.common.utils.poi.ExcelUtil;
import com.health.reservation.mapper.TOrdersettingMapper;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.service.ITOrdersettingService;

/**
 * 预约设置Service业务层处理
 *
 * @author ruoyi
 * @date 2026-06-30
 */
@Service
public class TOrdersettingServiceImpl implements ITOrdersettingService
{
    private static final Logger log = LoggerFactory.getLogger(TOrdersettingServiceImpl.class);

    @Autowired
    private TOrdersettingMapper tOrdersettingMapper;

    /**
     * 查询预约设置
     *
     * @param id 预约设置主键
     * @return 预约设置
     */
    @Override
    public TOrdersetting selectTOrdersettingById(Long id)
    {
        return tOrdersettingMapper.selectTOrdersettingById(id);
    }

    /**
     * 查询预约设置列表
     *
     * @param tOrdersetting 预约设置
     * @return 预约设置
     */
    @Override
    public List<TOrdersetting> selectTOrdersettingList(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.selectTOrdersettingList(tOrdersetting);
    }

    /**
     * 新增预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    @Override
    public int insertTOrdersetting(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.insertTOrdersetting(tOrdersetting);
    }

    /**
     * 修改预约设置
     *
     * @param tOrdersetting 预约设置
     * @return 结果
     */
    @Override
    public int updateTOrdersetting(TOrdersetting tOrdersetting)
    {
        return tOrdersettingMapper.updateTOrdersetting(tOrdersetting);
    }

    /**
     * 批量删除预约设置
     *
     * @param ids 需要删除的预约设置主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersettingByIds(Long[] ids)
    {
        return tOrdersettingMapper.deleteTOrdersettingByIds(ids);
    }

    /**
     * 删除预约设置信息
     *
     * @param id 预约设置主键
     * @return 结果
     */
    @Override
    public int deleteTOrdersettingById(Long id)
    {
        return tOrdersettingMapper.deleteTOrdersettingById(id);
    }

    /**
     * 按年月获取预约设置（返回视图对象）
     *
     * @param month 月份，格式 YYYY-MM
     * @return 预约设置VO列表
     */
    @Override
    public List<OrderSettingVO> getOrderSettingByMonth(String month)
    {
        if (month == null || !month.matches("\\d{4}-\\d{2}"))
        {
            throw new IllegalArgumentException("月份格式错误，应为 YYYY-MM");
        }
        List<TOrdersetting> settings = tOrdersettingMapper.selectByYearMonth(month);
        return settings.stream().map(setting -> {
            OrderSettingVO vo = new OrderSettingVO();
            vo.setId(setting.getId());
            vo.setDate(setting.getOrderDate().toInstant()
                    .atZone(ZoneId.of("Asia/Shanghai"))
                    .toLocalDate().getDayOfMonth());
            vo.setNumber(setting.getNumber().intValue());
            vo.setReservations(setting.getReservations().intValue());
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 导入预约设置数据
     *
     * @param file 导入的文件
     * @return 导入结果信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importOrderSetting(MultipartFile file) throws Exception
    {
        // 1. 使用若依内置 ExcelUtil 解析 Excel
        ExcelUtil<TOrdersetting> util = new ExcelUtil<TOrdersetting>(TOrdersetting.class);
        List<TOrdersetting> list = util.importExcel(file.getInputStream());

        if (list == null || list.isEmpty())
        {
            throw new IllegalArgumentException("Excel 文件内容为空");
        }

        // 2. 遍历数据，判断是新增还是更新（按 orderDate 唯一）
        List<TOrdersetting> insertList = new ArrayList<>();
        List<TOrdersetting> updateList = new ArrayList<>();
        int skipCount = 0;

        for (TOrdersetting setting : list)
        {
            // 跳过 null 行（Excel 中的空行）
            if (setting == null)
            {
                skipCount++;
                continue;
            }
            // 跳过无效数据
            if (setting.getOrderDate() == null || setting.getNumber() == null)
            {
                skipCount++;
                log.warn("跳过无效数据（日期或人数为空）: orderDate={}, number={}", setting.getOrderDate(), setting.getNumber());
                continue;
            }

            // 查询数据库是否已存在该日期的设置
            TOrdersetting existing = tOrdersettingMapper.selectByOrderDate(setting.getOrderDate());
            if (existing != null)
            {
                // 存在：更新（保留 id）
                setting.setId(existing.getId());
                updateList.add(setting);
            }
            else
            {
                // 不存在：新增
                insertList.add(setting);
            }
        }

        log.info("Excel导入: 总行数={}, 新增={}, 更新={}, 跳过={}", list.size(), insertList.size(), updateList.size(), skipCount);

        // 3. 批量插入
        if (!insertList.isEmpty())
        {
            tOrdersettingMapper.insertBatch(insertList);
        }

        // 4. 批量更新（使用若依通用 update 方法，逐条更新更安全）
        for (TOrdersetting item : updateList)
        {
            tOrdersettingMapper.updateTOrdersetting(item);
        }

        // 返回详细统计信息
        return AjaxResult.success("导入完成，共处理 " + list.size() + " 条，新增 " + insertList.size() + " 条，更新 " + updateList.size() + " 条，跳过 " + skipCount + " 条");
    }
}
