package dev.elexi.hugeblank.bagels_baking;

import dev.elexi.hugeblank.bagels_baking.block.BasicCakeBlock;
import dev.elexi.hugeblank.bagels_baking.item.MidasSaladItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Baking implements ModInitializer {

	private static final String ID = "bagels_baking";

	// Sandwiches - Gives 2 items
	private static final Item STEAK_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(9).saturationModifier(12.4f).build()));
	private static final Item CHICKEN_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(8).saturationModifier(9.6f).build()));
	private static final Item PORK_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(9).saturationModifier(12.4f).build()));
	private static final Item MUTTON_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(8).saturationModifier(10.8f).build()));
	private static final Item FISH_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(9f).build()));
	private static final Item RABBIT_SANDWICH = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(9f).build()));

	// Dinner - Gives 1 item
	private static final Item STEAK_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(13).saturationModifier(18.8f).build()));
	private static final Item CHICKEN_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(11).saturationModifier(13.2f).build()));
	private static final Item PORK_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(13).saturationModifier(18.8f).build()));
	private static final Item MUTTON_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(11).saturationModifier(15.6f).build()));
	private static final Item FISH_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(10).saturationModifier(12f).build()));
	private static final Item RABBIT_DINNER = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(10).saturationModifier(12f).build()));

	// Pocket - Gives 2 items
	private static final Item STEAK_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(11).saturationModifier(12.9f).build()));
	private static final Item CHICKEN_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(10).saturationModifier(10.1f).build()));
	private static final Item PORK_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(11).saturationModifier(12.9f).build()));
	private static final Item MUTTON_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(10).saturationModifier(11.3f).build()));
	private static final Item FISH_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(9).saturationModifier(9.5f).build()));
	private static final Item RABBIT_POCKET = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(9).saturationModifier(9.5f).build()));

	// The rest of the stews - Gives 1 item
	private static final MushroomStewItem STEAK_STEW = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(14).saturationModifier(18.8f).build()));
	private static final MushroomStewItem CHICKEN_STEW = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(11).saturationModifier(13.2f).build()));
	private static final MushroomStewItem PORK_STEW = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(14).saturationModifier(18.8f).build()));
	private static final MushroomStewItem MUTTON_STEW = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(11).saturationModifier(15.6f).build()));
	private static final MushroomStewItem FISH_STEW = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(10).saturationModifier(12f).build()));
	// No need to add Rabbit Stew!

	// Misc - Item amt listed individually
	private static final Item SURF_N_TURF = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(14f).build())); // Give 2 items
	private static final MushroomStewItem VEGGIE_MEDLEY = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).recipeRemainder(Items.BOWL).food(new FoodComponent.Builder().hunger(9).saturationModifier(7f).build())); // Give 1 item
	private static final MushroomStewItem FRUIT_SALAD = new MushroomStewItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(8).saturationModifier(4f).build())); // Give 1 item
	private static final MidasSaladItem MIDAS_SALAD = new MidasSaladItem( new Item.Settings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(14).saturationModifier(35f).alwaysEdible() // Give 1 item
			.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20*120), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*20, 1), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20*60), 1f).build()));
	private static final Item BAGEL = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(6.5f).build())); // Give 2 items
	private static final Item DONUT = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(7f).build())); // Give 2 items
	private static final Item SHEPHERDS_PIE = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(11).saturationModifier(13.6f).build())); // Give 1 item
	private static final Item COOKED_EGG = new Item( new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(2).saturationModifier(5f).build())); // Give 1 item

	// Tacos - Gives 2 Items
	public static final Item STEAK_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(9.4f).build()));
	public static final Item CHICKEN_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(6).saturationModifier(6.6f).build()));
	public static final Item PORK_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(7).saturationModifier(9.4f).build()));
	public static final Item MUTTON_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(6).saturationModifier(7.8f).build()));
	public static final Item FISH_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(5).saturationModifier(6f).build()));
	public static final Item RABBIT_TACO = new Item(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(5).saturationModifier(6f).build()));

	// Cakes
	public static final Block CARROT_CAKE = new BasicCakeBlock();
	public static final BlockItem CARROT_CAKE_ITEM = new BlockItem(CARROT_CAKE, new Item.Settings().group(ItemGroup.FOOD));
	public static final Block CHOCOLATE_CAKE = new BasicCakeBlock();
	public static final BlockItem CHOCOLATE_CAKE_ITEM = new BlockItem(CHOCOLATE_CAKE, new Item.Settings().group(ItemGroup.FOOD));



	@Override
	public void onInitialize() {

		// Sandwiches
		Registry.register(Registry.ITEM, new Identifier(ID, "steak_sandwich"), STEAK_SANDWICH);
		Registry.register(Registry.ITEM, new Identifier(ID, "chicken_sandwich"), CHICKEN_SANDWICH);
		Registry.register(Registry.ITEM, new Identifier(ID, "pork_sandwich"), PORK_SANDWICH);
		Registry.register(Registry.ITEM, new Identifier(ID, "mutton_sandwich"), MUTTON_SANDWICH);
		Registry.register(Registry.ITEM, new Identifier(ID, "fish_sandwich"), FISH_SANDWICH);
		Registry.register(Registry.ITEM, new Identifier(ID, "rabbit_sandwich"), RABBIT_SANDWICH);

		// Dinners
		Registry.register(Registry.ITEM, new Identifier(ID, "steak_dinner"), STEAK_DINNER);
		Registry.register(Registry.ITEM, new Identifier(ID, "chicken_dinner"), CHICKEN_DINNER);
		Registry.register(Registry.ITEM, new Identifier(ID, "pork_dinner"), PORK_DINNER);
		Registry.register(Registry.ITEM, new Identifier(ID, "mutton_dinner"), MUTTON_DINNER);
		Registry.register(Registry.ITEM, new Identifier(ID, "fish_dinner"), FISH_DINNER);
		Registry.register(Registry.ITEM, new Identifier(ID, "rabbit_dinner"), RABBIT_DINNER);

		// Pockets
		Registry.register(Registry.ITEM, new Identifier(ID, "steak_pocket"), STEAK_POCKET);
		Registry.register(Registry.ITEM, new Identifier(ID, "chicken_pocket"), CHICKEN_POCKET);
		Registry.register(Registry.ITEM, new Identifier(ID, "pork_pocket"), PORK_POCKET);
		Registry.register(Registry.ITEM, new Identifier(ID, "mutton_pocket"), MUTTON_POCKET);
		Registry.register(Registry.ITEM, new Identifier(ID, "fish_pocket"), FISH_POCKET);
		Registry.register(Registry.ITEM, new Identifier(ID, "rabbit_pocket"), RABBIT_POCKET);

		// Stews
		Registry.register(Registry.ITEM, new Identifier(ID, "steak_stew"), STEAK_STEW);
		Registry.register(Registry.ITEM, new Identifier(ID, "chicken_stew"), CHICKEN_STEW);
		Registry.register(Registry.ITEM, new Identifier(ID, "pork_stew"), PORK_STEW);
		Registry.register(Registry.ITEM, new Identifier(ID, "mutton_stew"), MUTTON_STEW);
		Registry.register(Registry.ITEM, new Identifier(ID, "fish_stew"), FISH_STEW);
		// No need to add rabbit stew!

		// Tacos
		Registry.register(Registry.ITEM, new Identifier(ID, "steak_taco"), STEAK_TACO);
		Registry.register(Registry.ITEM, new Identifier(ID, "chicken_taco"), CHICKEN_TACO);
		Registry.register(Registry.ITEM, new Identifier(ID, "pork_taco"), PORK_TACO);
		Registry.register(Registry.ITEM, new Identifier(ID, "mutton_taco"), MUTTON_TACO);
		Registry.register(Registry.ITEM, new Identifier(ID, "fish_taco"), FISH_TACO);
		Registry.register(Registry.ITEM, new Identifier(ID, "rabbit_taco"), RABBIT_TACO);

		// Cakes
		final Identifier carrot_cake =  new Identifier(ID, "carrot_cake");
		final Identifier chocolate_cake =  new Identifier(ID, "chocolate_cake");
		Registry.register(Registry.BLOCK, carrot_cake, CARROT_CAKE);
		Registry.register(Registry.BLOCK, chocolate_cake, CHOCOLATE_CAKE);
		Registry.register(Registry.ITEM, carrot_cake, CARROT_CAKE_ITEM);
		Registry.register(Registry.ITEM, chocolate_cake, CHOCOLATE_CAKE_ITEM);

		// Misc
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "surf_n_turf"), SURF_N_TURF);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "veggie_medley"), VEGGIE_MEDLEY);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "fruit_salad"), FRUIT_SALAD);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "midas_salad"), MIDAS_SALAD);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "bagel"), BAGEL);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "donut"), DONUT);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "shepherds_pie"), SHEPHERDS_PIE);
		Registry.register(Registry.ITEM, new Identifier("bagels_baking", "cooked_egg"), COOKED_EGG);

	}
}
