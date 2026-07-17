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
import com.health.common.annotation.Anonymous;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.reservation.domain.Assessment;
import com.health.reservation.domain.AssessmentQuestion;
import com.health.reservation.domain.AssessmentResult;
import com.health.reservation.service.IAssessmentService;
import com.health.reservation.service.IAssessmentQuestionService;
import com.health.reservation.service.IAssessmentResultService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;
import com.health.common.utils.SecurityUtils;

/**
 * 心理量表管理Controller
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@RestController
@RequestMapping("/assessment")
public class AssessmentController extends BaseController
{
    @Autowired
    private IAssessmentService assessmentService;

    @Autowired
    private IAssessmentQuestionService assessmentQuestionService;

    @Autowired
    private IAssessmentResultService assessmentResultService;

    /**
     * 查询量表列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Assessment assessment)
    {
        startPage();
        List<Assessment> list = assessmentService.selectAssessmentList(assessment);
        return getDataTable(list);
    }

    /**
     * 导出量表列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:export')")
    @Log(title = "心理量表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Assessment assessment)
    {
        List<Assessment> list = assessmentService.selectAssessmentList(assessment);
        ExcelUtil<Assessment> util = new ExcelUtil<Assessment>(Assessment.class);
        util.exportExcel(response, list, "心理量表数据");
    }

    /**
     * 查询量表题目列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:list')")
    @GetMapping("/questions/{assessmentId}")
    public AjaxResult getQuestions(@PathVariable("assessmentId") Long assessmentId)
    {
        AssessmentQuestion question = new AssessmentQuestion();
        question.setAssessmentId(assessmentId);
        List<AssessmentQuestion> list = assessmentQuestionService.selectAssessmentQuestionList(question);
        return success(list);
    }

    /**
     * 查询量表详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assessmentService.selectAssessmentById(id));
    }

    /**
     * 新增量表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:add')")
    @Log(title = "心理量表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Assessment assessment)
    {
        return toAjax(assessmentService.insertAssessment(assessment));
    }

    /**
     * 修改量表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:edit')")
    @Log(title = "心理量表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Assessment assessment)
    {
        return toAjax(assessmentService.updateAssessment(assessment));
    }

    /**
     * 删除量表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:remove')")
    @Log(title = "心理量表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assessmentService.deleteAssessmentByIds(ids));
    }

    /**
     * 查询测评结果列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:list')")
    @GetMapping("/results")
    public TableDataInfo listResults(AssessmentResult assessmentResult)
    {
        startPage();
        List<AssessmentResult> list = assessmentResultService.selectAssessmentResultList(assessmentResult);
        return getDataTable(list);
    }

    /**
     * 查询测评结果详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:query')")
    @GetMapping("/result/{id}")
    public AjaxResult getResultInfo(@PathVariable("id") Long id)
    {
        return success(assessmentResultService.selectAssessmentResultById(id));
    }

    /**
     * 新增测评结果
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:add')")
    @Log(title = "测评结果", businessType = BusinessType.INSERT)
    @PostMapping("/result")
    public AjaxResult addResult(@RequestBody AssessmentResult assessmentResult)
    {
        return toAjax(assessmentResultService.insertAssessmentResult(assessmentResult));
    }

    /**
     * 删除测评结果
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:remove')")
    @Log(title = "测评结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/result/{ids}")
    public AjaxResult removeResult(@PathVariable Long[] ids)
    {
        return toAjax(assessmentResultService.deleteAssessmentResultByIds(ids));
    }

    /**
     * 提交量表测评并自动评分(用户端)
     */
    @Anonymous
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody Map<String, Object> submitData)
    {
        Map<String, Object> result = assessmentService.submitAssessment(submitData);
        return success(result);
    }

    /**
     * 获取量表及题目(用于答题,用户端)
     */
    @Anonymous
    @GetMapping("/take/{id}")
    public AjaxResult take(@PathVariable("id") Long id)
    {
        Map<String, Object> result = assessmentService.getAssessmentForTake(id);
        return success(result);
    }

    /**
     * 查询用户测评历史
     */
    @PreAuthorize("@ss.hasPermi('reservation:assessment:list')")
    @GetMapping("/history")
    public TableDataInfo history(@RequestParam(required = false) Long userId)
    {
        Long queryUserId = userId != null ? userId : SecurityUtils.getUserId();
        startPage();
        List<AssessmentResult> list = assessmentResultService.selectAssessmentHistoryWithNames(queryUserId);
        return getDataTable(list);
    }

    /**
    * 查询用户测评历史(用户端,匿名)
    */
    @Anonymous
    @GetMapping("/myHistory")
    public AjaxResult myHistory(@RequestParam Long userId)
    {
        List<AssessmentResult> list = assessmentResultService.selectAssessmentHistoryWithNames(userId);
        return success(list);
    }
}
