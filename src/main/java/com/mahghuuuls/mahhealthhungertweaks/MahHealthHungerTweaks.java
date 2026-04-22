package com.mahghuuuls.mahhealthhungertweaks;

import com.mahghuuuls.mahhealthhungertweaks.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MahHealthHungerTweaks.MOD_ID, name = MahHealthHungerTweaks.NAME, version = MahHealthHungerTweaks.VERSION)
public class MahHealthHungerTweaks {
    public static final String MOD_ID = "mahhealthhungertweaks";
    public static final String NAME = "Mah Health and Hunger Tweaks";
    public static final String VERSION = "1.0.0";

    @SidedProxy(
            clientSide = "com.mahghuuuls.mahhealthhungertweaks.proxy.ClientProxy",
            serverSide = "com.mahghuuuls.mahhealthhungertweaks.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}