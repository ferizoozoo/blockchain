package blockchain;

import java.io.File;

public class BlockChainFileUtil {
    private static String blockChainFileName = "Blockchain";
    
    public static BlockChain getOrCreateBlockChain() throws IOException, ClassNotFoundException {
        File blockChainFile = new File(blockChainFileName);
        if (blockChainFile.exists() && blockChainFile.isFile()) {
            return (BlockChain) SerializeUtil.deserialize(blockChainFile);   
        } 
        return new BlockChain();
    }
    
    public static void saveBlockChain(BlockChain blockchain) throws IOException {
        File blockChainFile = new File(blockChainFileName);
        SerializeUtil.serialize(blockchain, blockChainFileName);
    }
}
