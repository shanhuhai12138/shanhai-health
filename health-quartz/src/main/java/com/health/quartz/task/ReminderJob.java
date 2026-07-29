package com.health.quartz.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.health.quartz.domain.SysJob;
import com.health.quartz.util.AbstractQuartzJob;
import com.health.reservation.domain.Appointment;
import com.health.reservation.service.IAppointmentService;
import com.health.reservation.service.INotificationService;
import com.health.common.utils.bean.BeanUtils;
import com.health.common.utils.spring.SpringUtils;
import java.util.Date;
import java.util.List;

/**
 * 预约提醒任务
 *
 * @author ruoyi
 */
public class ReminderJob extends AbstractQuartzJob {

    private static final Logger log = LoggerFactory.getLogger(ReminderJob.class);

    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        try {
            // 获取服务实例
            IAppointmentService appointmentService = SpringUtils.getBean(IAppointmentService.class);
            INotificationService notificationService = SpringUtils.getBean(INotificationService.class);

            // 查询未来24小时内已确认的预约（简单版）
            Date now = new Date();
            Date future24Hours = new Date(now.getTime() + 24 * 60 * 60 * 1000);

            // 创建查询对象
            Appointment queryAppointment = new Appointment();
            queryAppointment.setStatus("1"); // 已确认

            // 获取所有预约（演示用，实际应添加时间范围过滤）
            List<Appointment> list = appointmentService.selectAppointmentList(queryAppointment);

            for (Appointment appt : list) {
                Date appTime = appt.getAppointmentTime();
                if (appTime != null && appTime.after(now) && appTime.before(future24Hours)) {
                    try {
                        // 发送提醒通知
                        notificationService.createNotification(
                            appt.getUserId(),
                            "appointment",
                            "预约提醒",
                            "您的预约即将开始，请准时参加预约时间：" + appt.getAppointmentTime(),
                            appt.getId(),
                            "appointment"
                        );
                        log.info("已发送预约提醒给用户{}，预约ID:{}", appt.getUserId(), appt.getId());
                    } catch (Exception e) {
                        log.error("发送预约提醒失败，用户{}，预约ID:{}", appt.getUserId(), appt.getId(), e);
                    }
                }
            }

            log.info("预约提醒任务执行完成，共检查{}条预约", list.size());

        } catch (Exception e) {
            log.error("预约提醒任务执行异常", e);
            throw e;
        }
    }
}
