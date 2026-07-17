package com.health.reservation.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.health.common.annotation.Anonymous;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.reservation.domain.TSetmeal;
import com.health.reservation.service.IUserReservationService;

/**
 * 用户端预约Controller（匿名访问）
 */
@Anonymous
@RestController
@RequestMapping("/reservation/user")
public class UserReservationController extends BaseController
{
    @Autowired
    private IUserReservationService userReservationService;

    /**
     * 可预约套餐列表
     * GET /reservation/user/setmeals?sex=0&age=18-60
     */
    @GetMapping("/setmeals")
    public AjaxResult getSetmeals(@RequestParam(required = false) String sex,
                                   @RequestParam(required = false) String age)
    {
        List<TSetmeal> list = userReservationService.getAvailableSetmeals(sex, age);
        return success(list);
    }

    /**
     * 套餐详情（含检查组和检查项）
     * GET /reservation/user/setmeal/{id}/details
     */
    @GetMapping("/setmeal/{id}/details")
    public AjaxResult getSetmealDetails(@PathVariable("id") Long id)
    {
        Map<String, Object> detail = userReservationService.getSetmealDetails(id);
        return success(detail);
    }

    /**
     * 可预约日期列表
     * GET /reservation/user/available-dates?setmealId=1
     */
    @GetMapping("/available-dates")
    public AjaxResult getAvailableDates(@RequestParam(required = false) Long setmealId)
    {
        List<Map<String, Object>> list = userReservationService.getAvailableDates(setmealId);
        return success(list);
    }

    /**
     * 创建预约订单
     * POST /reservation/user/order
     * Body: { userId, setmealId, orderDate, checkgroupIds: [1,2,3], memberName, memberPhone, memberIdcard }
     */
    @PostMapping("/order")
    public AjaxResult createOrder(@RequestBody Map<String, Object> params)
    {
        try {
            Long userId = params.get("userId") != null ? Long.valueOf(params.get("userId").toString()) : null;
            Long setmealId = Long.valueOf(params.get("setmealId").toString());
            String orderDateStr = params.get("orderDate").toString();
            String memberName = params.get("memberName").toString();
            String memberPhone = params.get("memberPhone").toString();
            String memberIdcard = params.get("memberIdcard").toString();
            String checkgroupIdsStr = params.get("checkgroupIds") != null ? params.get("checkgroupIds").toString() : null;

            // 解析日期
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = sdf.parse(orderDateStr);

            Long[] checkgroupIds = null;
            if (checkgroupIdsStr != null && !checkgroupIdsStr.isEmpty()) {
                String trimmed = checkgroupIdsStr.trim();
                if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                    trimmed = trimmed.substring(1, trimmed.length() - 1);
                }
                if (!trimmed.isEmpty()) {
                    String[] parts = trimmed.split(",");
                    checkgroupIds = new Long[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        checkgroupIds[i] = Long.parseLong(parts[i].trim());
                    }
                }
            }

            String reportNo = userReservationService.createOrder(userId, setmealId, orderDate, checkgroupIds, memberName, memberPhone, memberIdcard);
            return success(Map.of("reportNo", reportNo, "message", "预约成功"));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 取消预约
     */
    @PutMapping("/cancel/{reportNo}")
    public AjaxResult cancelOrder(@PathVariable("reportNo") String reportNo)
    {
        try {
            userReservationService.cancelOrder(reportNo);
            return success("取消成功");
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}
