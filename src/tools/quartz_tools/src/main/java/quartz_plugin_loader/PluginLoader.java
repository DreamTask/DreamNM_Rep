package quartz_plugin_loader;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

import java.util.logging.Logger;

/**
* This example will spawn a large number of jobs to run
*
* @author James House, Bill Kratzer
*/
public class PluginLoader {

    public void run() throws Exception {
        Logger log = Logger.getLogger("quartz.PluginLoader");
         // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        log.info("------- Initialization Complete -----------");

        log.info("------- Starting Scheduler ----------------");
        // start the schedule
        sched.start();
        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting five minutes... -----------");
        // wait five minutes to give our jobs a chance to run
        try {
            Thread.sleep(300L * 1000L);
        } catch (Exception e) {
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws Exception {

        PluginLoader loader = new PluginLoader();
        loader.run();
    }

}