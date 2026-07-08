package com.health.reservation.controller;

import java.util.List;
import com.health.common.annotation.Anonymous;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.reservation.domain.TReport;
import com.health.reservation.service.ITReportService;
import com.health.reservation.vo.ReportDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 体检报告用户端Controller
 * 通过手机号查询个人报告，无需登录认证
 *
 * @author ruoyi
 * @date 2026-07-02
 */
@Anonymous
@RestController
@RequestMapping("/reservation/report/user")
public class TReportUserController extends BaseController
{
    @Autowired
    private ITReportService tReportService;

    /**
     * 通过手机号查询个人报告列表
     * GET /reservation/report/user/list?phone=xxx&idcard=xxx
     */
    @GetMapping("/list")
    public AjaxResult listByPhone(String phone, String idcard)
    {
        if (phone == null || phone.trim().isEmpty())
        {
            return AjaxResult.error("请输入手机号码");
        }
        List<TReport> list = tReportService.selectReportByPhoneAndIdcard(phone.trim(), idcard != null ? idcard.trim() : "");
        return success(list);
    }

    /**
     * 查询个人报告详情
     * GET /reservation/report/user/detail/{reportId}?phone=xxx&idcard=xxx
     */
    @GetMapping(value = "/detail/{reportId}")
    public AjaxResult getDetail(@PathVariable("reportId") Long reportId,
                                 @RequestParam String phone,
                                 @RequestParam(required = false) String idcard)
    {
        if (phone == null || phone.trim().isEmpty())
        {
            return AjaxResult.error("请输入手机号码");
        }

        TReport report = tReportService.selectTReportById(reportId);
        if (report == null)
        {
            return AjaxResult.error("报告不存在");
        }

        // 双重校验：手机号必须匹配，如有身份证则也需匹配
        if (!phone.trim().equals(report.getMemberPhone()))
        {
            return AjaxResult.error("手机号不匹配，无权查看该报告");
        }
        if (idcard != null && !idcard.trim().isEmpty() && !idcard.trim().equals(report.getMemberIdcard()))
        {
            return AjaxResult.error("身份证号不匹配，无权查看该报告");
        }

        return success(tReportService.selectReportDetail(reportId));
    }
}
