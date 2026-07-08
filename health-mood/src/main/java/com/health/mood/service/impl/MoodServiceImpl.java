package com.health.mood.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.mood.domain.MoodRecord;
import com.health.mood.mapper.MoodMapper;
import com.health.mood.service.IMoodService;

@Service
public class MoodServiceImpl implements IMoodService
{
    @Autowired
    private MoodMapper moodMapper;

    @Override
    public List<MoodRecord> selectMoodRecordList(MoodRecord moodRecord)
    {
        return moodMapper.selectMoodRecordList(moodRecord);
    }

    @Override
    public MoodRecord selectMoodRecordById(Long id)
    {
        return moodMapper.selectMoodRecordById(id);
    }

    @Override
    public int insertMoodRecord(MoodRecord moodRecord)
    {
        return moodMapper.insertMoodRecord(moodRecord);
    }

    @Override
    public int updateMoodRecord(MoodRecord moodRecord)
    {
        return moodMapper.updateMoodRecord(moodRecord);
    }

    @Override
    public int deleteMoodRecordById(Long id)
    {
        return moodMapper.deleteMoodRecordById(id);
    }

    @Override
    public int deleteMoodRecordByIds(Long[] ids)
    {
        return moodMapper.deleteMoodRecordByIds(ids);
    }
}
