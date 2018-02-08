package com.heitor57.hunterxhunter.gui;

import java.io.IOException;

import com.heitor57.hunterxhunter.PlayerEventHandler;
import com.heitor57.hunterxhunter.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiModStats extends GuiScreen{
	
	final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/gui/blankpaper.png");
	int guiheight=230,guiwidth=230;
	GuiButton button1,button2;
	
	final int BUTTON1 =0,BUTTON2=1;
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		IAttributeInstance strength = Minecraft.getMinecraft().player.getAttributeMap().getAttributeInstance(PlayerEventHandler.STRENGTH);
		IAttributeInstance maxaura = Minecraft.getMinecraft().player.getAttributeMap().getAttributeInstance(PlayerEventHandler.MAX_AURA);
		drawDefaultBackground();
		int centerx = (width/2)-guiwidth/2;
		int centery = (height/2)-guiheight/2;
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(centerx,centery,0,0,guiwidth,guiheight);
		drawString(fontRenderer, "Stats",(width/2)-fontRenderer.getStringWidth("Stats")/2, centery, 0);
		
		drawString(fontRenderer,String.valueOf(strength.getBaseValue()),(width/2)-fontRenderer.getStringWidth(String.valueOf(strength.getBaseValue()))/2,height/2,0);
		drawString(fontRenderer,String.valueOf(maxaura.getBaseValue()),(width/2)-fontRenderer.getStringWidth(String.valueOf(maxaura.getBaseValue()))/2,height/2+30,0);

		mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.EMERALD), centerx,centery);

		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(button1 = new GuiButton(BUTTON1,(width/2)+30,(height/2),20,20,"+"));
		buttonList.add(button2 = new GuiButton(BUTTON2,(width/2)+30,(height/2)+30,20,20,"+"));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		IAttributeInstance strength = Minecraft.getMinecraft().player.getAttributeMap().getAttributeInstance(PlayerEventHandler.STRENGTH);
		IAttributeInstance maxaura = Minecraft.getMinecraft().player.getAttributeMap().getAttributeInstance(PlayerEventHandler.MAX_AURA);
		
		switch(button.id) {
			case BUTTON1:
				strength.setBaseValue(strength.getBaseValue() +10);
				break;
			case BUTTON2:
				maxaura.setBaseValue(maxaura.getBaseValue() +10);
				break;
		}
		super.actionPerformed(button);
	}
	
}
