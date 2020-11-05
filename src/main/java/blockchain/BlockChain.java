package blockchain;

import java.util.ArrayList;
import java.util.Date;

public class BlockChain {
    
    public ArrayList<Block> blocks = new ArrayList<Block>();
    
    public boolean generateBlock() {
        Block block = new Block();
        block.id = this.blocks.size() + 1;
        block.timestamp = new Date().getTime();
        block.hashOfPreviousBlock = block.id == 1 ? "0" : this.blocks.get((int)block.id - 2).hashOfThisBlock;
        block.hashOfThisBlock = StringUtil.applySha256(block.toStringToBeHashed());
        blocks.add(block);
        return this.validateBlockChain();
    }
    
    public boolean validateBlockChain() {
        for (int index = 0; index < this.blocks.size() - 1; index++) {
            if (this.blocks.get(index).hashOfThisBlock == this.blocks.get(index + 1).hashOfPreviousBlock) {
                return false;
            }
        }
        return true;
    }
}
