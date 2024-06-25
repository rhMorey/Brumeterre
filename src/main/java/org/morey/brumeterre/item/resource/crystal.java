package org.morey.brumeterre.item.resource;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class crystal {

    public static int price = 2000;
    public static ItemStack customItem()
    {

        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§e§lACTIVABLE §7- test");
        lore.add(" ");
        lore.add("§7Prix: §e" + price);


        ItemBuilder.ItemConfig config = new ItemBuilder.ItemConfig(Material.EMERALD, "§rCrystalium")
                .setLore(lore)
                .setAttackDamage(4.2)
                .setCustomModelData(3);
        return ItemBuilder.createItem(config);
    }
}
