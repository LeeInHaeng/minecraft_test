package animal.animal;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Animal extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Animal onEnable");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Animal onDisable");
    }

    @EventHandler
    public void AnimalHit(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Item item = new Item();
        Random re = new Random();

        if (event.getCaught().getType() == EntityType.COW) {
            int random = re.nextInt(5)+1; // 1~5
            if (1 == random) {
                player.sendMessage("당첨!");
                player.getInventory().addItem(item.SetSlot(Material.PAPER, "§f당첨종이"));
            }
            else {
                player.sendMessage("꽝!");
            }
            event.getCaught().remove();
        }
        else if (event.getCaught().getType() == EntityType.CHICKEN) {
            player.sendMessage("소환술 !");
            event.getCaught().teleport(player.getLocation());
        }
        else if (event.getCaught().getType() == EntityType.SHEEP) {
            event.getCaught().setCustomName(player.getName()+" 님의 양");
            event.getCaught().setCustomNameVisible(true);
        }
    }
}
