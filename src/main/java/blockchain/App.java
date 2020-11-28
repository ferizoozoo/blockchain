package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App {
    // Creating an executor for creating many threads and assigning
    // each thread a miner
    public static void main(String[] args) {
        BlockChain blockchain = BlockChainFileUtil.getOrCreateBlockChain();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            executor.execute(new Miner(blockchain));
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
        System.out.println(blockchain.toString());
    }
}
