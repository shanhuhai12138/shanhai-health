package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.Counselor;

/**
 * 咨询师Mapper接口
 *
 * @author ruoyi
 * @date 2026-07-09
 */
public interface CounselorMapper
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
     * 删除咨询师
     *
     * @param id 咨询师主键
     * @return 结果
     */
    public int deleteCounselorById(Long id);

    /**
     * 批量删除咨询师
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCounselorByIds(Long[] ids);
}
