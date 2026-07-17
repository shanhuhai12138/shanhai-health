package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.Assessment;

/**
 * 心理量表Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface AssessmentMapper
{
    /**
     * 查询心理量表
     *
     * @param id 心理量表主键
     * @return 心理量表
     */
    public Assessment selectAssessmentById(Long id);

    /**
     * 查询心理量表列表
     *
     * @param assessment 心理量表
     * @return 心理量表集合
     */
    public List<Assessment> selectAssessmentList(Assessment assessment);

    /**
     * 新增心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    public int insertAssessment(Assessment assessment);

    /**
     * 修改心理量表
     *
     * @param assessment 心理量表
     * @return 结果
     */
    public int updateAssessment(Assessment assessment);

    /**
     * 删除心理量表
     *
     * @param id 心理量表主键
     * @return 结果
     */
    public int deleteAssessmentById(Long id);

    /**
     * 批量删除心理量表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssessmentByIds(Long[] ids);

    /**
     * 查询量表及题目(关联查询)
     *
     * @param assessmentId 量表ID
     * @return 量表信息
     */
    public Assessment selectAssessmentWithQuestions(Long assessmentId);
}
