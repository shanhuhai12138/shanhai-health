package com.health.reservation.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
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
import org.springframework.web.multipart.MultipartFile;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.common.utils.DateUtils;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;
import com.health.reservation.domain.TOrdersetting;
import com.health.reservation.service.ITOrdersettingService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 预约设置Controller
 *
 * @author ruoyi
 * @date 2026-06-30
 */
@RestController
@RequestMapping("/reservation/ordersetting")
public class TOrdersettingController extends BaseController
{
    @Autowired
    private ITOrdersettingService tOrdersettingService;

    /**
     * 查询预约设置列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(TOrdersetting tOrdersetting)
    {
        startPage();
        List<TOrdersetting> list = tOrdersettingService.selectTOrdersettingList(tOrdersetting);
        return getDataTable(list);
    }

    /**
     * 根据年月查询预约设置数据
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:list')")
    @GetMapping("/getOrderSettingByMonth")
    public AjaxResult getOrderSettingByMonth(@RequestParam String month)
    {
        // 解析年月，构造查询条件
        String[] parts = month.split("-");
        int year = Integer.parseInt(parts[0]);
        int mon = Integer.parseInt(parts[1]);
        // 月初和月末
        String startStr = String.format("%04d-%02d-01", year, mon);
        Calendar cal = Calendar.getInstance();
        cal.set(year, mon - 1, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String endStr = String.format("%04d-%02d-%02d", year, mon, maxDay);
        Date startDate = DateUtils.dateTime("yyyy-MM-dd", startStr);
        Date endDate = DateUtils.dateTime("yyyy-MM-dd", endStr);

        TOrdersetting query = new TOrdersetting();
        List<TOrdersetting> list = tOrdersettingService.selectTOrdersettingList(query);

        // 过滤出当月范围内的数据
        List<TOrdersetting> result = list.stream()
            .filter(item -> item.getOrderDate() != null
                && !item.getOrderDate().before(startDate)
                && !item.getOrderDate().after(endDate))
            .toList();
        return success(result);
    }

    /**
     * 编辑某天的可预约人数
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:edit')")
    @PutMapping("/editNumberByOrderDate")
    public AjaxResult editNumberByOrderDate(@RequestBody Map<String, Object> params)
    {
        Long id = Long.valueOf(params.get("id").toString());
        Long number = Long.valueOf(params.get("number").toString());
        TOrdersetting setting = tOrdersettingService.selectTOrdersettingById(id);
        if (setting != null)
        {
            setting.setNumber(number);
            tOrdersettingService.updateTOrdersetting(setting);
        }
        return success();
    }

    /**
     * 新增预约设置
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:add')")
    @Log(title = "预约设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TOrdersetting tOrdersetting)
    {
        tOrdersetting.setReservations(0L);
        tOrdersetting.setCreateTime(DateUtils.getNowDate());
        tOrdersettingService.insertTOrdersetting(tOrdersetting);
        return success(tOrdersetting);
    }

    /**
     * 上传预约设置Excel文件
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:add')")
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile excelFile)
    {
        try
        {
            ExcelUtil<TOrdersetting> util = new ExcelUtil<TOrdersetting>(TOrdersetting.class);
            List<TOrdersetting> settingList = util.importExcel(excelFile.getInputStream());
            String msg = "导入成功，共导入 " + settingList.size() + " 条数据。";
            for (TOrdersetting setting : settingList)
            {
                if (setting.getId() != null && setting.getNumber() != null)
                {
                    // 更新
                    tOrdersettingService.updateTOrdersetting(setting);
                }
                else
                {
                    // 新增
                    if (setting.getNumber() == null)
                        setting.setNumber(0L);
                    if (setting.getReservations() == null)
                        setting.setReservations(0L);
                    setting.setCreateTime(DateUtils.getNowDate());
                    tOrdersettingService.insertTOrdersetting(setting);
                }
            }
            return AjaxResult.success(msg);
        }
        catch (Exception e)
        {
            return AjaxResult.error("导入失败：" + e.getMessage());
        }
    }

    /**
     * 下载预约设置模板
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response)
    {
        try
        {
            // 创建一个空的工作簿作为模板
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("预约设置模板");

            // 表头
            Row headerRow = sheet.createRow(0);
            String[] headers = {"日期", "可预约人数"};
            for (int i = 0; i < headers.length; i++)
            {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // 示例数据
            Row exampleRow = sheet.createRow(1);
            exampleRow.createCell(0).setCellValue("2026-07-01");
            exampleRow.createCell(1).setCellValue(100);

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = URLEncoder.encode("ordersetting_template", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            workbook.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取预约设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tOrdersettingService.selectTOrdersettingById(id));
    }

    /**
     * 导出预约设置列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:export')")
    @Log(title = "预约设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TOrdersetting tOrdersetting)
    {
        List<TOrdersetting> list = tOrdersettingService.selectTOrdersettingList(tOrdersetting);
        ExcelUtil<TOrdersetting> util = new ExcelUtil<TOrdersetting>(TOrdersetting.class);
        util.exportExcel(response, list, "预约设置数据");
    }

    /**
     * 删除预约设置
     */
    @PreAuthorize("@ss.hasPermi('reservation:ordersetting:remove')")
    @Log(title = "预约设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tOrdersettingService.deleteTOrdersettingByIds(ids));
    }
}
