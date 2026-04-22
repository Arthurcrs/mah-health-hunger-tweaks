package com.mahghuuuls.mahhealthhungertweaks.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RegenTimerCapability {

    public interface IRegenTimer {
        int getTicksSinceDamage();
        void setTicksSinceDamage(int ticks);
        void tick();
        void reset();
    }

    public static class DefaultRegenTimer implements IRegenTimer {
        private int ticksSinceDamage = 0;

        @Override
        public int getTicksSinceDamage() { return ticksSinceDamage; }

        @Override
        public void setTicksSinceDamage(int ticks) { this.ticksSinceDamage = ticks; }

        @Override
        public void tick() { this.ticksSinceDamage++; }

        @Override
        public void reset() { this.ticksSinceDamage = 0; }
    }

    public static class Storage implements IStorage<IRegenTimer> {
        @Override
        public NBTBase writeNBT(Capability<IRegenTimer> capability, IRegenTimer instance, EnumFacing side) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("ticksSinceDamage", instance.getTicksSinceDamage());
            return tag;
        }

        @Override
        public void readNBT(Capability<IRegenTimer> capability, IRegenTimer instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound) {
                instance.setTicksSinceDamage(((NBTTagCompound) nbt).getInteger("ticksSinceDamage"));
            }
        }
    }
}