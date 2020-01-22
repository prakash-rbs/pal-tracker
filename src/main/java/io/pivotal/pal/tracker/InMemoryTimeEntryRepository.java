package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private List<TimeEntry> timeEntryRepoList = new ArrayList<>();
    private static long timeEntryIdColmn = 0L;

    public InMemoryTimeEntryRepository(){
        timeEntryIdColmn = 0L;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        if(timeEntry.getId() <= 0)
        {
            timeEntryIdColmn++;
            timeEntry.setId(timeEntryIdColmn);
        }
        if(find(timeEntry.getId()) != null)
        {
            update(timeEntryIdColmn,timeEntry);
        }else
        {
            this.timeEntryRepoList.add(timeEntry);
        }
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        for (TimeEntry timeEntry : timeEntryRepoList) {
            if (timeEntry.getId() == timeEntryId) {
                return timeEntry;
            }
        }
        return null;
    }

    @Override
    public List<TimeEntry> list(){
        return timeEntryRepoList;
    }

    @Override
    public TimeEntry update(long timeEntryID, TimeEntry timeEntry){
        for(int i=0; i<timeEntryRepoList.size(); i++){
            if(timeEntryRepoList.get(i).getId() == timeEntryID){
                timeEntryRepoList.get(i).setDate(timeEntry.getDate());
                timeEntryRepoList.get(i).setProjectId(timeEntry.getProjectId());
                timeEntryRepoList.get(i).setUserId(timeEntry.getUserId());
                timeEntryRepoList.get(i).setHours(timeEntry.getHours());
                return timeEntryRepoList.get(i);
            }
        }
        return null;
        }

    @Override
    public void delete(long timeEntryId) {
        for(int i=0; i<timeEntryRepoList.size(); i++){
            if(timeEntryRepoList.get(i).getId() == timeEntryId){
                timeEntryRepoList.remove(i);
                break;
            }
        }
    }



}
