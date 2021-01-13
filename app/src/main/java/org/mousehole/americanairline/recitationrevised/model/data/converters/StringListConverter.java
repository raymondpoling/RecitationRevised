package org.mousehole.americanairline.recitationrevised.model.data.converters;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringListConverter {
    @TypeConverter
    public String fromStringList(List<String> strings) {

        return strings == null ? "" : TextUtils.join(";" ,strings);
    }

    @TypeConverter
    public List<String> toStringList(String toSplit) {
        Pattern pattern = Pattern.compile(";");
        return toSplit == null ? new ArrayList<>() : Arrays.asList(TextUtils.split(toSplit, pattern));
    }
}
