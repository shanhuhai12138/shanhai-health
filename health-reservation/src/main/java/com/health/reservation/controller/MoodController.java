package com.health.reservation.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.common.utils.DateUtils;
import com.health.reservation.domain.MoodRecord;
import com.health.reservation.service.IMoodService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 情绪记录Controller
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@RestController
@RequestMapping("/reservation/mood")
public class MoodController extends BaseController
{
    @Autowired
    private IMoodService moodService;

    /**
     * 查询情绪记录列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:list')")
    @GetMapping("/list")
    public TableDataInfo list(MoodRecord moodRecord)
    {
        startPage();
        List<MoodRecord> list = moodService.selectMoodRecordList(moodRecord);
        return getDataTable(list);
    }

    /**
     * 导出情绪记录列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:export')")
    @Log(title = "情绪记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MoodRecord moodRecord)
    {
        List<MoodRecord> list = moodService.selectMoodRecordList(moodRecord);
        ExcelUtil<MoodRecord> util = new ExcelUtil<MoodRecord>(MoodRecord.class);
        util.exportExcel(response, list, "情绪记录数据");
    }

    /**
     * 查询情绪记录详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(moodService.selectMoodRecordById(id));
    }

    /**
     * 新增情绪记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:add')")
    @Log(title = "情绪记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MoodRecord moodRecord)
    {
        return toAjax(moodService.insertMoodRecord(moodRecord));
    }

    /**
     * 修改情绪记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:edit')")
    @Log(title = "情绪记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MoodRecord moodRecord)
    {
        return toAjax(moodService.updateMoodRecord(moodRecord));
    }

    /**
     * 删除情绪记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:remove')")
    @Log(title = "情绪记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(moodService.deleteMoodRecordByIds(ids));
    }

    /**
     * 月度情绪统计
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/statistics/monthly")
    public AjaxResult monthlyStats(@RequestParam("userId") Long userId,
                                    @RequestParam(value = "yearMonth", required = false) String yearMonth)
    {
        if (yearMonth == null || yearMonth.isEmpty())
        {
            yearMonth = DateUtils.getDate();
        }
        return success(moodService.selectMonthlyStats(userId, yearMonth));
    }

    /**
     * 情绪趋势数据（最近N天）
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/statistics/trend")
    public AjaxResult trendData(@RequestParam("userId") Long userId,
                                 @RequestParam(value = "days", defaultValue = "30") Integer days)
    {
        return success(moodService.selectTrendData(userId, days));
    }

    /**
     * 情绪分布饼图数据
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/statistics/chart")
    public AjaxResult chartData(@RequestParam("userId") Long userId)
    {
        return success(moodService.selectDistribution(userId));
    }

    /**
     * 健康因素相关性分析
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/statistics/health-factors")
    public AjaxResult healthFactors(@RequestParam("userId") Long userId)
    {
        return success(moodService.selectHealthFactors(userId));
    }

    /**
     * 综合统计摘要（Dashboard用）
     */
    @PreAuthorize("@ss.hasPermi('reservation:mood:query')")
    @GetMapping("/statistics/summary")
    public AjaxResult summary(@RequestParam("userId") Long userId)
    {
        return success(moodService.selectSummary(userId));
    }
}
