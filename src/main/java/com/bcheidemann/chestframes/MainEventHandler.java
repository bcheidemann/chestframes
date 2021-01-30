package com.bcheidemann.chestframes;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;
import org.bukkit.event.Cancellable;

public class MainEventHandler implements Listener {

    private static final String Cancellable = null;
    private Logger logger;

    public MainEventHandler(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (player == null) {
            return;
        }

        if (player.isSneaking()) {
            return;
        }

        if (entity == null) {
            return;
        }

        if (entity instanceof ItemFrame) {

            ItemFrame itemFrame = (ItemFrame) entity;

            BlockFace attachedBlockFace = itemFrame.getAttachedFace();

            if (attachedBlockFace == null) {
                return;
            }

            Location itemFrameLocation = itemFrame.getLocation();

            Location attachedBlockLocation = itemFrameLocation.add(
                    new Vector(attachedBlockFace.getModX(), attachedBlockFace.getModY(), attachedBlockFace.getModZ()));

            Block attachedBlock = itemFrame.getWorld().getBlockAt(attachedBlockLocation);

            if (attachedBlock == null) {
                return;
            }

            BlockState attachedBlockState = attachedBlock.getState();

            if (attachedBlockState instanceof Container) {
                Container containerBlock = (Container) attachedBlockState;
                player.openInventory(containerBlock.getInventory());
                event.setCancelled(true);
                return;
            }

            else if (attachedBlockState instanceof EnderChest) {
                player.openInventory(player.getEnderChest());
                event.setCancelled(true);
                return;
            }

            Material attachedBlockMaterial = attachedBlockState.getType();

            if (attachedBlockMaterial == null) {
                return;
            }

            if (attachedBlockMaterial.equals(Material.ANVIL)) {
                openInventory(event, InventoryType.ANVIL);
                return;
            }

            if (attachedBlockMaterial.equals(Material.BEACON)) {
                openInventory(event, InventoryType.BEACON);
                return;
            }

            if (attachedBlockMaterial.equals(Material.CARTOGRAPHY_TABLE)) {
                openInventory(event, InventoryType.CARTOGRAPHY);
                return;
            }

            if (attachedBlockMaterial.equals(Material.CRAFTING_TABLE)) {
                openInventory(event, InventoryType.CRAFTING);
                return;
            }

            if (attachedBlockMaterial.equals(Material.ENCHANTING_TABLE)) {
                openInventory(event, InventoryType.ENCHANTING);
                return;
            }

            if (attachedBlockMaterial.equals(Material.GRINDSTONE)) {
                openInventory(event, InventoryType.GRINDSTONE);
                return;
            }

            if (attachedBlockMaterial.equals(Material.LECTERN)) {
                openInventory(event, InventoryType.LECTERN);
                return;
            }

            if (attachedBlockMaterial.equals(Material.LOOM)) {
                openInventory(event, InventoryType.LOOM);
                return;
            }

            if (attachedBlockMaterial.equals(Material.SMITHING_TABLE)) {
                openInventory(event, InventoryType.SMITHING);
                return;
            }

            if (attachedBlockMaterial.equals(Material.STONECUTTER)) {
                openInventory(event, InventoryType.STONECUTTER);
                return;
            }
        }
    }

    private void openInventory(PlayerEvent event, InventoryType type) {
        Player player = event.getPlayer();
        Inventory inventory = Bukkit.createInventory(player, type);
        player.openInventory(inventory);
        if (event instanceof Cancellable) {
            ((Cancellable) event).setCancelled(true);
        }
    }

}
