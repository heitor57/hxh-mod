package com.heitor57.hunterxhunter.entity;

import com.heitor57.hunterxhunter.ClientMessage;
import com.heitor57.hunterxhunter.ServerMessage;
import com.heitor57.hunterxhunter.hxh;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class Fireball extends EntitySnowball{
	
	public Fireball(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		// TODO Auto-generated constructor stub
	}

	static int count=0; 
	@Override
	protected void onImpact(RayTraceResult result) {
		//if (!Minecraft.getMinecraft().world.isRemote) {
			System.out.println("ewqewwq eqweqw ");
			explosioooon(result.hitVec);
			
			Minecraft.getMinecraft().world.removeEntity(this);
			super.onImpact(result);
		//}
	}
	
	public static void explosioooon(Vec3d vec) {
		float size = 5f;
		Explosion explosion = new Explosion(
				Minecraft.getMinecraft().world,
				null,
				vec.x, 
				vec.y,
				vec.z,
				size, 
				false,
				true);
		hxh.network.sendToServer(new ServerMessage(vec.x,vec.y,vec.z,size));
		explosion.doExplosionA();
		explosion.doExplosionB(true);
	}
}
