package topi.tablist;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SetTab {

    public static Map<UUID, String> tabList = new HashMap<>();

    public static void SetTabListName(Player player, String string) {
        player.setPlayerListName(string);

        tabList.put(player.getUniqueId(), string);
    }
}
