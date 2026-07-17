package com.health.reservation.controller;

import java.util.List;
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
import com.health.reservation.domain.CounselorSchedule;
import com.health.reservation.service.ICounselorScheduleService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 咨询师排班Controller
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@RestController
@RequestMapping("/reservation/schedule")
public class CounselorScheduleController extends BaseController
{
    @Autowired
    private ICounselorScheduleService counselorScheduleService;

    /**
     * 查询咨询师排班列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(CounselorSchedule counselorSchedule)
    {
        startPage();
        List<CounselorSchedule> list = counselorScheduleService.selectCounselorScheduleList(counselorSchedule);
        return getDataTable(list);
    }

    /**
     * 导出咨询师排班列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:export')")
    @Log(title = "咨询师排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CounselorSchedule counselorSchedule)
    {
        List<CounselorSchedule> list = counselorScheduleService.selectCounselorScheduleList(counselorSchedule);
        ExcelUtil<CounselorSchedule> util = new ExcelUtil<CounselorSchedule>(CounselorSchedule.class);
        util.exportExcel(response, list, "咨询师排班数据");
    }

    /**
     * 获取咨询师排班详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(counselorScheduleService.selectCounselorScheduleById(id));
    }

    /**
     * 新增咨询师排班
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:add')")
    @Log(title = "咨询师排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CounselorSchedule counselorSchedule)
    {
        return toAjax(counselorScheduleService.insertCounselorSchedule(counselorSchedule));
    }

    /**
     * 修改咨询师排班
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:edit')")
    @Log(title = "咨询师排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CounselorSchedule counselorSchedule)
    {
        return toAjax(counselorScheduleService.updateCounselorSchedule(counselorSchedule));
    }

    /**
     * 删除咨询师排班
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:remove')")
    @Log(title = "咨询师排班", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(counselorScheduleService.deleteCounselorScheduleByIds(ids));
    }

    /**
     * 查询某咨询师的所有排班
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:list')")
    @GetMapping("/counselor/{counselorId}")
    public AjaxResult getCounselorSchedules(@PathVariable("counselorId") Long counselorId)
    {
        List<CounselorSchedule> list = counselorScheduleService.selectScheduleByCounselorId(counselorId);
        return success(list);
    }

    /**
     * 查询某日可用排班
     */
    @PreAuthorize("@ss.hasPermi('reservation:schedule:list')")
    @GetMapping("/available/{date}")
    public AjaxResult getAvailableSchedules(@PathVariable("date") String date)
    {
        List<CounselorSchedule> list = counselorScheduleService.selectAvailableSchedulesByDate(date);
        return success(list);
    }
}
