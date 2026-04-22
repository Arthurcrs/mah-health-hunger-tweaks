package com.mahghuuuls.mahhealthhungertweaks.potion;

import com.mahghuuuls.mahhealthhungertweaks.MahHealthHungerTweaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionSatiated extends Potion {

    private static final ResourceLocation ICON = new ResourceLocation(MahHealthHungerTweaks.MOD_ID, "textures/gui/potion_satiated.png");

    public PotionSatiated() {
        // false = this is a "good" effect (not a debuff)
        // 0xFFD700 = The liquid color (Gold/Yellow)
        super(false, 0xFFD700);

        this.setRegistryName(new ResourceLocation(MahHealthHungerTweaks.MOD_ID, "satiated"));
        this.setPotionName("effect.mahhealthhungertweaks.satiated");
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        if (mc.currentScreen != null) {
            mc.getTextureManager().bindTexture(ICON);
            Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
        }
    }
}