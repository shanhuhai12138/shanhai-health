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
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.service.ITOrdersettingService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 预约设置Controller
 * 
 * @author ruoyi
 * @date 2026-06-30
 */
@RestController
@RequestMapping("/reservation/ordersetting")
public class TOrdersettingController extends BaseController
{
    @Autowired
    private ITOrdersettingService tOrdersettingService;

    /**
     * 查询预约设置列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(TOrdersetting tOrdersetting)
    {
        startPage();
        List<TOrdersetting> list = tOrdersettingService.selectTOrdersettingList(tOrdersetting);
        return getDataTable(list);
    }

    /**
     * 导出预约设置列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:export')")
    @Log(title = "预约设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TOrdersetting tOrdersetting)
    {
        List<TOrdersetting> list = tOrdersettingService.selectTOrdersettingList(tOrdersetting);
        ExcelUtil<TOrdersetting> util = new ExcelUtil<TOrdersetting>(TOrdersetting.class);
        util.exportExcel(response, list, "预约设置数据");
    }

    /**
     * 获取预约设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tOrdersettingService.selectTOrdersettingById(id));
    }

    /**
     * 新增预约设置
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:add')")
    @Log(title = "预约设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TOrdersetting tOrdersetting)
    {
        return toAjax(tOrdersettingService.insertTOrdersetting(tOrdersetting));
    }

    /**
     * 修改预约设置
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:edit')")
    @Log(title = "预约设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TOrdersetting tOrdersetting)
    {
        return toAjax(tOrdersettingService.updateTOrdersetting(tOrdersetting));
    }

    /**
     * 删除预约设置
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:remove')")
    @Log(title = "预约设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tOrdersettingService.deleteTOrdersettingByIds(ids));
    }
}
