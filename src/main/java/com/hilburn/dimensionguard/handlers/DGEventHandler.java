package com.hilburn.dimensionguard.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
/**
 * DimensionGuard Mod
 * 
 * @author Charlie Paterson
 * @license GNU General Public License v3
 **/
public class DGEventHandler {
	
//	@SubscribeEvent
//	public void PlayerInteract(PlayerInteractEvent event){
//		if (event.action==Action.RIGHT_CLICK_BLOCK){
//			ItemStack heldItem=event.entityPlayer.getCurrentEquippedItem();
//			if (heldItem!=null){
//				if (event.entityPlayer.isSneaking()||!event.world.getBlock(event.x, event.y, event.z)
//						.onBlockActivated(event.world, event.x, event.y, event.z, event.entityPlayer, event.face, 0.5F, 0.5F, 0.5F)){//(event.world.getTileEntity(event.x, event.y, event.z)==null)){
//					//if (DisabledHandler.isDisabledBlock(heldItem.getItem(),heldItem.getItemDamage(),event.entityPlayer.dimension)) {
//					if (DisabledHandler.isDisabled(GameRegistry.findUniqueIdentifierFor(heldItem.getItem()).toString(),heldItem.getItemDamage(),event.entityPlayer.dimension)) {
//						if (event.world.isRemote)Logger.chatLog(event.entityPlayer,"[DimensionGuard] Placing "+heldItem.getDisplayName()+" in Dimension "+ event.entityPlayer.dimension+ " has been disabled.");
//						event.setCanceled(true);
//						//Logger.log(event.entityPlayer.getLookVec().xCoord+","+event.entityPlayer.getLookVec().yCoord+","+event.entityPlayer.getLookVec().zCoord);
//					}
//				}
//			}
//		}
//	}
	
	@SubscribeEvent
	public void itemPickupEvent(EntityItemPickupEvent event){
		ItemStack stack = event.item.getEntityItem();
		event.item.setEntityItemStack(DisabledHandler.scanStack(stack, event.entity.dimension, false));
	}
	
	@SubscribeEvent 
	public void checkSpawnEvent(LivingSpawnEvent.CheckSpawn event){
		if (DisabledHandler.isDisabledEntity(event.entityLiving.getClass(), event.entityLiving.dimension)) event.setResult(Result.DENY);
	}
	
//	@SubscribeEvent
//	public void JoinWorld(EntityJoinWorldEvent event){
//		if (event.entity instanceof EntityPlayer && !event.world.isRemote){
//			DisabledHandler.scanInventory((EntityPlayer)event.entity,false);
//		}
//	}
	
	@SubscribeEvent
	public void changeDimension(PlayerChangedDimensionEvent event)
	{
		DisabledHandler.scanInventory(event.player,false);
	}
	

	
	
	
	
}
