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
import org.springframework.web.bind.annotation.RestController;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.service.ITSetmealService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 套餐组Controller
 * 
 * @author ruoyi
 * @date 2026-06-27
 */
@RestController
@RequestMapping("/reservation/setmeal")
public class TSetmealController extends BaseController
{
    @Autowired
    private ITSetmealService tSetmealService;

    /**
     * 查询套餐组列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSetmeal tSetmeal)
    {
        startPage();
        List<TSetmeal> list = tSetmealService.selectTSetmealList(tSetmeal);
        return getDataTable(list);
    }

    /**
     * 导出套餐组列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:export')")
    @Log(title = "套餐组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSetmeal tSetmeal)
    {
        List<TSetmeal> list = tSetmealService.selectTSetmealList(tSetmeal);
        ExcelUtil<TSetmeal> util = new ExcelUtil<TSetmeal>(TSetmeal.class);
        util.exportExcel(response, list, "套餐组数据");
    }

    /**
     * 获取套餐组详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tSetmealService.selectTSetmealById(id));
    }

    /**
     * 新增套餐组
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:add')")
    @Log(title = "套餐组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSetmeal tSetmeal)
    {
        return toAjax(tSetmealService.insertTSetmeal(tSetmeal));
    }

    /**
     * 修改套餐组
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:edit')")
    @Log(title = "套餐组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSetmeal tSetmeal)
    {
        return toAjax(tSetmealService.updateTSetmeal(tSetmeal));
    }

    /**
     * 删除套餐组
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:remove')")
    @Log(title = "套餐组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tSetmealService.deleteTSetmealByIds(ids));
    }

    /**
     * 查询套餐关联的检查组列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:query')")
    @GetMapping("/checkgroups/{setmealId}")
    public TableDataInfo listCheckgroups(@PathVariable("setmealId") Long setmealId)
    {
        List<TCheckgroup> list = tSetmealService.selectTCheckgroupBySetmealId(setmealId);
        return getDataTable(list);
    }

    /**
     * 批量设置套餐关联的检查组
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:edit')")
    @Log(title = "套餐组", businessType = BusinessType.UPDATE)
    @PutMapping("/checkgroups/batch")
    public AjaxResult batchSetCheckgroups(@RequestBody Map<String, Object> params)
    {
        Long setmealId = Long.valueOf(params.get("setmealId").toString());
        @SuppressWarnings("unchecked")
        List<Long> checkgroupIdList = (List<Long>) params.get("checkgroupIds");
        Long[] checkgroupIds = checkgroupIdList != null ? checkgroupIdList.toArray(new Long[0]) : new Long[0];
        return toAjax(tSetmealService.batchSetCheckgroups(setmealId, checkgroupIds));
    }

    /**
     * 查询套餐详情（含关联的检查组和检查项）
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:query')")
    @GetMapping("/details/{setmealId}")
    public AjaxResult getDetail(@PathVariable("setmealId") Long setmealId)
    {
        return success(tSetmealService.selectSetmealDetail(setmealId));
    }

    /**
     * 查询套餐及其关联的检查组（扁平化列表）
     */
    @PreAuthorize("@ss.hasPermi('reservation:setmeal:query')")
    @GetMapping("/{id}/withCheckgroups")
    public AjaxResult getWithCheckgroups(@PathVariable("id") Long id)
    {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        TSetmeal setmeal = tSetmealService.selectTSetmealById(id);
        List<TCheckgroup> checkgroups = tSetmealService.selectTCheckgroupBySetmealId(id);
        result.put("setmeal", setmeal);
        result.put("checkgroups", checkgroups);
        return success(result);
    }
}
