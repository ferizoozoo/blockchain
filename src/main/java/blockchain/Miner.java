package blockchain;

public class Miner implements Runnable{
    private static long lastMiner = 1;

    private BlockChain blockchain;
    private long id;

    public Miner(BlockChain blockchain) {
        this.blockchain = blockchain;
        this.id = lastMiner++;
    }

    public void run() {
        Block newBlock = new Block(
                this.blockchain.getNumberOfZeros(),
                this.id,
                this.blockchain.getLastBlockOrNull()
        );
        this.blockchain.addBlock(newBlock);
    }
}
