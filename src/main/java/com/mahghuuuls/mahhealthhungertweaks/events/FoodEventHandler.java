package com.mahghuuuls.mahhealthhungertweaks.events;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import com.mahghuuuls.mahhealthhungertweaks.config.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.hunger.ExhaustionEvent;

@Mod.EventBusSubscriber(modid = MahHealthHungerTweaks.MOD_ID)
public class FoodEventHandler {

    // Detect Eating and Apply Satiated
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        if (!event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) {
            ItemStack consumedStack = event.getItem();

            if (consumedStack.getItem() instanceof ItemFood) {
                ItemFood food = (ItemFood) consumedStack.getItem();
                int hungerRestored = food.getHealAmount(consumedStack);

                if (hungerRestored > 0) {
                    EntityPlayer player = (EntityPlayer) event.getEntityLiving();

                    // Calculate base duration
                    int calculatedTicks = calculateSatiatedDuration(hungerRestored);

                    // Clamp to Min/Max config limits
                    calculatedTicks = Math.max(ModConfig.satiated.satiatedMinDuration, calculatedTicks);
                    calculatedTicks = Math.min(ModConfig.satiated.satiatedMaxDuration, calculatedTicks);

                    // Check for existing effect to avoid stacking additively
                    PotionEffect existingEffect = player.getActivePotionEffect(RegistryHandler.SATIATED);
                    int finalTicks = calculatedTicks;

                    if (existingEffect != null) {
                        finalTicks = Math.max(existingEffect.getDuration(), calculatedTicks);
                    }

                    // Apply the effect (Duration: finalTicks, Amplifier: 0, Ambient: false, ShowParticles: true)
                    player.addPotionEffect(new PotionEffect(RegistryHandler.SATIATED, finalTicks, 0, false, true));
                }
            }
        }
    }

    // Prevent Hunger Loss & Handle Debuff Interaction
    @SubscribeEvent
    public static void onExhaustion(ExhaustionEvent.AllowExhaustion event) {
        if (event.player.isPotionActive(RegistryHandler.SATIATED)) {

            // If player receives vanilla Hunger debuff, remove Satiated
            if (event.player.isPotionActive(MobEffects.HUNGER)) {
                event.player.removePotionEffect(RegistryHandler.SATIATED);
            } else {
                // While Satiated is active, deny exhaustion safely via AppleCore
                event.setResult(Event.Result.DENY);
            }
        }
    }

    private static int calculateSatiatedDuration(int hungerRestored) {
        if (ModConfig.satiated.satiatedMode == ModConfig.SatiatedMode.LINEAR) {
            // Linear: Base + (Hunger * Multiplier)
            return ModConfig.satiated.linear.linearBaseDuration +
                    (hungerRestored * ModConfig.satiated.linear.linearDurationPerHunger);

        } else {
            // Diminishing: Base + MaxAdded * (1 - e^(-Strength * Hunger))
            double base = ModConfig.satiated.diminishing.diminishingBaseDuration;
            double maxAdded = ModConfig.satiated.diminishing.diminishingMaxAddedDuration;
            double strength = ModConfig.satiated.diminishing.diminishingCurveStrength;

            double addedDuration = maxAdded * (1.0 - Math.exp(-strength * hungerRestored));

            return (int) Math.round(base + addedDuration);
        }
    }
}