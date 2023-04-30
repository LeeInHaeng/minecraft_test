package topi.tablist;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (0 == strings.length) {
                player.sendMessage("Enter /name <nickname>");
                return false;
            }

            SetTab.SetTabListName(player, strings[0]);
            player.sendMessage("You changed your TabListName to " + strings[0]);

        } else {
            commandSender.sendMessage("This command is only allowed to the players.");
        }

        return false;
    }
}
