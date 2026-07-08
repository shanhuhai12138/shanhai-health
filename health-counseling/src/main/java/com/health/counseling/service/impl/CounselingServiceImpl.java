package com.health.counseling.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.counseling.domain.Counselor;
import com.health.counseling.domain.Appointment;
import com.health.counseling.mapper.CounselingMapper;
import com.health.counseling.service.ICounselingService;

@Service
public class CounselingServiceImpl implements ICounselingService
{
    @Autowired
    private CounselingMapper counselingMapper;

    @Override
    public List<Counselor> selectCounselorList(Counselor counselor) { return counselingMapper.selectCounselorList(counselor); }
    @Override
    public Counselor selectCounselorById(Long id) { return counselingMapper.selectCounselorById(id); }
    @Override
    public int insertCounselor(Counselor counselor) { return counselingMapper.insertCounselor(counselor); }
    @Override
    public int updateCounselor(Counselor counselor) { return counselingMapper.updateCounselor(counselor); }
    @Override
    public int deleteCounselorById(Long id) { return counselingMapper.deleteCounselorById(id); }
    @Override
    public int deleteCounselorByIds(Long[] ids) { return counselingMapper.deleteCounselorByIds(ids); }

    @Override
    public List<Appointment> selectAppointmentList(Appointment appointment) { return counselingMapper.selectAppointmentList(appointment); }
    @Override
    public Appointment selectAppointmentById(Long id) { return counselingMapper.selectAppointmentById(id); }
    @Override
    public int insertAppointment(Appointment appointment) { return counselingMapper.insertAppointment(appointment); }
    @Override
    public int updateAppointment(Appointment appointment) { return counselingMapper.updateAppointment(appointment); }
    @Override
    public int deleteAppointmentById(Long id) { return counselingMapper.deleteAppointmentById(id); }
    @Override
    public int deleteAppointmentByIds(Long[] ids) { return counselingMapper.deleteAppointmentByIds(ids); }
}
