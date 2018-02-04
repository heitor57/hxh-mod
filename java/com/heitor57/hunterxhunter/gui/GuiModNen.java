package com.heitor57.hunterxhunter.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import com.heitor57.hunterxhunter.AuraInfo;
import com.heitor57.hunterxhunter.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.ResourceLocation;

public class GuiModNen extends GuiScreen{
	final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/gui/blankpaper.png");
	int guiheight=230,guiwidth=230;
	static String auratype = new String();
	final int BUTTON1=0;
	GuiButton button1;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		int centerx = (width/2)-guiwidth/2;
		int centery = (height/2)-guiheight/2;
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(centerx, centery, 0, 0, guiwidth, guiheight);
		if(!auratype.isEmpty())
			drawCenteredString(fontRenderer, "Aura type: "+auratype,width/2, centery,Color.WHITE.getRGB());
		drawCenteredString(fontRenderer, "Enhancement",width/2,height/2-40, Color.GREEN.getRGB());
		drawCenteredString(fontRenderer, "Transmutation",width/2+50,height/2-15, Color.MAGENTA.getRGB());
		drawCenteredString(fontRenderer, "Conjuration",width/2+50,height/2+15, Color.RED.getRGB());
		drawCenteredString(fontRenderer, "Emission",width/2-50,height/2-15, Color.YELLOW.getRGB());
		drawCenteredString(fontRenderer, "Manipulation",width/2-50,height/2+15, Color.GRAY.getRGB());
		drawCenteredString(fontRenderer, "Specialization",width/2,height/2+40, Color.BLUE.getRGB());
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		if(auratype.isEmpty()) {
			buttonList.add(button1 = new GuiButton(BUTTON1,(width/2)-guiwidth/2,((height/2)-guiheight/2),30,20,"Roll"));
		}
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == BUTTON1) {
			if(auratype.isEmpty()) {
				int randomn = new Random().nextInt(AuraInfo.types.length);
				auratype = AuraInfo.types[randomn];
			}
			if(!auratype.isEmpty()){
				button1.enabled = false;
			}
		}
		super.actionPerformed(button);
	}
}
