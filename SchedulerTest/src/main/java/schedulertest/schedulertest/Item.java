package schedulertest.schedulertest;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Item {

    public static void PlaySound(Player player, Sound sound, float volume, float pitch) {
        if (false == player.isOnline()) {
            return;
        }

        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static ItemStack SetSlot(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack SetSlot(Material material, String name, ArrayList<String> lore) {
        ArrayList<String> array = new ArrayList<>();
        for (String s : lore) {
            array.add(s);
        }
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(array);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack SetSlot(ItemStack itemStack, String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack SetSlot(ItemStack itemStack, String name, ArrayList<String> lore) {
        ArrayList<String> array = new ArrayList<>();
        for (String s : lore) {
            array.add(s);
        }
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(array);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
