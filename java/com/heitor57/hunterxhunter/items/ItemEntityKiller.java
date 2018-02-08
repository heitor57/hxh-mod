package com.heitor57.hunterxhunter.items;

import com.heitor57.hunterxhunter.entity.Fireball;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder.Spawn;

/**
 * An item that kills an entity when you left click on it.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2760814-getting-entitys-string-id
 *
 * @author Choonster
 */
public class ItemEntityKiller extends ModItemBase {
	public ItemEntityKiller() {
		super("entity_killer");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
		if (!player.world.isRemote) {
			final Entity entityToKill;
			if (entity instanceof MultiPartEntityPart) { // If it's a multipart entity, kill the main entity
				entityToKill = (Entity) ((MultiPartEntityPart) entity).parent;
			} else {
				entityToKill = entity;
			}

			entityToKill.onKillCommand();
			player.sendMessage(new TextComponentTranslation("commands.kill.successful", entityToKill.getDisplayName()));
		}

		return true;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			System.out.println("eqweeqwe");
			Fireball entityfireball= new Fireball(worldIn,playerIn);
			entityfireball.shoot(playerIn, playerIn.rotationPitch,playerIn.rotationYaw, 1F, 1.5F, 1.0F);
			worldIn.spawnEntity(entityfireball);	
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
}
