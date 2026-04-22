package com.mahghuuuls.examplemod.config;

import com.mahghuuuls.examplemod.ExampleMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ExampleMod.MOD_ID)
@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ModConfig {
    @SubscribeEvent // This method fires when the user clicks "Done" in the in-game Mod Options menu
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ExampleMod.MOD_ID)) {
            // Forces Forge to read the updated values from the GUI and write them to your variables and the .cfg file
            ConfigManager.sync(ExampleMod.MOD_ID, Config.Type.INSTANCE);
        }
    }

    @Config.Comment("Example config boolean")
    public static boolean exampleBoolean = true;
}