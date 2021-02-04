package dev.elexi.hugeblank.bagels_baking.item;

import dev.elexi.hugeblank.bagels_baking.Baking;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicDrink extends PotionItem implements BrewableItem {


    private static Item.Settings genSettings(Item type) {
        return new Item.Settings().group(ItemGroup.FOOD)
                .maxCount(type.equals(Items.BUCKET) ? 1 : 16)
                .recipeRemainder(type.equals(Items.BUCKET) ? Items.BUCKET : null);
    }

    private boolean isBucket;
    private final boolean brewable;


    private void bucketDrink(Item type) {
        this.isBucket = type.equals(Items.BUCKET);
    }

    public BasicDrink(Item type) {
        super(genSettings(type));
        bucketDrink(type);
        this.brewable = false;
    }

    public BasicDrink(Item type, boolean brewable) {
        super(genSettings(type));
        bucketDrink(type);
        this.brewable = brewable;
    }

    public BasicDrink(Item type, int hunger, float saturation) {
        super(genSettings(type)
                .food(new FoodComponent.Builder()
                        .hunger(hunger)
                        .saturationModifier(saturation)
                        .build()
                )
        );
        bucketDrink(type);
        this.brewable = false;
    }

    // Coffee-Like drinks
    public BasicDrink(int hunger, float saturation, StatusEffectInstance effect) {
        super(genSettings(Baking.CUP)
                .food(new FoodComponent.Builder()
                        .hunger(hunger)
                        .saturationModifier(saturation)
                        .statusEffect(effect, 1.0f)
                        .build()
                )
        );
        bucketDrink(Baking.CUP);
        this.brewable = true;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
            stack.decrement(1);
        }

        if (stack.isEmpty()) {
            return new ItemStack(isBucket ? Items.BUCKET : Baking.CUP);
        } else {
            if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
                ItemStack itemStack = new ItemStack(isBucket ? Items.BUCKET : Baking.CUP);
                PlayerEntity playerEntity = (PlayerEntity)user;
                if (!playerEntity.inventory.insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }
            return stack;
        }
    }

    public int getMaxUseTime(ItemStack stack) {
        return isBucket ? 32 : 16;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            stacks.add(new ItemStack(this));
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return getTranslationKey();
    }

    @Override
    public ItemStack getDefaultStack() {
        return new ItemStack(this);
    }

    public boolean isBucket() {
        return isBucket;
    }

    public boolean isBrewable() {
        return !isBucket && this.brewable;
    }
}
