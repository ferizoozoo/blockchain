package blockchain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Block implements Serializable {
    private static final long serialVersionUID = 1534253L;

    private long id;
    private long minerId;
    private long timestamp;
    private long magicNumber;
    private String hashOfPreviousBlock;
    private String hashOfThisBlock;
    private long timeSpentCreating;
    private transient String NChangeMessage;

    public Block(int numberOfZeros, long minerId, Block previousBlock) {
        this.timestamp = new Date().getTime();
        this.id = previousBlock == null ? 1 : previousBlock.getId() + 1;
        this.minerId = minerId;
        this.magicNumber = 0L;
        this.hashOfPreviousBlock = previousBlock == null ? "0" : previousBlock.getHashOfThisBlock();
        this.hashOfThisBlock = proofOfWork(numberOfZeros);
        this.timeSpentCreating = new Date().getTime() - this.timestamp;
    }

    public String proofOfWork(int numberOfZeros) {
        String hashedBlock = StringUtil.applySha256(this.toStringToBeHashed());
        Random rand = new Random();
        while (!hashedBlock.startsWith("0".repeat(numberOfZeros)) && numberOfZeros != 0) {
            this.magicNumber = rand.nextInt(1000000000);
            hashedBlock = StringUtil.applySha256(this.toStringToBeHashed());
        }
        return hashedBlock;
    }

    public long getId() {
        return this.id;
    }

    public long getMinerId() { return this.minerId; }

    public long getTimestamp() {
        return this.timestamp;
    }

    public long getMagicNumber() {
        return this.magicNumber;
    }

    public String getHashOfPreviousBlock() {
        return this.hashOfPreviousBlock;
    }

    public String getHashOfThisBlock() {
        return this.hashOfThisBlock;
    }

    public long getTimeSpentCreating() { return this.timeSpentCreating; }

    public String getNChangeMessage() { return this.NChangeMessage; }

    public void setNChangeMessage(String message) { this.NChangeMessage = message; }

    // Return the string of the block data to be hashed
    public String toStringToBeHashed() {
        return String.format(
                this.getId() + this.getMagicNumber() + this.getTimestamp() + this.getHashOfPreviousBlock() + this.getHashOfThisBlock()
        );
    }

    // Return a string representation of the block to be printed
    @Override
    public String toString() {
        return String.format(
                "Block:\n" +
                        "Created by miner # " + this.getMinerId() + "\n" +
                        "Id: " + this.getId() + "\n" +
                        "Timestamp: " + this.getTimestamp() + "\n" +
                        "Magic number: " + this.getMagicNumber() + "\n" +
                        "Hash of the previous block:\n" + this.getHashOfPreviousBlock() + "\n" +
                        "Hash of the block:\n" + this.getHashOfThisBlock() + "\n" +
                        "Block was generating for " + this.getTimeSpentCreating() + " seconds\n" +
                        this.getNChangeMessage()
        );
    }
}
