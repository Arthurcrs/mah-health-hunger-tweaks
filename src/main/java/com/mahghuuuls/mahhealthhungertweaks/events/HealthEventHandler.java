package com.mahghuuuls.mahhealthhungertweaks.events;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import com.mahghuuuls.mahhealthhungertweaks.capability.CapabilityHandler;
import com.mahghuuuls.mahhealthhungertweaks.capability.RegenTimerCapability.IRegenTimer;
import com.mahghuuuls.mahhealthhungertweaks.compat.InhibitedCompat;
import com.mahghuuuls.mahhealthhungertweaks.config.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = MahHealthHungerTweaks.MOD_ID)
public class HealthEventHandler {

    // Enforce Natural Regeneration GameRule to False
    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (!event.getWorld().isRemote) {
            GameRules rules = event.getWorld().getGameRules();

            if (rules.getBoolean("naturalRegeneration")) {
                rules.setOrCreateGameRule("naturalRegeneration", "false");
            }
        }
    }

    // Reset the delay timer when taking health-reducing damage
    @SubscribeEvent
    public static void onPlayerTakeDamage(LivingDamageEvent event) {
        if (!event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) {
            if (event.getAmount() > 0.0F) {
                EntityPlayer player = (EntityPlayer) event.getEntityLiving();
                IRegenTimer cap = player.getCapability(CapabilityHandler.REGEN_TIMER_CAP, null);

                if (cap != null) {
                    cap.reset();
                }
            }
        }
    }

    // Handle Passive Regeneration
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote) {
            EntityPlayer player = event.player;

            // Full health, do nothing
            if (player.getHealth() >= player.getMaxHealth()) {
                return;
            }

            // Hunger is below minimum threshold
            if (player.getFoodStats().getFoodLevel() < ModConfig.health.regenMinHunger) {
                return;
            }

            if (ModConfig.health.blockRegenWhileInhibited && InhibitedCompat.hasInhibited(player)) {
                return;
            }

            IRegenTimer cap = player.getCapability(CapabilityHandler.REGEN_TIMER_CAP, null);
            if (cap != null) {
                cap.tick();

                int ticksSinceDamage = cap.getTicksSinceDamage();

                if (ticksSinceDamage >= ModConfig.health.regenDelayTicks) {

                    int activeRegenTicks = ticksSinceDamage - ModConfig.health.regenDelayTicks;

                    if (activeRegenTicks % ModConfig.health.regenHealIntervalTicks == 0) {
                        player.heal(1.0F);
                    }
                }
            }
        }
    }
}