package com.mahghuuuls.examplemod;

import com.mahghuuuls.examplemod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MOD_ID, name = ExampleMod.NAME, version = ExampleMod.VERSION, dependencies = ExampleMod.DEPENDENCIES)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES = "";

    @SidedProxy(clientSide = "com.mahghuuuls.examplemod.proxy.ClientProxy", serverSide = "com.mahghuuuls.examplemod.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(); // On a client, this executes ClientProxy#preInit. On a server, CommonProxy#preInit.
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(); // On a client, this executes ClientProxy#init. On a server, CommonProxy#init.
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(); // On a client, this executes ClientProxy#postInit. On a server, CommonProxy#postInit.
    }
}