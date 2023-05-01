package tpplugin.tpplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;

public final class TpPlugin extends JavaPlugin implements Listener,CommandExecutor {

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Location> pos = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("텔포").setExecutor(this);

        getLogger().info("TpPlugin onEnable");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("/텔포 유저등록");
            player.sendMessage("/텔포 시작");
            if (player.isOp()) {
                player.sendMessage("/텔포 지역생성");
            }
            return false;
        }

        if (args[0].equals("유저등록")){
            for (Player value : players) {
                if (player.getPlayer().getName().equals(value.getName())) {
                    player.sendMessage("이미 등록된 플레이어 입니다. (" + player.getPlayer().getName() + ")");
                    return false;
                }
            }

            players.add(player);
            player.sendMessage(player.getPlayer().getName() + "플레이어가 등록 되었습니다.");
            return true;
        }

        if (!player.isOp() && args[0].equals("지역생성")) {
            player.sendMessage("/텔포 유저등록");
            return false;
        }

        if (args[0].equals("지역생성")) {
            pos.add(player.getPlayer().getLocation());
            player.sendMessage("지역 생성이 완료 되었습니다.");
            return true;
        }
        
        if (args[0].equals("시작") && pos.size() < players.size()) {
            player.sendMessage("지역생성이 더 많이 필요 합니다.");
            return false;
        }

        if (args[0].equals("시작")) {
            Random random = new Random();
            int index = random.nextInt(pos.size());
            for (Player p : players) {
                p.sendMessage(p.getName()+"님 텔포 시작되어 자동 텔포 되었습니다.");
                p.teleport(pos.get(index));
            }
        }

        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info("TpPlugin onDisable");
    }
}
