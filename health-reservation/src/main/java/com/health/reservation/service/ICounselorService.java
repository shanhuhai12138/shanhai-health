package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.Counselor;

/**
 * 咨询师Service接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface ICounselorService
{
    /**
     * 查询咨询师
     *
     * @param id 咨询师主键
     * @return 咨询师
     */
    public Counselor selectCounselorById(Long id);

    /**
     * 查询咨询师列表
     *
     * @param counselor 咨询师
     * @return 咨询师集合
     */
    public List<Counselor> selectCounselorList(Counselor counselor);

    /**
     * 新增咨询师
     *
     * @param counselor 咨询师
     * @return 结果
     */
    public int insertCounselor(Counselor counselor);

    /**
     * 修改咨询师
     *
     * @param counselor 咨询师
     * @return 结果
     */
    public int updateCounselor(Counselor counselor);

    /**
     * 批量删除咨询师
     *
     * @param ids 需要删除的咨询师主键集合
     * @return 结果
     */
    public int deleteCounselorByIds(Long[] ids);

    /**
     * 删除咨询师信息
     *
     * @param id 咨询师主键
     * @return 结果
     */
    public int deleteCounselorById(Long id);
}
