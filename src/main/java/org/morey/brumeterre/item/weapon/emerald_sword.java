package org.morey.brumeterre.item.weapon;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.item.ItemBuilder;
import org.morey.brumeterre.item.ItemManager;

import java.util.ArrayList;
import java.util.List;

public class emerald_sword {

    public static int price = 1500;
    public static String rarity = ItemManager.mythic;
    public static ItemStack customItem()
    {

        List<String> lore = new ArrayList<>();
        lore.add(rarity);
        lore.add(" ");
        lore.add("§e§lPASSIF §7- Permet à Morey de faire des test");
        lore.add(" ");
        lore.add("§7Prix: §e" + price);

        ItemBuilder.ItemConfig config = new ItemBuilder.ItemConfig(Material.DIAMOND_SWORD, "§aÉpée en émeraude")
                .setLore(lore)
                .setAttackDamage(18)
                .setCustomModelData(23)
                .setPersistentValue("emerald_sword");
        return ItemBuilder.createItem(config);
    }
}
