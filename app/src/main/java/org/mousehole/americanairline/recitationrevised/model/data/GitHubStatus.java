package org.mousehole.americanairline.recitationrevised.model.data;

import java.util.Date;

public class GitHubStatus {
    private Date lastUpdate;
    private Date dueDate;

    public GitHubStatus(Date lastUpdate, Date dueDate) {
        this.lastUpdate = lastUpdate;
        this.dueDate = dueDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
