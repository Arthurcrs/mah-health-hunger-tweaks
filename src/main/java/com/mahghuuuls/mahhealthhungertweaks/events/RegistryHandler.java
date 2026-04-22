package com.mahghuuuls.mahhealthhungertweaks.events;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import com.mahghuuuls.mahhealthhungertweaks.potion.PotionSatiated;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MahHealthHungerTweaks.MOD_ID)
public class RegistryHandler {

    public static final Potion SATIATED = new PotionSatiated();

    @SubscribeEvent
    public static void onPotionRegistry(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(SATIATED);
    }
}