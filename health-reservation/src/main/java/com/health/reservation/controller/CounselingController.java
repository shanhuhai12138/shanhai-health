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
import com.health.reservation.domain.Counselor;
import com.health.reservation.service.ICounselorService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 咨询师管理Controller
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@RestController
@RequestMapping("/counseling")
public class CounselingController extends BaseController
{
    @Autowired
    private ICounselorService counselorService;

    /**
     * 查询咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:list')")
    @GetMapping("/counselor/list")
    public TableDataInfo list(Counselor counselor)
    {
        startPage();
        List<Counselor> list = counselorService.selectCounselorList(counselor);
        return getDataTable(list);
    }

    /**
     * 导出咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:export')")
    @Log(title = "咨询师管理", businessType = BusinessType.EXPORT)
    @PostMapping("/counselor/export")
    public void export(HttpServletResponse response, Counselor counselor)
    {
        List<Counselor> list = counselorService.selectCounselorList(counselor);
        ExcelUtil<Counselor> util = new ExcelUtil<Counselor>(Counselor.class);
        util.exportExcel(response, list, "咨询师数据");
    }

    /**
     * 查询咨询师详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:query')")
    @GetMapping("/counselor/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(counselorService.selectCounselorById(id));
    }

    /**
     * 新增咨询师
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:add')")
    @Log(title = "咨询师管理", businessType = BusinessType.INSERT)
    @PostMapping("/counselor")
    public AjaxResult add(@RequestBody Counselor counselor)
    {
        return toAjax(counselorService.insertCounselor(counselor));
    }

    /**
     * 修改咨询师
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:edit')")
    @Log(title = "咨询师管理", businessType = BusinessType.UPDATE)
    @PutMapping("/counselor")
    public AjaxResult edit(@RequestBody Counselor counselor)
    {
        return toAjax(counselorService.updateCounselor(counselor));
    }

    /**
     * 删除咨询师
     */
    @PreAuthorize("@ss.hasPermi('reservation:counselor:remove')")
    @Log(title = "咨询师管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/counselor/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(counselorService.deleteCounselorByIds(ids));
    }
}
