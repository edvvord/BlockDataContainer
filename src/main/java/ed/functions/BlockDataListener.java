package ed.functions;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.List;

public class BlockDataListener implements Listener {

    public BlockDataStorage plugin;

    public BlockDataListener(BlockDataStorage plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        List<Block> blocks = event.getBlocks();

        if(event.isCancelled()){
            return;
        }

        if (!blocks.isEmpty()) {
            BlockFace direction = event.getDirection();

            for (int i = blocks.size() - 1; i >= 0; i--) {
                Block originalBlock = blocks.get(i);
                Block newBlock = originalBlock.getRelative(direction);

                if(DataContainer.hasData(plugin, originalBlock, "custom")){

                    String custom = DataContainer.getData(plugin, originalBlock, "custom");
                    String click = DataContainer.getData(plugin, originalBlock, "click");
                    String hardness = DataContainer.getData(plugin, originalBlock, "hardness");
                    String id = DataContainer.getData(plugin, originalBlock, "id");
                    String instrument = DataContainer.getData(plugin, originalBlock, "instrument");
                    String note = DataContainer.getData(plugin, originalBlock, "note");
                    String stripable = DataContainer.getData(plugin, originalBlock, "stripable");
                    String block_direction = DataContainer.getData(plugin, originalBlock, "direction");

                    DataContainer.removeData(plugin, originalBlock, "custom", true);
                    DataContainer.removeData(plugin, originalBlock, "click", true);
                    DataContainer.removeData(plugin, originalBlock, "hardness", true);
                    DataContainer.removeData(plugin, originalBlock, "id", true);
                    DataContainer.removeData(plugin, originalBlock, "instrument", true);
                    DataContainer.removeData(plugin, originalBlock, "note", true);
                    DataContainer.removeData(plugin, originalBlock, "stripable", true);
                    DataContainer.removeData(plugin, originalBlock, "direction", true);

                    DataContainer.setData(plugin, newBlock, "custom", custom);
                    DataContainer.setData(plugin, newBlock, "click", click);
                    DataContainer.setData(plugin, newBlock, "hardness", hardness);
                    DataContainer.setData(plugin, newBlock, "id", id);
                    DataContainer.setData(plugin, newBlock, "instrument", instrument);
                    DataContainer.setData(plugin, newBlock, "note", note);
                    DataContainer.setData(plugin, newBlock, "stripable", stripable);
                    DataContainer.setData(plugin, newBlock, "direction", block_direction);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (!event.isSticky()) {
            return;
        }

        List<Block> blocks = event.getBlocks();
        if (blocks.isEmpty()) {
            return;
        }

        BlockFace direction = event.getDirection();

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block originalBlock = blocks.get(i);
            Block newBlock = originalBlock.getRelative(direction);

            if(originalBlock.getBlockData() instanceof NoteBlock){
                if (DataContainer.hasData(plugin, originalBlock, "custom")) {

                    String custom = DataContainer.getData(plugin, originalBlock, "custom");
                    String click = DataContainer.getData(plugin, originalBlock, "click");
                    String hardness = DataContainer.getData(plugin, originalBlock, "hardness");
                    String id = DataContainer.getData(plugin, originalBlock, "id");
                    String instrument = DataContainer.getData(plugin, originalBlock, "instrument");
                    String note = DataContainer.getData(plugin, originalBlock, "note");
                    String stripable = DataContainer.getData(plugin, originalBlock, "stripable");
                    String block_direction = DataContainer.getData(plugin, originalBlock, "direction");

                    DataContainer.removeData(plugin, originalBlock, "custom", true);
                    DataContainer.removeData(plugin, originalBlock, "click", true);
                    DataContainer.removeData(plugin, originalBlock, "hardness", true);
                    DataContainer.removeData(plugin, originalBlock, "id", true);
                    DataContainer.removeData(plugin, originalBlock, "instrument", true);
                    DataContainer.removeData(plugin, originalBlock, "note", true);
                    DataContainer.removeData(plugin, originalBlock, "stripable", true);
                    DataContainer.removeData(plugin, originalBlock, "direction", true);

                    DataContainer.setData(plugin, newBlock, "custom", custom);
                    DataContainer.setData(plugin, newBlock, "click", click);
                    DataContainer.setData(plugin, newBlock, "hardness", hardness);
                    DataContainer.setData(plugin, newBlock, "id", id);
                    DataContainer.setData(plugin, newBlock, "instrument", instrument);
                    DataContainer.setData(plugin, newBlock, "note", note);
                    DataContainer.setData(plugin, newBlock, "stripable", stripable);
                    DataContainer.setData(plugin, newBlock, "direction", block_direction);
                }
            }
        }
    }

}
