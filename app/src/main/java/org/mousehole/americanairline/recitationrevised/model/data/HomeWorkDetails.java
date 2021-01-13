package org.mousehole.americanairline.recitationrevised.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.mousehole.americanairline.recitationrevised.model.data.converters.DateConverters;
import org.mousehole.americanairline.recitationrevised.model.data.converters.StringListConverter;

import java.util.Date;
import java.util.List;

@Entity(indices = @Index(value = {"week", "day"}, name = "week_day_index"), tableName = "homework")
public class HomeWorkDetails {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "homework_id")
    private long id;

    @ColumnInfo(name = "week")
    private int week;

    @ColumnInfo(name = "day")
    private int day;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "concepts")
    @TypeConverters(value = {StringListConverter.class})
    private List<String> concepts;

    @ColumnInfo(name = "git_repository")
    private String gitRepository;

    @ColumnInfo(name = "due_date")
    @TypeConverters(value = {DateConverters.class})
    private Date dueDate;

    public HomeWorkDetails(long id, int week, int day, String subject, String description, List<String> concepts, String gitRepository, Date dueDate) {
        this.id = id;
        this.week = week;
        this.day = day;
        this.subject = subject;
        this.description = description;
        this.concepts = concepts;
        this.gitRepository = gitRepository;
        this.dueDate = dueDate;
    }

    @Ignore
    public HomeWorkDetails(int week, int day, String subject, String description, List<String> concepts, String gitRepository, Date dueDate) {
        this.week = week;
        this.day = day;
        this.subject = subject;
        this.description = description;
        this.concepts = concepts;
        this.gitRepository = gitRepository;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<String> concepts) {
        this.concepts = concepts;
    }

    public String getGitRepository() {
        return gitRepository;
    }

    public void setGitRepository(String gitRepository) {
        this.gitRepository = gitRepository;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Subject: ").append(subject).append('\n');
        sb.append("Description: ").append(description).append('\n');
        sb.append("Due Date: ").append(dueDate).append('\n');
        return sb.toString();
    }
}
