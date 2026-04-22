package com.mahghuuuls.mahhealthhungertweaks.capability;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MahHealthHungerTweaks.MOD_ID)
public class CapabilityHandler {

    @CapabilityInject(RegenTimerCapability.IRegenTimer.class)
    public static Capability<RegenTimerCapability.IRegenTimer> REGEN_TIMER_CAP = null;

    public static final ResourceLocation CAP_KEY = new ResourceLocation(MahHealthHungerTweaks.MOD_ID, "regen_timer");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(CAP_KEY, new RegenTimerProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            RegenTimerCapability.IRegenTimer oldCap = event.getOriginal().getCapability(REGEN_TIMER_CAP, null);
            RegenTimerCapability.IRegenTimer newCap = event.getEntityPlayer().getCapability(REGEN_TIMER_CAP, null);

            if (oldCap != null && newCap != null) {
                newCap.setTicksSinceDamage(oldCap.getTicksSinceDamage());
            }
        }
    }
}