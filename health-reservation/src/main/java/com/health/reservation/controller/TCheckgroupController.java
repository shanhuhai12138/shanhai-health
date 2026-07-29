package com.health.reservation.controller;

import java.util.List;
import jakarta.validation.Valid;
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
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.service.ITCheckgroupService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 检查组管理Controller
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
@RestController
@RequestMapping("/reservation/checkgroup")
public class TCheckgroupController extends BaseController
{
    @Autowired
    private ITCheckgroupService tCheckgroupService;

    /**
     * 查询检查组管理列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCheckgroup tCheckgroup)
    {
        startPage();
        List<TCheckgroup> list = tCheckgroupService.selectTCheckgroupList(tCheckgroup);
        return getDataTable(list);
    }

    /**
     * 导出检查组管理列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:export')")
    @Log(title = "检查组管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCheckgroup tCheckgroup)
    {
        List<TCheckgroup> list = tCheckgroupService.selectTCheckgroupList(tCheckgroup);
        ExcelUtil<TCheckgroup> util = new ExcelUtil<TCheckgroup>(TCheckgroup.class);
        util.exportExcel(response, list, "检查组管理数据");
    }

    /**
     * 获取检查组管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCheckgroupService.selectTCheckgroupById(id));
    }

    /**
     * 新增检查组管理
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:add')")
    @Log(title = "检查组管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody TCheckgroup tCheckgroup)
    {
        return toAjax(tCheckgroupService.insertTCheckgroup(tCheckgroup));
    }

    /**
     * 修改检查组管理
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:edit')")
    @Log(title = "检查组管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody TCheckgroup tCheckgroup)
    {
        return toAjax(tCheckgroupService.updateTCheckgroup(tCheckgroup));
    }

    /**
     * 删除检查组管理
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:remove')")
    @Log(title = "检查组管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tCheckgroupService.deleteTCheckgroupByIds(ids));
    }

    /**
     * 查询所有检查组列表（不分页）
     */
    @PreAuthorize("@ss.hasPermi('reservation:checkgroup:list')")
    @GetMapping("/all")
    public AjaxResult listAll()
    {
        List<TCheckgroup> list = tCheckgroupService.selectTCheckgroupList(new TCheckgroup());
        return success(list);
    }
}
