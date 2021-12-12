package com.saita.nightsoulsmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ParagonicBlock extends Block {

	public ParagonicBlock(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {

		return 15;
	}
		
}
