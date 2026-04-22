package com.mahghuuuls.mahhealthhungertweaks.config;

import com.mahghuuuls.mahhealthhungertweaks.ExampleMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ExampleMod.MOD_ID)
@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ModConfig {
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ExampleMod.MOD_ID)) {
            ConfigManager.sync(ExampleMod.MOD_ID, Config.Type.INSTANCE);
        }
    }
}