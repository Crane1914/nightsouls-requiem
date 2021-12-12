package com.saita.nightsoulsmod.common.world;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import com.saita.nightsoulsmod.core.init.StructureInit;

public class StructureGen {
	
	 public static void generateStructures(final BiomeLoadingEvent event) {
	        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
	        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

	        if(types.contains(BiomeDictionary.Type.OVERWORLD) && !types.contains(BiomeDictionary.Type.OCEAN)) {
	            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

	            structures.add(() -> StructureInit.NIGHTSOULS_CAMP.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	        }
	    }

}
