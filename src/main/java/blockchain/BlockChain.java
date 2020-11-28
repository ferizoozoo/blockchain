package blockchain;

import java.io.Serializable;
import java.util.LinkedList;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = 2164851L;
    private static final int thresholdTime = 60; // Threshold time in seconds for regulating the number of zeros

    private LinkedList<Block> chain;
    private transient int numberOfZeros; // Number of zeros in front of the hash of each block

    public BlockChain() {
        this.chain = new LinkedList<Block>();
        this.numberOfZeros = 0;
    }

    public int getNumberOfZeros() {
        return this.numberOfZeros;
    }

    // Regulate the number of zeros based on the time spent creating a block
    private String regulateNumberOfZeros(long time) {
        String message = "";
        if (time > thresholdTime && this.numberOfZeros > 0) {
            this.numberOfZeros--;
            message += "N was decreased by 1";
        } else if (time < thresholdTime) {
            this.numberOfZeros++;
            message += "N was increased to " + this.getNumberOfZeros();
        } else {
            message += "N stays the same";
        }
        return message;
    }

    public LinkedList<Block> getChain() {
        return this.chain;
    }

    // If the chain is empty, get the last block, else, return null
    public Block getLastBlockOrNull() {
        Block lastBlock;
        if (this.chain.isEmpty()) {
            lastBlock = null;
        } else {
            lastBlock = this.getChain().getLast();
        }
        return lastBlock;
    }

    // Add block to the chain, and if the block is
    // valid, save the blockchain to the blockchain file and regulate
    // the number of zeros
    public void addBlock(Block newBlock) {
        if (this.validateBlock(newBlock)) {
            String message = this.regulateNumberOfZeros(newBlock.getTimeSpentCreating());
            newBlock.setNChangeMessage(message);
            this.chain.add(newBlock);
            this.saveBlockChain();
        }
    }

    public int numberOfBlocks() {
        return this.chain.size();
    }

    // Check if the new block, the hash of the previous block and
    // the hash of the current block are the same
    public boolean validateBlock(Block newBlock) {
        boolean isValid = false;
        Block previousBlock = this.getLastBlockOrNull();
        if (previousBlock == null) {
            isValid = true;
        } else if (previousBlock.getHashOfThisBlock().equals(newBlock.getHashOfPreviousBlock())) {
            isValid = true;
        }
        return isValid;
    }

    public void saveBlockChain() {
        BlockChainFileUtil.saveBlockChain(this);
    }

    // String representation of the blockchain
    @Override
    public String toString() {
        String blockchainRepresentation = "";
        for (var block : this.chain) {
            // Get the string representation of each block
            blockchainRepresentation += block.toString();
            blockchainRepresentation += "\n\n";
        }
        return blockchainRepresentation;
    }
}
