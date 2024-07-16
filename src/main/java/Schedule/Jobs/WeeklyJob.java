package Schedule.Jobs;

import Schedule.TaskUtils;
import Tasks.ApprovedKV;
import logging.LoggerWrapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;

/**
 * The WeeklyJob class represents a job that is executed weekly.
 * It reads and writes key-value data and logs the execution time.
 */
public class WeeklyJob implements Job
{
    private static final LoggerWrapper logger = new LoggerWrapper(WeeklyJob.class);

    /**
     * Executes the weekly job, which involves reading and writing key-value data.
     *
     * @param context the job execution context
     */
    @Override
    public void execute(JobExecutionContext context)
    {
        ApprovedKV approvedKV = new ApprovedKV();

        try
        {
            approvedKV.readAndWrite();
            logger.info("Weekly task executed successfully at " + TaskUtils.currentDateTime());
        }
        catch (IOException e)
        {
            logger.error("Failed to execute weekly task at " + TaskUtils.currentDateTime() + "\n" + e.getMessage());
            throw new RuntimeException("Error during weekly job execution", e);
        }
    }
}