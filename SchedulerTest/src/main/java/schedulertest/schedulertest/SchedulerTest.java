package schedulertest.schedulertest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;

public final class SchedulerTest extends JavaPlugin implements Listener, CommandExecutor {

    boolean check;
    boolean singletonCheck = true;

    Inventory inventory = Bukkit.createInventory(null, 27, "시계");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("스케줄러").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Item item = new Item();

        inventory.setItem(0, item.SetSlot(Material.DIAMOND, "시작"));
        inventory.setItem(1, item.SetSlot(Material.GOLD_BLOCK, "종료"));
        inventory.setItem(13, item.SetSlot(Material.CLOCK, "" + Calendar.getInstance().get(Calendar.HOUR)+"시"+Calendar.getInstance().get(Calendar.MINUTE)+"분"));
        player.openInventory(inventory);

        return true;
    }

    @EventHandler
    public void CustomInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (true == event.getView().getTitle().equals("시계")) {
            event.setCancelled(true);
            if (0 == event.getRawSlot()) {
                if (true == singletonCheck) {
                    singletonCheck = false;
                    check = true;
                    ClockTaskTimer(player);
                }
            } else if (1 == event.getRawSlot()) {
                check = false;
            }
        }
    }

    public void ClockTaskTimer(Player player) {
        Item item = new Item();
        new BukkitRunnable() {
            public void run() {
                if (false == check) {
                    player.sendMessage("멈췄어");
                    cancel();
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 1);
                inventory.setItem(13, item.SetSlot(Material.CLOCK, "" + Calendar.getInstance().get(Calendar.HOUR)+"시"+Calendar.getInstance().get(Calendar.MINUTE)+"분"));
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}
