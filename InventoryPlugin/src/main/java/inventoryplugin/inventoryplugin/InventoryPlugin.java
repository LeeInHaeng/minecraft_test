package inventoryplugin.inventoryplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryPlugin extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("inventory plugin onEnable");
        getCommand("상자").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("inventory plugin onDisable");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player)sender;
        Inventory inventory = Bukkit.createInventory(null, 27, "§c§l메뉴");
        Item item = new Item();

        try
        {
            if (false == sender instanceof Player) {
                sender.sendMessage("only player command");
                return false;
            }

            if (0 == args.length) {
                player.sendMessage("/상자 열기");
                return false;
            }

            if (1 == args.length && true == args[0].equals("열기")) {
                inventory.setItem(10, item.SetSlot(Material.PUMPKIN, "§d메뉴1"));
                inventory.setItem(13, item.SetSlot(Material.STONE, "§d메뉴2"));
                inventory.setItem(16, item.SetSlot(Material.LEGACY_RED_ROSE, "§d메뉴3"));
                player.openInventory(inventory);
            }
        }
        catch (Exception exception)
        {
            getLogger().warning("Inventory Plugin onCommand Exception : " + exception);
            return false;
        }

        return true;
    }

    @EventHandler
    public void CustomInventoryClickEvent(InventoryClickEvent event) {

        Player player;

        try
        {
            if (false == event.getWhoClicked() instanceof Player) {
                getLogger().warning("Inventory Plugin CustomInventoryClickEvent whocliked not player");
                return;
            }

            player = (Player) event.getWhoClicked();
            if (true == event.getView().getTitle().equals("§c§l메뉴")) {
                event.setCancelled(true);
                if (10 == event.getRawSlot()) {
                    player.sendMessage("메뉴1 이다");
                }
                else if (13 == event.getRawSlot()) {
                    player.sendMessage("메뉴2 이다");
                }
                else if (16 == event.getRawSlot()) {
                    player.sendMessage("메뉴3 이다");
                }
            }
        }
        catch (Exception exception)
        {
            getLogger().warning("Inventory Plugin CustomInventoryClickEvent Exception : " + exception);
        }
    }

    @EventHandler
    public void CustomBlockPlaceEvent(BlockPlaceEvent event) {

        Player player = event.getPlayer();
        Item item = new Item();

        try
        {
            if (Material.CHEST == event.getBlock().getType()) {
                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                event.setCancelled(true);
                Inventory inventory = Bukkit.createInventory(null, 27, "§c§l보급품");
                inventory.setItem(10, item.SetSlot(Material.DIAMOND_SWORD, "§d다야 검"));
                inventory.setItem(13, item.SetSlot(Material.BOW, "§b활"));
                inventory.setItem(16, item.SetSlot(Material.ARROW, "§e화살"));
                player.openInventory(inventory);
            }

        }
        catch (Exception exception)
        {
            getLogger().warning("Inventory Plugin CustomBlockPlaceEvent Exception : " + exception);
        }

    }
}
