package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import com.health.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.CounselorMapper;
import com.health.reservation.domain.Counselor;
import com.health.reservation.service.ICounselorService;

/**
 * 咨询师Service业务层处理
 *
 * @author ruoyi
 * @date 2026-07-09
 */
@Service
public class CounselorServiceImpl implements ICounselorService
{
    @Autowired
    private CounselorMapper counselorMapper;

    /**
     * 查询咨询师
     *
     * @param id 咨询师主键
     * @return 咨询师
     */
    @Override
    public Counselor selectCounselorById(Long id)
    {
        return counselorMapper.selectCounselorById(id);
    }

    /**
     * 查询咨询师列表
     *
     * @param counselor 咨询师
     * @return 咨询师
     */
    @Override
    public List<Counselor> selectCounselorList(Counselor counselor)
    {
        return counselorMapper.selectCounselorList(counselor);
    }

    /**
     * 新增咨询师
     *
     * @param counselor 咨询师
     * @return 结果
     */
    @Override
    public int insertCounselor(Counselor counselor)
    {
        counselor.setCreateTime(DateUtils.getNowDate());
        counselor.setCreateBy(SecurityUtils.getUsername());
        return counselorMapper.insertCounselor(counselor);
    }

    /**
     * 修改咨询师
     *
     * @param counselor 咨询师
     * @return 结果
     */
    @Override
    public int updateCounselor(Counselor counselor)
    {
        counselor.setUpdateTime(DateUtils.getNowDate());
        counselor.setUpdateBy(SecurityUtils.getUsername());
        return counselorMapper.updateCounselor(counselor);
    }

    /**
     * 批量删除咨询师
     *
     * @param ids 需要删除的咨询师主键
     * @return 结果
     */
    @Override
    public int deleteCounselorByIds(Long[] ids)
    {
        return counselorMapper.deleteCounselorByIds(ids);
    }

    /**
     * 删除咨询师信息
     *
     * @param id 咨询师主键
     * @return 结果
     */
    @Override
    public int deleteCounselorById(Long id)
    {
        return counselorMapper.deleteCounselorById(id);
    }
}
