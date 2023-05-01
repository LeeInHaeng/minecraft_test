package eventhandlertest.eventhandlertest;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class EventHandlerTest extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("구독").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (false == sender instanceof Player) {
            sender.sendMessage("This command only player");
            return false;
        }

        Player player = (Player) sender;
        Item item = new Item();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("§f이분은... 구독자?");
        arrayList.add("§c감사합니다.");

        if (0 == args.length) { // 구독
            player.sendMessage("좋아요도 누르나요?");
        } else if (true == args[0].equals("좋아요")) {
            player.sendMessage("땡큐");
            player.getInventory().addItem(item.SetSlot(Material.LEGACY_REDSTONE_TORCH_OFF, "§a§l땡큐", arrayList));
        }

        return true;
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
