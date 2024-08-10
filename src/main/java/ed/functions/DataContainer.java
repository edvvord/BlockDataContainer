package ed.functions;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DataContainer {

    static public void removeData(BlockDataStorage plugin, Block block, String metadataName, boolean all){
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Chunk chunk = location.getChunk();
        PersistentDataContainer chunkData = chunk.getPersistentDataContainer();

        if (all) {
            for (NamespacedKey key : chunkData.getKeys()) {
                String keyString = key.getKey();

                if (keyString.startsWith(x + "_" + y + "_" + z + "_")) {
                    chunkData.remove(key);
                }
            }
        } else {
            NamespacedKey key = new NamespacedKey(plugin, x + "_" + y + "_" + z + "_" + metadataName);
            chunkData.remove(key);
        }
    }

    static public <T> void setData(BlockDataStorage plugin, Block block, String metadataName, T metadataValue) {
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Chunk chunk = location.getChunk();

        PersistentDataContainer chunkData = chunk.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, x + "_" + y + "_" + z + "_" + metadataName);
        chunkData.set(key,  PersistentDataType.STRING, String.valueOf(metadataValue));
    }


    public static String getData(BlockDataStorage plugin, Block block, String metadataName) {
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Chunk chunk = location.getChunk();

        PersistentDataContainer chunkData = chunk.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, x + "_" + y + "_" + z + "_" + metadataName);
        return chunkData.get(key, PersistentDataType.STRING);
    }

    static public Boolean hasData(BlockDataStorage plugin, Block block, String metadataName){
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Chunk chunk = location.getChunk();

        PersistentDataContainer chunkData = chunk.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, x + "_" + y + "_" + z + "_" + metadataName);
        return chunkData.has(key, PersistentDataType.STRING);
    }
}
