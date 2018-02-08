package com.heitor57.hunterxhunter;




import org.lwjgl.input.Keyboard;

import com.heitor57.hunterxhunter.gui.GuiModNen;
import com.heitor57.hunterxhunter.gui.GuiModStats;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PlayerEventHandler {
	public static final IAttribute 
	MAX_AURA = new RangedAttribute(null, 
			Reference.MOD_ID + ".maxAura", 
			0D, 
			0.0D, 
			Double.MAX_VALUE).setShouldWatch(true),
	STRENGTH = new RangedAttribute(null,
			Reference.MOD_ID + ".maxStrength",
			0D,
			0.0D,
			Double.MAX_VALUE).setShouldWatch(true);


	@SubscribeEvent
	public static void playerloggedin(PlayerEvent.PlayerLoggedInEvent event) {
		double strengthb=10,maxaurab=30;
		hxh.network.sendTo(new ClientMessage(),(EntityPlayerMP)event.player);
		event.player.getAttributeMap().registerAttribute(MAX_AURA);
		event.player.getAttributeMap().registerAttribute(STRENGTH);
		event.player.getAttributeMap().getAttributeInstance(STRENGTH).setBaseValue(strengthb);
		event.player.getAttributeMap().getAttributeInstance(MAX_AURA).setBaseValue(maxaurab);
	}
	@SubscribeEvent
	public static void keyshot(InputEvent.KeyInputEvent event) {
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			Minecraft.getMinecraft().displayGuiScreen(new GuiModStats());
		}else if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiModNen());
		}else if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			System.out.println(Minecraft.getMinecraft().player.getName());
			//if(!Minecraft.getMinecraft().world.isRemote)
			//Minecraft.getMinecraft().world.expl
		/*	Minecraft.getMinecraft().world.newExplosion(
					null, 
					Minecraft.getMinecraft().player.posX, 
					Minecraft.getMinecraft().player.posY,
					Minecraft.getMinecraft().player.posZ,
					100f,
					false,
					false); */
			
			//explosion.
						
		}
		
		//Minecraft.getMinecraft().player.getAttributeMap().;
		
	}
	
	@SubscribeEvent
	public static void attackmodif(final AttackEntityEvent event) {
		
		IAttributeInstance strength = event.getEntityPlayer().getAttributeMap().getAttributeInstance(STRENGTH);
		
		float idamage=0,damage;
		for (AttributeModifier modifier:event.getEntityPlayer().getHeldItemMainhand().getAttributeModifiers(EntityEquipmentSlot.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())){
			idamage += modifier.getAmount();
		}
		// 2 same lines for performance
		if(idamage == 0 && event.getEntityPlayer().getHeldItemMainhand().getItem() == Items.AIR){
			damage=(int)strength.getBaseValue()/100;
			event.getTarget().attackEntityFrom(DamageSource.GENERIC,damage);
		}else if(idamage>0){
			damage = idamage;
			event.getTarget().attackEntityFrom(DamageSource.GENERIC,damage);
		}
		strength.setBaseValue(strength.getBaseValue()+5);
		
		
		
		//Minecraft.getMinecraft().world.spawnEntity(entityIn)
	}
	
	@SubscribeEvent
	public static void itemcrafted(final PlayerEvent.ItemCraftedEvent event) {
		IAttributeInstance strength = event.player.getAttributeMap().getAttributeInstance(STRENGTH);
		strength.setBaseValue(strength.getBaseValue()+3);
		
	}
	@SubscribeEvent
	public static void itempicked(final PlayerEvent.ItemPickupEvent event) {
		
		
	}
}
