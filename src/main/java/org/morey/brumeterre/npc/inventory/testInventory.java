package org.morey.brumeterre.npc.inventory;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.item.ItemBuilder;
import org.morey.brumeterre.item.resource.crystal;
import org.morey.brumeterre.item.resource.crystalium;
import org.morey.brumeterre.item.resource.titane;
import org.morey.brumeterre.item.weapon.emerald_sword;
import static org.morey.brumeterre.capture.economy.economyTimer.purchaseItem;

import java.util.ArrayList;

public class testInventory implements Listener {

    ItemBuilder.ItemConfig glass = new ItemBuilder.ItemConfig(Material.GRAY_STAINED_GLASS_PANE, " ");
    ItemStack item1 = emerald_sword.customItem();
    ItemStack item2 = crystalium.customItem();
    ItemStack item3 = crystal.customItem();
    ItemStack item4 = titane.customItem();
    public String npcName = "Test";
    @EventHandler
    public void interactNPC(NPCRightClickEvent event) {
        Player player = event.getClicker();
        if (event.getNPC().getName().equals(npcName)) {
            player.openInventory(inventory(player));
        }
    }

    public Inventory inventory(Player player)
    {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§7Sélectionnez dans ce menu les objets que vous souhaitez acheter.");
        ItemBuilder.ItemConfig menu = new ItemBuilder.ItemConfig(Material.PAPER, "§e§lTest inventory").setLore(lore);

        Inventory inv = Bukkit.createInventory(player, 54, npcName);

        // DESIGN
        inv.setItem(0, ItemBuilder.createItem(glass));
        inv.setItem(1, ItemBuilder.createItem(glass));
        inv.setItem(2, ItemBuilder.createItem(glass));
        inv.setItem(3, ItemBuilder.createItem(glass));
        inv.setItem(4, ItemBuilder.createItem(menu));
        inv.setItem(5, ItemBuilder.createItem(glass));
        inv.setItem(6, ItemBuilder.createItem(glass));
        inv.setItem(7, ItemBuilder.createItem(glass));
        inv.setItem(8, ItemBuilder.createItem(glass));
        // DESIGN

        // ITEM A VENDRE
        inv.setItem(19, item1);
        inv.setItem(20, item2);
        inv.setItem(21, item3);
        inv.setItem(22, item4);

        return inv;
    }

    @EventHandler
    public void ifClicking(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(npcName))
        {
            if(event.getCurrentItem() == null) return;
            if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) event.setCancelled(true);
            if(event.getCurrentItem().equals(item1))
            {
                purchaseItem(event, item1, player.getUniqueId(), emerald_sword.price);
            }
            if(event.getCurrentItem().equals(item2))
            {
                purchaseItem(event, item2, player.getUniqueId(), crystalium.price);
            }
            if(event.getCurrentItem().equals(item3))
            {
                purchaseItem(event, item3, player.getUniqueId(), crystal.price);
            }
            if(event.getCurrentItem().equals(item4))
            {
                purchaseItem(event, item4, player.getUniqueId(), titane.price);
            }
        }
    }
}
