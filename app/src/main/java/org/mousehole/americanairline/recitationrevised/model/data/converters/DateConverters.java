package org.mousehole.americanairline.recitationrevised.model.data.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverters {
    @TypeConverter
    public long fromDate(Date date) {
        return date != null ? date.getTime() : new Date().getTime();
    }

    @TypeConverter
    public Date fromTime(long time) {
        return new Date(time);
    }
}
