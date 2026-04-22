package com.mahghuuuls.mahhealthhungertweaks.config;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MahHealthHungerTweaks.MOD_ID, name = "mahhealthhungertweaks")
@Mod.EventBusSubscriber(modid = MahHealthHungerTweaks.MOD_ID)
public class ModConfig {

    public enum SatiatedMode {
        LINEAR,
        DIMINISHING
    }

    @Config.Name("1. Health Settings")
    public static final Health health = new Health();

    @Config.Name("2. Satiated Settings")
    public static final Satiated satiated = new Satiated();

    public static class Health {
        @Config.Comment("How many ticks must pass after taking damage before passive regen starts. (20 ticks = 1 second)")
        @Config.RangeInt(min = 0)
        public int regenDelayTicks = 200;

        @Config.Comment("How many ticks between each 1 HP (half-heart) heal during passive regen.")
        @Config.RangeInt(min = 1)
        public int regenHealIntervalTicks = 80;

        @Config.Comment("Minimum hunger points (half-shanks) required for passive regen to happen.")
        @Config.RangeInt(min = 0, max = 20)
        public int regenMinHunger = 6;

        @Config.Comment("If true, passive regeneration is completely blocked while the player has the Inhibited effect.")
        public boolean blockRegenWhileInhibited = true;
    }

    public static class Satiated {
        @Config.Comment("Which formula to use when calculating Satiated duration.")
        public SatiatedMode satiatedMode = SatiatedMode.LINEAR;

        @Config.Comment("Absolute minimum duration in ticks for Satiated, regardless of calculation.")
        @Config.RangeInt(min = 0)
        public int satiatedMinDuration = 0;

        @Config.Comment("Absolute maximum duration in ticks for Satiated, regardless of calculation.")
        @Config.RangeInt(min = 0)
        public int satiatedMaxDuration = 12000;

        @Config.Name("Linear Mode Settings")
        public final Linear linear = new Linear();

        @Config.Name("Diminishing Mode Settings")
        public final Diminishing diminishing = new Diminishing();
    }

    public static class Linear {
        @Config.Comment("Base duration in ticks given just for eating.")
        @Config.RangeInt(min = 0)
        public int linearBaseDuration = 100;

        @Config.Comment("Duration in ticks added per point of hunger restored.")
        @Config.RangeInt(min = 0)
        public int linearDurationPerHunger = 200;
    }

    public static class Diminishing {
        @Config.Comment("Base duration in ticks given just for eating.")
        @Config.RangeInt(min = 0)
        public int diminishingBaseDuration = 100;

        @Config.Comment("Maximum possible duration in ticks that can be added by the food value itself.")
        @Config.RangeInt(min = 0)
        public int diminishingMaxAddedDuration = 2400;

        @Config.Comment("How aggressively the curve flattens. Higher values mean you reach the max faster.")
        @Config.RangeDouble(min = 0.01, max = 10.0)
        public double diminishingCurveStrength = 0.1;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MahHealthHungerTweaks.MOD_ID)) {
            ConfigManager.sync(MahHealthHungerTweaks.MOD_ID, Config.Type.INSTANCE);
        }
    }
}