package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    //@Autowired
    TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

//    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
////        this.timeEntryRepository = timeEntryRepository;
//        this(timeEntryRepository, null);
//    }

    public TimeEntryController(
            TimeEntryRepository timeEntriesRepo,
            MeterRegistry meterRegistry
    ) {
        this.timeEntryRepository = timeEntriesRepo;

        if(meterRegistry != null) {
            timeEntrySummary = meterRegistry.summary("timeEntry.summary");
            actionCounter = meterRegistry.counter("timeEntry.actionCounter");
        }else{
            timeEntrySummary = null;
            actionCounter = null;
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry outTimeEntry = timeEntryRepository.create(timeEntryToCreate);

        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        return new ResponseEntity(outTimeEntry,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        TimeEntry outTimeEntry = timeEntryRepository.find(id);
        if(outTimeEntry == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            actionCounter.increment();
            return new ResponseEntity(outTimeEntry, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        actionCounter.increment();
        return new ResponseEntity(timeEntryList, HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {

        TimeEntry outTimeEntry = timeEntryRepository.update(timeEntryId,expected);
        if(outTimeEntry == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            actionCounter.increment();
            return new ResponseEntity(outTimeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id)
    {
        timeEntryRepository.delete(id);
        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
