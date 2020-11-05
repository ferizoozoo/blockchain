package blockchain;

public class Block {
     
    public long id;
    public long timestamp;
    public String hashOfPreviousBlock;
    public String hashOfThisBlock;
    
    @Override
    public String toString() {
        return this.toStringToBeHashed()  + "\nHash of the block:\n" + this.hashOfThisBlock;
    }
    
    public String toStringToBeHashed() {
        return String.format(
            "Block:\nId: " + this.id + "\n" +
            "Timestamp: " + this.timestamp + "\n" +
            "Hash of the previous block:\n" + this.hashOfPreviousBlock
        );
    }
}
