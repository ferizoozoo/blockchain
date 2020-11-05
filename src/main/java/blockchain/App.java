package blockchain;

public class App {
    public static void main(String[] args) {
        BlockChain blockchain = new BlockChain();
        for (int counter = 0; counter < 5; counter++) {
            blockchain.generateBlock();
        }
        
        for (var block : blockchain.blocks) {
            System.out.println(block.toString() + "\n");
        }
    }
}
