package per.study.juc.executor;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedule
{
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(QuartzSimpleJob.class).withIdentity("Job1", "Group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
