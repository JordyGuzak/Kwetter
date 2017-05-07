package bean;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by jordy on 03/28/2017.
 */
@Named
@SessionScoped
public class BatchBean implements Serializable {
    private long execID;
    private JobOperator jobOperator;
    private static final long serialVersionUID = 3712686178567411830L;

    public String startBatchJob() {
        jobOperator = BatchRuntime.getJobOperator();
        execID = jobOperator.start("userimport", null);
        return "batch";
    }

    public BatchStatus getJobStatus() {
        return jobOperator.getJobExecution(execID).getBatchStatus();
    }

    public String getJobStatusString() {
        if (jobOperator == null) {
            return "";
        } else {
            return jobOperator.getJobExecution(execID).getBatchStatus().toString();
        }
    }

    public boolean isCompleted() {
        return getJobStatusString().equals(BatchStatus.COMPLETED.toString());
    }
}
