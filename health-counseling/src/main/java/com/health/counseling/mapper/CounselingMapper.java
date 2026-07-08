package com.health.counseling.mapper;

import java.util.List;
import com.health.counseling.domain.Counselor;
import com.health.counseling.domain.Appointment;

public interface CounselingMapper
{
    public List<Counselor> selectCounselorList(Counselor counselor);
    public Counselor selectCounselorById(Long id);
    public int insertCounselor(Counselor counselor);
    public int updateCounselor(Counselor counselor);
    public int deleteCounselorById(Long id);
    public int deleteCounselorByIds(Long[] ids);

    public List<Appointment> selectAppointmentList(Appointment appointment);
    public Appointment selectAppointmentById(Long id);
    public int insertAppointment(Appointment appointment);
    public int updateAppointment(Appointment appointment);
    public int deleteAppointmentById(Long id);
    public int deleteAppointmentByIds(Long[] ids);
}
