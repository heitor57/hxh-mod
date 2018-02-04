package com.heitor57.hunterxhunter;




import org.lwjgl.input.Keyboard;

import com.heitor57.hunterxhunter.gui.GuiModNen;
import com.heitor57.hunterxhunter.gui.GuiModStats;

import javax.swing.text.JTextComponent.KeyBinding;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import scala.reflect.api.Trees.PackageDefExtractor;

import java.lang.reflect.Field;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PlayerEventHandler {
	public static final IAttribute 
	MAX_AURA = new RangedAttribute(null, 
			Reference.MOD_ID + ".maxAura", 
			50D, 
			0.0D, 
			Double.MAX_VALUE).setShouldWatch(true),
	STRENGTH = new RangedAttribute(null,
			Reference.MOD_ID + ".maxStrength",
			10D,
			0.0D,
			Double.MAX_VALUE).setShouldWatch(true);


	@SubscribeEvent
	public static void playerloggedin(PlayerEvent.PlayerLoggedInEvent event) {
		hxh.network.sendToServer(new MyMessage("mensagem criada"));
		hxh.network.sendTo(new MyMessage("mensagem criada do play"),(EntityPlayerMP) event.player);
		event.player.getAttributeMap().registerAttribute(MAX_AURA);
		event.player.getAttributeMap().registerAttribute(STRENGTH);
		event.player.getAttributeMap().getAttributeInstance(STRENGTH).setBaseValue(10);
		event.player.getAttributeMap().getAttributeInstance(MAX_AURA).setBaseValue(0);
		
	}
	@SubscribeEvent
	public static void keyshot(InputEvent.KeyInputEvent event) {
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			Minecraft.getMinecraft().displayGuiScreen(new GuiModStats());
		}else if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiModNen());
		}else if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			System.out.println(Minecraft.getMinecraft().player.getName());
			
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
		
	}
	@SubscribeEvent
	public static void itemcrafted(final PlayerEvent.ItemCraftedEvent event) {
		
		IAttributeInstance strength = event.player.getAttributeMap().getAttributeInstance(STRENGTH);
		strength.setBaseValue(strength.getBaseValue()+3);
		
	}
}
