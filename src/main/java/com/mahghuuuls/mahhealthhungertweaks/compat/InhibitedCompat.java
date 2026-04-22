package com.mahghuuuls.mahhealthhungertweaks.compat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InhibitedCompat {

    private static boolean initialized = false;
    private static Potion inhibitedPotion = null;

    public static boolean hasInhibited(EntityPlayer player) {
        if (!Loader.isModLoaded("inhibited")) {
            return false;
        }

        if (!initialized) {
            inhibitedPotion = ForgeRegistries.POTIONS.getValue(new ResourceLocation("inhibited", "inhibited"));
            initialized = true;
        }

        if (inhibitedPotion != null) {
            return player.isPotionActive(inhibitedPotion);
        }

        return false;
    }
}