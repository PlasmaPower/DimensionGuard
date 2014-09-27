
package com.hilburn.dimensionguard;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import com.hilburn.dimensionguard.handlers.DGEventHandler;
import com.hilburn.dimensionguard.handlers.DisabledHandler;
import com.hilburn.dimensionguard.handlers.TickHandler;
import com.hilburn.dimensionguard.items.ModItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = ModInformation.MODID, name = ModInformation.NAME, version = ModInformation.VERSION)
/**
 * DimensionGuard Mod
 * 
 * @author Charlie Paterson
 * @license GNU General Public License v3
 **/
public class DimensionGuard {
	public static File config;
	@Instance(ModInformation.MODID)
	public static DimensionGuard instance = new DimensionGuard();
	
	@EventHandler 
	public void preInit(FMLPreInitializationEvent event){
		config=event.getSuggestedConfigurationFile();
		ModItems.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(new DGEventHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		DisabledHandler.init();
	}
	
}