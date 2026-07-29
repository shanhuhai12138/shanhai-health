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
import org.springframework.web.bind.annotation.RequestParam;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.Appointment;
import com.health.reservation.service.IAppointmentService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;
import com.health.common.annotation.Anonymous;
import com.health.common.utils.SecurityUtils;

/**
 * 预约记录Controller
 *
 * @author ruoyi
 * @date 2026-07-11
 */
@RestController
@RequestMapping("/reservation/appointment")
public class AppointmentController extends BaseController
{
    @Autowired
    private IAppointmentService appointmentService;

    /**
     * 查询预约记录列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Appointment appointment)
    {
        startPage();
        List<Appointment> list = appointmentService.selectAppointmentList(appointment);
        return getDataTable(list);
    }

    /**
     * 导出预约记录列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:export')")
    @Log(title = "预约记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Appointment appointment)
    {
        List<Appointment> list = appointmentService.selectAppointmentList(appointment);
        ExcelUtil<Appointment> util = new ExcelUtil<Appointment>(Appointment.class);
        util.exportExcel(response, list, "预约记录数据");
    }

    /**
     * 获取预约记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(appointmentService.selectAppointmentById(id));
    }

    /**
     * 新增预约记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:add')")
    @Log(title = "预约记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody Appointment appointment)
    {
        return toAjax(appointmentService.insertAppointment(appointment));
    }

    /**
     * 修改预约记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:edit')")
    @Log(title = "预约记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody Appointment appointment)
    {
        return toAjax(appointmentService.updateAppointment(appointment));
    }

    /**
     * 删除预约记录
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:remove')")
    @Log(title = "预约记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appointmentService.deleteAppointmentByIds(ids));
    }

    /**
     * 取消预约
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:cancel')")
    @Log(title = "取消预约", businessType = BusinessType.UPDATE)
    @PutMapping("/cancel/{id}")
    public AjaxResult cancel(@PathVariable("id") Long id)
    {
        return toAjax(appointmentService.cancelAppointment(id));
    }

    /**
     * 确认预约
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:confirm')")
    @Log(title = "确认预约", businessType = BusinessType.UPDATE)
    @PutMapping("/confirm/{id}")
    public AjaxResult confirm(@PathVariable("id") Long id)
    {
        return toAjax(appointmentService.confirmAppointment(id));
    }

    /**
     * 完成预约
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:complete')")
    @Log(title = "完成预约", businessType = BusinessType.UPDATE)
    @PutMapping("/complete/{id}")
    public AjaxResult complete(@PathVariable("id") Long id)
    {
        return toAjax(appointmentService.completeAppointment(id));
    }

    /**
     * 查询用户预约列表（用户端）
     */
    @Anonymous
    @GetMapping("/user/list")
    public AjaxResult getUserAppointments(@RequestParam Long userId)
    {
        List<Appointment> list = appointmentService.selectAppointmentByUserId(userId);
        return success(list);
    }

    /**
     * 查询咨询师预约列表（咨询师端）
     */
    @PreAuthorize("@ss.hasPermi('reservation:appointment:list')")
    @GetMapping("/counselor/list")
    public AjaxResult getCounselorAppointments(@RequestParam Long counselorId)
    {
        List<Appointment> list = appointmentService.selectAppointmentByCounselorId(counselorId);
        return success(list);
    }

    /**
     * 用户端创建预约（匿名访问）
     */
    @Anonymous
    @PostMapping("/user/create")
    public AjaxResult createUserAppointment(@Valid @RequestBody Appointment appointment)
    {
        // 设置默认状态为待确认
        appointment.setStatus("0");
        return toAjax(appointmentService.insertAppointment(appointment));
    }
}
