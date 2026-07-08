package com.health.assessment.controller;

import java.util.List;
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
import com.health.assessment.domain.Assessment;
import com.health.assessment.domain.AssessmentQuestion;
import com.health.assessment.domain.AssessmentResult;
import com.health.assessment.service.IAssessmentService;
import com.health.common.core.page.TableDataInfo;

/**
 * 心理测评Controller
 *
 * @author ruoyi
 * @date 2026-07-08
 */
@RestController
@RequestMapping("/assessment")
public class AssessmentController extends BaseController
{
    @Autowired
    private IAssessmentService assessmentService;

    /**
     * 查询量表列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Assessment assessment)
    {
        startPage();
        List<Assessment> list = assessmentService.selectAssessmentList(assessment);
        return getDataTable(list);
    }

    /**
     * 查询量表详情
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assessmentService.selectAssessmentById(id));
    }

    /**
     * 根据编码查询量表
     */
    @GetMapping("/byCode/{code}")
    public AjaxResult getByCode(@PathVariable("code") String code)
    {
        return success(assessmentService.selectAssessmentByCode(code));
    }

    /**
     * 查询量表题目列表
     */
    @GetMapping("/questions/{assessmentId}")
    public AjaxResult getQuestions(@PathVariable("assessmentId") Long assessmentId)
    {
        return success(assessmentService.selectQuestionsByAssessmentId(assessmentId));
    }

    /**
     * 新增量表
     */
    @Log(title = "量表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Assessment assessment)
    {
        return toAjax(assessmentService.insertAssessment(assessment));
    }

    /**
     * 修改量表
     */
    @Log(title = "量表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Assessment assessment)
    {
        return toAjax(assessmentService.updateAssessment(assessment));
    }

    /**
     * 删除量表
     */
    @Log(title = "量表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assessmentService.deleteAssessmentByIds(ids));
    }

    /**
     * 查询用户测评结果列表
     */
    @GetMapping("/results")
    public TableDataInfo results()
    {
        Long userId = getUserId();
        List<AssessmentResult> list = assessmentService.selectResultsByUserId(userId);
        return getDataTable(list);
    }

    /**
     * 查询测评结果详情
     */
    @GetMapping("/result/{id}")
    public AjaxResult getResult(@PathVariable("id") Long id)
    {
        return success(assessmentService.selectResultById(id));
    }

    /**
     * 新增测评结果
     */
    @Log(title = "测评结果", businessType = BusinessType.INSERT)
    @PostMapping("/result")
    public AjaxResult addResult(@RequestBody AssessmentResult result)
    {
        result.setUserId(getUserId());
        return toAjax(assessmentService.insertResult(result));
    }

    /**
     * 批量删除量表
     */
    public int deleteAssessmentByIds(Long[] ids)
    {
        for (Long id : ids) {
            assessmentService.deleteAssessmentById(id);
        }
        return ids.length;
    }
}
