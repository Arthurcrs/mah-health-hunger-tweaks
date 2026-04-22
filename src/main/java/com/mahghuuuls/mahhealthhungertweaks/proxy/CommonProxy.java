package com.mahghuuuls.mahhealthhungertweaks.proxy;

import com.mahghuuuls.mahhealthhungertweaks.capability.RegenTimerCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {

    public void preInit() {
        // Register our custom Capability so Forge knows how to save/load it
        CapabilityManager.INSTANCE.register(
                RegenTimerCapability.IRegenTimer.class,
                new RegenTimerCapability.Storage(),
                RegenTimerCapability.DefaultRegenTimer::new
        );
    }

    public void init() {
        // Event handlers are auto-registered via @EventBusSubscriber
    }

    public void postInit() {
        // Inter-mod communication, config finalization
    }
}