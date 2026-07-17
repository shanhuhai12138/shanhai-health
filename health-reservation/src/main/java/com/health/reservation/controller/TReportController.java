package com.health.reservation.controller;

import java.util.*;
import java.text.SimpleDateFormat;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.common.utils.SecurityUtils;
import com.health.common.utils.StringUtils;
import com.health.reservation.domain.TReport;
import com.health.reservation.service.ITReportService;
import com.health.common.core.page.TableDataInfo;
import com.health.common.utils.poi.ExcelUtil;
import com.health.reservation.vo.ReportDetailVO;
import com.health.reservation.domain.TReportImage;
import com.health.reservation.service.ITReportImageService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 体检报告Controller
 *
 * @author ruoyi
 * @date 2026-07-02
 */
@RestController
@RequestMapping("/reservation/report")
public class TReportController extends BaseController
{
    @Autowired
    private ITReportService tReportService;

    @Autowired
    private ITReportImageService tReportImageService;

    /**
     * 查询体检报告列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(TReport tReport)
    {
        startPage();
        List<TReport> list = tReportService.selectTReportList(tReport);
        return getDataTable(list);
    }

    /**
     * 导出体检报告列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:export')")
    @Log(title = "体检报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TReport tReport)
    {
        List<TReport> list = tReportService.selectTReportList(tReport);
        ExcelUtil<TReport> util = new ExcelUtil<TReport>(TReport.class);
        util.exportExcel(response, list, "体检报告数据");
    }

    /**
     * 获取体检报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tReportService.selectTReportById(id));
    }

    /**
     * 获取体检报告详情（含分组明细）
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:query')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult getDetail(@PathVariable("id") Long id)
    {
        return success(tReportService.selectReportDetail(id));
    }

    /**
     * 新增体检报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:add')")
    @Log(title = "体检报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TReport tReport)
    {
        return toAjax(tReportService.insertTReport(tReport));
    }

    /**
     * 修改体检报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:edit')")
    @Log(title = "体检报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TReport tReport)
    {
        return toAjax(tReportService.updateTReport(tReport));
    }

    /**
     * 删除体检报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:remove')")
    @Log(title = "体检报告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tReportService.deleteTReportByIds(ids));
    }

    /**
     * 审核报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:audit')")
    @Log(title = "体检报告审核", businessType = BusinessType.UPDATE)
    @PutMapping("/audit/{id}")
    public AjaxResult audit(@PathVariable("id") Long id)
    {
        Long userId = SecurityUtils.getUserId();
        String nickname = SecurityUtils.getLoginUser().getUser().getNickName();
        return toAjax(tReportService.auditReport(id, userId, nickname));
    }

    /**
     * 发布报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:publish')")
    @Log(title = "体检报告发布", businessType = BusinessType.UPDATE)
    @PutMapping("/publish/{id}")
    public AjaxResult publish(@PathVariable("id") Long id)
    {
        Long userId = SecurityUtils.getUserId();
        String nickname = SecurityUtils.getLoginUser().getUser().getNickName();
        return toAjax(tReportService.publishReport(id, userId, nickname));
    }

    /**
     * 归档报告
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:archive')")
    @Log(title = "体检报告归档", businessType = BusinessType.UPDATE)
    @PutMapping("/archive/{id}")
    public AjaxResult archive(@PathVariable("id") Long id)
    {
        return toAjax(tReportService.archiveReport(id));
    }

    /**
     * 下载体检报告导入模板
     */
    // ========== 体检报告图像 CRUD ==========

    /**
     * 查询图像列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:list')")
    @GetMapping("/image/list")
    public TableDataInfo imageList(TReportImage tReportImage)
    {
        startPage();
        List<TReportImage> list = tReportImageService.selectTReportImageList(tReportImage);
        return getDataTable(list);
    }

    /**
     * 查询图像详情
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:query')")
    @GetMapping(value = "/image/{id}")
    public AjaxResult getImage(@PathVariable("id") Long id)
    {
        return success(tReportImageService.selectTReportImageById(id));
    }

    /**
     * 根据报告ID查询图像列表
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:query')")
    @GetMapping("/image/report/{reportId}")
    public AjaxResult getImagesByReportId(@PathVariable("reportId") Long reportId)
    {
        return success(tReportImageService.selectTReportImageByReportId(reportId));
    }

    /**
     * 新增图像
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:add')")
    @Log(title = "体检报告图像", businessType = BusinessType.INSERT)
    @PostMapping("/image")
    public AjaxResult addImage(@RequestBody TReportImage tReportImage)
    {
        return toAjax(tReportImageService.insertTReportImage(tReportImage));
    }

    /**
     * 修改图像
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:edit')")
    @Log(title = "体检报告图像", businessType = BusinessType.UPDATE)
    @PutMapping("/image")
    public AjaxResult editImage(@RequestBody TReportImage tReportImage)
    {
        return toAjax(tReportImageService.updateTReportImage(tReportImage));
    }

    /**
     * 删除图像
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:image:remove')")
    @Log(title = "体检报告图像", businessType = BusinessType.DELETE)
    @DeleteMapping("/image/{ids}")
    public AjaxResult removeImage(@PathVariable Long[] ids)
    {
        return toAjax(tReportImageService.deleteTReportImageByIds(ids));
    }

    /**
     * 下载体检报告导入模板
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:import')")
    @GetMapping("/template")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<TReport> util = new ExcelUtil<TReport>(TReport.class);
        util.importTemplateExcel(response, "体检报告数据");
    }

    /**
     * Excel导入体检报告
     * Excel格式：第一行表头，每行一条报告
     * 列顺序：体检人姓名、手机号、身份证号、性别(0男/1女)、年龄、套餐名称、体检日期(yyyy-MM-dd)、报告状态(0待录入/1已审核/2已发布/3已归档)、医生建议
     */
    @PreAuthorize("@ss.hasPermi('reservation:report:import')")
    @Log(title = "体检报告", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet.getLastRowNum() < 1)
        {
            return error("导入数据不能为空");
        }

        // 读取表头确认列映射
        Row headerRow = sheet.getRow(0);
        Map<String, Integer> colMap = new HashMap<>();
        for (int c = 0; c < headerRow.getLastCellNum(); c++)
        {
            String val = getStringValue(headerRow.getCell(c));
            if (StringUtils.isNotEmpty(val))
            {
                colMap.put(val.trim(), c);
            }
        }

        int colName = colMap.getOrDefault("体检人姓名", 0);
        int colPhone = colMap.getOrDefault("手机号", 1);
        int colIdcard = colMap.getOrDefault("身份证号", 2);
        int colSex = colMap.getOrDefault("性别", 3);
        int colAge = colMap.getOrDefault("年龄", 4);
        int colSetmeal = colMap.getOrDefault("套餐名称", 5);
        int colDate = colMap.getOrDefault("体检日期", 6);
        int colStatus = colMap.getOrDefault("报告状态", 7);
        int colAdvice = colMap.getOrDefault("医生建议", 8);

        int successCount = 0;
        int failCount = 0;
        StringBuilder failMsg = new StringBuilder();

        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            try
            {
                TReport report = new TReport();
                report.setMemberName(getStringValue(row.getCell(colName)));
                report.setMemberPhone(getStringValue(row.getCell(colPhone)));
                report.setMemberIdcard(getStringValue(row.getCell(colIdcard)));

                String sexStr = getStringValue(row.getCell(colSex));
                report.setMemberSex(StringUtils.isEmpty(sexStr) ? "2" : sexStr);

                String ageStr = getStringValue(row.getCell(colAge));
                if (StringUtils.isNotEmpty(ageStr))
                {
                    report.setMemberAge(Integer.parseInt(ageStr));
                }

                report.setSetmealName(getStringValue(row.getCell(colSetmeal)));

                String dateStr = getStringValue(row.getCell(colDate));
                if (StringUtils.isNotEmpty(dateStr))
                {
                    report.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
                }

                String statusStr = getStringValue(row.getCell(colStatus));
                report.setReportStatus(StringUtils.isEmpty(statusStr) ? "0" : statusStr);

                report.setDoctorAdvice(getStringValue(row.getCell(colAdvice)));
                report.setCreateBy(SecurityUtils.getUsername());
                report.setCreateTime(new Date());

                // 自动生成报告编号
                report.setReportNo(tReportService.generateReportNo());

                tReportService.insertTReport(report);
                successCount++;
            }
            catch (Exception e)
            {
                failCount++;
                failMsg.append("第").append(i + 1).append("行导入失败：").append(e.getMessage()).append("; ");
            }
        }

        String result = "总共导入" + (successCount + failCount) + "条，成功" + successCount + "条，失败" + failCount + "条";
        if (failCount > 0)
        {
            result += "。失败原因：" + failMsg.toString();
            return error(result);
        }
        return success(result);
    }

    private String getStringValue(org.apache.poi.ss.usermodel.Cell cell)
    {
        if (cell == null) return "";
        switch (cell.getCellType())
        {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell))
                {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }
                // 避免科学计数法
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }
}
