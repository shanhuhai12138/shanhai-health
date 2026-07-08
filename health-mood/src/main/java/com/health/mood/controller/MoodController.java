package com.health.mood.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.mood.domain.MoodRecord;
import com.health.mood.service.IMoodService;
import com.health.common.core.page.TableDataInfo;

@RestController
@RequestMapping("/mood")
public class MoodController extends BaseController
{
    @Autowired
    private IMoodService moodService;

    @GetMapping("/list")
    public TableDataInfo list(MoodRecord moodRecord)
    {
        startPage();
        List<MoodRecord> list = moodService.selectMoodRecordList(moodRecord);
        return getDataTable(list);
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(moodService.selectMoodRecordById(id));
    }

    @Log(title = "情绪记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MoodRecord moodRecord)
    {
        moodRecord.setUserId(getUserId());
        return toAjax(moodService.insertMoodRecord(moodRecord));
    }

    @Log(title = "情绪记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MoodRecord moodRecord)
    {
        return toAjax(moodService.updateMoodRecord(moodRecord));
    }

    @Log(title = "情绪记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(moodService.deleteMoodRecordByIds(ids));
    }
}
