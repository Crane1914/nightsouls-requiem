package com.saita.nightsoulsmod.common.items;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.saita.nightsoulsmod.core.init.SoundInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class NightSoulsBackupKey extends Item {
	
	public static final Logger LOGGER = LogManager.getLogger();
	BlockPos requiemBlockPos = new BlockPos(0,0,0);

	public NightSoulsBackupKey(Properties properties) {
		super(properties);

	}
	
	@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {

	tooltip.add(new StringTextComponent("�7ADMIN ONLY. Right Click to revert NightSouls Requiem."));
	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		
		return true;
	}
	
	@Override
	public Rarity getRarity(ItemStack stack) {
		
		return Rarity.EPIC;
	}
	
	 @Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {	 
		 	    
	    if(playerIn.world.getDimensionKey().getLocation().getPath() == "overworld")
  	    {
	    	ItemStack itemstack = playerIn.getHeldItem(handIn);
		    itemstack.shrink(1);
		    playerIn.getCooldownTracker().setCooldown(this, 200);
	    
		    if(worldIn.getWorldBorder().getSize() == NightSoulsKey.requiemWBSize)
		    {
		    	worldIn.getWorldBorder().setSize(NightSoulsKey.defaultWBSize);	    	
			    
		    	worldIn.playSound(playerIn, playerIn.getPosition(), SoundInit.REQUIEM.get(), SoundCategory.MASTER, 1.0F, 1.0F);	    	    	
	
			    if(worldIn.isRemote)
			    {		
			    	LOGGER.info(""+playerIn.getName().getString()+" a fait revenir en arri�re le mode NightSouls Requiem sur ce monde.");      	
			    	playerIn.sendMessage(new TranslationTextComponent("You reverted the NightSouls Requiem World."), null);   	
			    }
			    
		    }
		    else
		    {	 	     			 
		    	 if(worldIn.isRemote)
		 	     {
		 	 	     playerIn.sendMessage(new TranslationTextComponent("This world isn't a NightSouls Requiem World."), null);
		 	     }
		    }
	    
  	    }
	    else
    	{
    	    if(worldIn.isRemote)
    		{
  	           playerIn.sendMessage(new TranslationTextComponent("�f"+playerIn.getName().getString()+", you need to be in the Overworld to use a NightSouls Backup Key"), null);
    		}
    	}
	    	    	
	    return super.onItemRightClick(worldIn, playerIn, handIn);
	   }

}
