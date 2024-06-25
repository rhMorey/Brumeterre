package org.morey.brumeterre.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.morey.brumeterre.main.plugin;

public class ItemBuilder {


    public static class ItemConfig {
        private final Material material;
        private final String name;
        private List<String> lore;
        private double attackDamage;
        private int customModelData;
        private String persistentValue;

        public ItemConfig(Material material, String name)
        {
            this.material = material;
            this.name = "§r" + name;
            this.lore = new ArrayList<>();
            this.attackDamage = 0;
            this.customModelData = 0;
            this.persistentValue = null;
        }

        public ItemConfig setLore(List<String> lore)
        {
            this.lore = lore;
            return this;
        }

        public Material getMaterial()
        {
            return material;
        }

        public String getName()
        {
            return name;
        }

        public List<String> getLore()
        {
            return lore;
        }

        public double getAttackDamage()
        {
            return attackDamage;
        }

        public ItemConfig setAttackDamage(double attackDamage)
        {
            this.attackDamage = attackDamage;
            return this;
        }

        public ItemConfig setCustomModelData(int data)
        {
            this.customModelData = data;
            return this;
        }

        public int getCustomModelData()
        {
            return customModelData;
        }
        public ItemConfig setPersistentValue(String value)
        {
            this.persistentValue = value;
            return this;
        }

        public String getPersistentValue()
        {
            return persistentValue;
        }
    }

    public static ItemStack createItem(ItemBuilder.ItemConfig build)
    {
        ItemStack item = new ItemStack(build.getMaterial());

        ItemMeta meta = item.getItemMeta();

        if(meta != null)
        {
            if(build.getAttackDamage() > 0)
            {
                UUID modifierUUID = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
                AttributeModifier modifier = new AttributeModifier(modifierUUID, "generic.attackDamage", build.getAttackDamage(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
            }
            if(build.getCustomModelData() > 0)
            {
                meta.setCustomModelData(build.getCustomModelData());
            }
            meta.setDisplayName(build.getName());
            meta.setLore(build.getLore());
            Bukkit.getLogger().info("test1");
            if(build.getPersistentValue() != null)
            {
                Bukkit.getLogger().info("test2");
                NamespacedKey key = new NamespacedKey(plugin, "custom_item");
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, build.getPersistentValue());
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
