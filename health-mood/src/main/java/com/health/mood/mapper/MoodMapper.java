package com.health.mood.mapper;

import java.util.List;
import com.health.mood.domain.MoodRecord;

public interface MoodMapper
{
    public List<MoodRecord> selectMoodRecordList(MoodRecord moodRecord);
    public MoodRecord selectMoodRecordById(Long id);
    public int insertMoodRecord(MoodRecord moodRecord);
    public int updateMoodRecord(MoodRecord moodRecord);
    public int deleteMoodRecordById(Long id);
    public int deleteMoodRecordByIds(Long[] ids);
}
