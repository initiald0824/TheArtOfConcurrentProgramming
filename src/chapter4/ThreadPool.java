package chapter4;

/**
 * @author initiald0824
 * @date 2019/7/10 12:37
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个job，这个job需要实现Runnable
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     * @param num
     */
    void removeWorker(int num);

    /**
     * 得到正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}
