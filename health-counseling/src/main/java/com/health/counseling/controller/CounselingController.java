package com.health.counseling.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.counseling.domain.Counselor;
import com.health.counseling.domain.Appointment;
import com.health.counseling.service.ICounselingService;
import com.health.common.core.page.TableDataInfo;

@RestController
@RequestMapping("/counseling")
public class CounselingController extends BaseController
{
    @Autowired
    private ICounselingService counselingService;

    @GetMapping("/counselor/list")
    public TableDataInfo counselorList(Counselor counselor)
    {
        startPage();
        List<Counselor> list = counselingService.selectCounselorList(counselor);
        return getDataTable(list);
    }

    @GetMapping("/counselor/{id}")
    public AjaxResult getCounselor(@PathVariable("id") Long id)
    {
        return success(counselingService.selectCounselorById(id));
    }

    @Log(title = "咨询师管理", businessType = BusinessType.INSERT)
    @PostMapping("/counselor")
    public AjaxResult addCounselor(@RequestBody Counselor counselor)
    {
        return toAjax(counselingService.insertCounselor(counselor));
    }

    @Log(title = "咨询师管理", businessType = BusinessType.UPDATE)
    @PutMapping("/counselor")
    public AjaxResult editCounselor(@RequestBody Counselor counselor)
    {
        return toAjax(counselingService.updateCounselor(counselor));
    }

    @Log(title = "咨询师管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/counselor/{ids}")
    public AjaxResult removeCounselor(@PathVariable Long[] ids)
    {
        return toAjax(counselingService.deleteCounselorByIds(ids));
    }

    @GetMapping("/appointment/list")
    public TableDataInfo appointmentList(Appointment appointment)
    {
        startPage();
        List<Appointment> list = counselingService.selectAppointmentList(appointment);
        return getDataTable(list);
    }

    @GetMapping("/appointment/{id}")
    public AjaxResult getAppointment(@PathVariable("id") Long id)
    {
        return success(counselingService.selectAppointmentById(id));
    }

    @Log(title = "预约管理", businessType = BusinessType.INSERT)
    @PostMapping("/appointment")
    public AjaxResult addAppointment(@RequestBody Appointment appointment)
    {
        appointment.setUserId(getUserId());
        return toAjax(counselingService.insertAppointment(appointment));
    }

    @Log(title = "预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/appointment")
    public AjaxResult editAppointment(@RequestBody Appointment appointment)
    {
        return toAjax(counselingService.updateAppointment(appointment));
    }

    @Log(title = "预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/appointment/{ids}")
    public AjaxResult removeAppointment(@PathVariable Long[] ids)
    {
        return toAjax(counselingService.deleteAppointmentByIds(ids));
    }
}
