package calling_program;

import java.io.IOException;
import java.util.Set;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class calling implements Job {

    public boolean  exec(String app_path){
        try{
            Runtime.getRuntime().exec(app_path);
            System.out.println("execute app succeed. command is: " + app_path);
            return  true;
        }catch (IOException e){
            System.out.println("execute app failed. error is: " + e.getMessage());
            return  false;
        }
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        if(context.getMergedJobDataMap().size() > 0) {
            String val = "";
            String exeCommand = "";
            Set<String> keys = context.getMergedJobDataMap().keySet();
            for(String key: keys) {
                val = context.getMergedJobDataMap().getString(key);
                if(key.equals("filename") && exeCommand.length() == 0){
                    exeCommand = exeCommand + " " + "\"" + val + "\"";
                }
                else if(key.equals("filename") && exeCommand.length() > 0){
                    exeCommand = "\"" + val + "\"" + " " + exeCommand;
                }
                if(key.equals("parameter")){
                    exeCommand = exeCommand + "\"" + val + "\"";
                }
                System.out.println("execute command is: " + exeCommand);
            }
            exec(exeCommand);
        }
    }
}
