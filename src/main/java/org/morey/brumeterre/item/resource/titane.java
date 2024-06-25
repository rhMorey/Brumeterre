package org.morey.brumeterre.item.resource;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class titane {

    public static int price = 5000;
    public static ItemStack customItem()
    {

        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§e§lPASSIF §7- casse les couilles à morey");
        lore.add(" ");
        lore.add("§7Prix: §e" + price);


        ItemBuilder.ItemConfig config = new ItemBuilder.ItemConfig(Material.NETHERITE_INGOT, "§8Titane")
                .setLore(lore)
                .setCustomModelData(2)
                .setPersistentValue("titane");
        return ItemBuilder.createItem(config);
    }
}
