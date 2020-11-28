package blockchain;

import java.io.File;
import java.io.IOException;

public class BlockChainFileUtil {
    private static String blockChainFileName = "Blockchain";

    public static BlockChain getOrCreateBlockChain() {
        BlockChain blockChain = new BlockChain();
        try {
            File blockChainFile = new File(blockChainFileName);
            if (blockChainFile.exists() && blockChainFile.isFile()) {
                blockChain = (BlockChain) SerializeUtil.deserialize(blockChainFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return blockChain;
    }

    public static void saveBlockChain(BlockChain blockchain) {
        try {
            SerializeUtil.serialize(blockchain, blockChainFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
