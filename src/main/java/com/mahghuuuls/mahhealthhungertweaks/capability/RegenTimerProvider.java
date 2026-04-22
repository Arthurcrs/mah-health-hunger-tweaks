package com.mahghuuuls.mahhealthhungertweaks.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RegenTimerProvider implements ICapabilitySerializable<NBTTagCompound> {

    private final RegenTimerCapability.IRegenTimer instance = new RegenTimerCapability.DefaultRegenTimer();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability != null && capability == CapabilityHandler.REGEN_TIMER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability != null && capability == CapabilityHandler.REGEN_TIMER_CAP) {
            return CapabilityHandler.REGEN_TIMER_CAP.cast(instance);
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("ticksSinceDamage", this.instance.getTicksSinceDamage());
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.instance.setTicksSinceDamage(nbt.getInteger("ticksSinceDamage"));
    }


}