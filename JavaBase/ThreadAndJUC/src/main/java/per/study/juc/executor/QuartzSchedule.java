package per.study.juc.executor;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedule
{
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = newJob(QuartzSimpleJob.class).withIdentity("Job1", "Group1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/5 * * * * ?")).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
