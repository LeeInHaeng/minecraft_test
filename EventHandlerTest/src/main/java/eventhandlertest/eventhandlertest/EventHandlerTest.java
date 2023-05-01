package eventhandlertest.eventhandlertest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EventHandlerTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void CustomPlayerChatEvent(PlayerChatEvent chatEvent) {
        Player player = chatEvent.getPlayer();
        player.sendMessage("이름이 ? : " + player.getName());
        if (true == chatEvent.getMessage().equals("구독")) {
            player.sendMessage("좋아요");
        }
    }

    @EventHandler
    public void CustomPlayerJoinEvent(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        player.sendMessage("입장함");
    }

    @EventHandler
    public void CustomPlayerMoveEvent(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        player.sendMessage("너 움직임");
    }

    @EventHandler
    public void CustomBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("너 " + player.getName()+"가 "+event.getBlock().getType()+" 블럭을 부셨네");
    }

    @EventHandler
    public void CustomBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("너 " + player.getName()+"가 "+event.getBlock().getType()+" 블럭을 놓았네");
        if (event.getBlock().getType() == Material.STONE) {
            player.sendMessage("너 " + player.getName()+"가 돌을 놨냐");
        }
    }
}
