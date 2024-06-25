package org.morey.brumeterre.item.resource;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class crystalium {

    public static int price = 3000;
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
                .setPersistentValue("crystalium");
        return ItemBuilder.createItem(config);
    }

}
