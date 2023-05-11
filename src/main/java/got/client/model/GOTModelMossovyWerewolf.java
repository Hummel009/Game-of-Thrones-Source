package got.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class GOTModelMossovyWerewolf extends ModelBase {
	public boolean hunched;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Snout;
	ModelRenderer TeethU;
	ModelRenderer TeethL;
	ModelRenderer Mouth;
	ModelRenderer LEar;
	ModelRenderer REar;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer SideburnL;
	ModelRenderer SideburnR;
	ModelRenderer Chest;
	ModelRenderer Abdomen;
	ModelRenderer TailA;
	ModelRenderer TailC;
	ModelRenderer TailB;
	ModelRenderer TailD;
	ModelRenderer RLegA;
	ModelRenderer RFoot;
	ModelRenderer RLegB;
	ModelRenderer RLegC;
	ModelRenderer LLegB;
	ModelRenderer LFoot;
	ModelRenderer LLegC;
	ModelRenderer LLegA;
	ModelRenderer RArmB;
	ModelRenderer RArmC;
	ModelRenderer LArmB;
	ModelRenderer RHand;
	ModelRenderer RArmA;
	ModelRenderer LArmA;
	ModelRenderer LArmC;
	ModelRenderer LHand;
	ModelRenderer RFinger1;
	ModelRenderer RFinger2;
	ModelRenderer RFinger3;
	ModelRenderer RFinger4;
	ModelRenderer RFinger5;
	ModelRenderer LFinger1;
	ModelRenderer LFinger2;
	ModelRenderer LFinger3;
	ModelRenderer LFinger4;
	ModelRenderer LFinger5;

	public GOTModelMossovyWerewolf() {
		textureWidth = 64;
		textureHeight = 128;
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-4.0f, -3.0f, -6.0f, 8, 8, 6);
		Head.setRotationPoint(0.0f, -8.0f, -6.0f);
		Head.setTextureSize(64, 128);
		Nose = new ModelRenderer(this, 44, 33);
		Nose.addBox(-1.5f, -1.7f, -12.3f, 3, 2, 7);
		Nose.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(Nose, 0.2792527f, 0.0f, 0.0f);
		Snout = new ModelRenderer(this, 0, 25);
		Snout.addBox(-2.0f, 2.0f, -12.0f, 4, 2, 6);
		Snout.setRotationPoint(0.0f, -8.0f, -6.0f);
		TeethU = new ModelRenderer(this, 46, 18);
		TeethU.addBox(-2.0f, 4.01f, -12.0f, 4, 2, 5);
		TeethU.setRotationPoint(0.0f, -8.0f, -6.0f);
		TeethL = new ModelRenderer(this, 20, 109);
		TeethL.addBox(-1.5f, -12.5f, 2.01f, 3, 5, 2);
		TeethL.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(TeethL, 2.530727f, 0.0f, 0.0f);
		Mouth = new ModelRenderer(this, 42, 69);
		Mouth.addBox(-1.5f, -12.5f, 0.0f, 3, 9, 2);
		Mouth.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(Mouth, 2.530727f, 0.0f, 0.0f);
		LEar = new ModelRenderer(this, 13, 14);
		LEar.addBox(0.5f, -7.5f, -1.0f, 3, 5, 1);
		LEar.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(LEar, 0.0f, 0.0f, 0.1745329f);
		REar = new ModelRenderer(this, 22, 0);
		REar.addBox(-3.5f, -7.5f, -1.0f, 3, 5, 1);
		REar.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(REar, 0.0f, 0.0f, -0.1745329f);
		Neck = new ModelRenderer(this, 28, 0);
		Neck.addBox(-3.5f, -3.0f, -7.0f, 7, 8, 7);
		Neck.setRotationPoint(0.0f, -5.0f, -2.0f);
		setRotation(Neck, -0.6025001f, 0.0f, 0.0f);
		Neck2 = new ModelRenderer(this, 0, 14);
		Neck2.addBox(-1.5f, -2.0f, -5.0f, 3, 4, 7);
		Neck2.setRotationPoint(0.0f, -1.0f, -6.0f);
		setRotation(Neck2, -0.4537856f, 0.0f, 0.0f);
		SideburnL = new ModelRenderer(this, 28, 33);
		SideburnL.addBox(3.0f, 0.0f, -2.0f, 2, 6, 6);
		SideburnL.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(SideburnL, -0.2094395f, 0.418879f, -0.0872665f);
		SideburnR = new ModelRenderer(this, 28, 45);
		SideburnR.addBox(-5.0f, 0.0f, -2.0f, 2, 6, 6);
		SideburnR.setRotationPoint(0.0f, -8.0f, -6.0f);
		setRotation(SideburnR, -0.2094395f, -0.418879f, 0.0872665f);
		Chest = new ModelRenderer(this, 20, 15);
		Chest.addBox(-4.0f, 0.0f, -7.0f, 8, 8, 10);
		Chest.setRotationPoint(0.0f, -6.0f, -2.5f);
		setRotation(Chest, 0.641331f, 0.0f, 0.0f);
		Abdomen = new ModelRenderer(this, 0, 40);
		Abdomen.addBox(-3.0f, -8.0f, -8.0f, 6, 14, 8);
		Abdomen.setRotationPoint(0.0f, 4.5f, 5.0f);
		setRotation(Abdomen, 0.2695449f, 0.0f, 0.0f);
		TailA = new ModelRenderer(this, 52, 42);
		TailA.addBox(-1.5f, -1.0f, -2.0f, 3, 4, 3);
		TailA.setRotationPoint(0.0f, 9.5f, 6.0f);
		setRotation(TailA, 1.064651f, 0.0f, 0.0f);
		TailC = new ModelRenderer(this, 48, 59);
		TailC.addBox(-2.0f, 6.8f, -4.6f, 4, 6, 4);
		TailC.setRotationPoint(0.0f, 9.5f, 6.0f);
		setRotation(TailC, 1.099557f, 0.0f, 0.0f);
		TailB = new ModelRenderer(this, 48, 49);
		TailB.addBox(-2.0f, 2.0f, -2.0f, 4, 6, 4);
		TailB.setRotationPoint(0.0f, 9.5f, 6.0f);
		setRotation(TailB, 0.7504916f, 0.0f, 0.0f);
		TailD = new ModelRenderer(this, 52, 69);
		TailD.addBox(-1.5f, 9.8f, -4.1f, 3, 5, 3);
		TailD.setRotationPoint(0.0f, 9.5f, 6.0f);
		setRotation(TailD, 1.099557f, 0.0f, 0.0f);
		RLegA = new ModelRenderer(this, 12, 64);
		RLegA.addBox(-2.5f, -1.5f, -3.5f, 3, 8, 5);
		RLegA.setRotationPoint(-3.0f, 9.5f, 3.0f);
		setRotation(RLegA, -0.8126625f, 0.0f, 0.0f);
		RFoot = new ModelRenderer(this, 14, 93);
		RFoot.addBox(-2.506667f, 12.5f, -5.0f, 3, 2, 3);
		RFoot.setRotationPoint(-3.0f, 9.5f, 3.0f);
		RLegB = new ModelRenderer(this, 14, 76);
		RLegB.addBox(-1.9f, 4.2f, 0.5f, 2, 2, 5);
		RLegB.setRotationPoint(-3.0f, 9.5f, 3.0f);
		setRotation(RLegB, -0.8445741f, 0.0f, 0.0f);
		RLegC = new ModelRenderer(this, 14, 83);
		RLegC.addBox(-2.0f, 6.2f, 0.5f, 2, 8, 2);
		RLegC.setRotationPoint(-3.0f, 9.5f, 3.0f);
		setRotation(RLegC, -0.2860688f, 0.0f, 0.0f);
		LLegB = new ModelRenderer(this, 0, 76);
		LLegB.addBox(-0.1f, 4.2f, 0.5f, 2, 2, 5);
		LLegB.setRotationPoint(3.0f, 9.5f, 3.0f);
		setRotation(LLegB, -0.8445741f, 0.0f, 0.0f);
		LFoot = new ModelRenderer(this, 0, 93);
		LFoot.addBox(-0.5066667f, 12.5f, -5.0f, 3, 2, 3);
		LFoot.setRotationPoint(3.0f, 9.5f, 3.0f);
		LLegC = new ModelRenderer(this, 0, 83);
		LLegC.addBox(0.0f, 6.2f, 0.5f, 2, 8, 2);
		LLegC.setRotationPoint(3.0f, 9.5f, 3.0f);
		setRotation(LLegC, -0.2860688f, 0.0f, 0.0f);
		LLegA = new ModelRenderer(this, 0, 64);
		LLegA.addBox(-0.5f, -1.5f, -3.5f, 3, 8, 5);
		LLegA.setRotationPoint(3.0f, 9.5f, 3.0f);
		setRotation(LLegA, -0.8126625f, 0.0f, 0.0f);
		RArmB = new ModelRenderer(this, 48, 77);
		RArmB.addBox(-3.5f, 1.0f, -1.5f, 4, 8, 4);
		RArmB.setRotationPoint(-4.0f, -4.0f, -2.0f);
		setRotation(RArmB, 0.2617994f, 0.0f, 0.3490659f);
		RArmC = new ModelRenderer(this, 48, 112);
		RArmC.addBox(-6.0f, 5.0f, 3.0f, 4, 7, 4);
		RArmC.setRotationPoint(-4.0f, -4.0f, -2.0f);
		setRotation(RArmC, -0.3490659f, 0.0f, 0.0f);
		LArmB = new ModelRenderer(this, 48, 89);
		LArmB.addBox(-0.5f, 1.0f, -1.5f, 4, 8, 4);
		LArmB.setRotationPoint(4.0f, -4.0f, -2.0f);
		setRotation(LArmB, 0.2617994f, 0.0f, -0.3490659f);
		RHand = new ModelRenderer(this, 32, 118);
		RHand.addBox(-6.0f, 12.5f, -1.5f, 4, 3, 4);
		RHand.setRotationPoint(-4.0f, -4.0f, -2.0f);
		RArmA = new ModelRenderer(this, 0, 108);
		RArmA.addBox(-5.0f, -3.0f, -2.0f, 5, 5, 5);
		RArmA.setRotationPoint(-4.0f, -4.0f, -2.0f);
		setRotation(RArmA, 0.6320364f, 0.0f, 0.0f);
		LArmA = new ModelRenderer(this, 0, 98);
		LArmA.addBox(0.0f, -3.0f, -2.0f, 5, 5, 5);
		LArmA.setRotationPoint(4.0f, -4.0f, -2.0f);
		setRotation(LArmA, 0.6320364f, 0.0f, 0.0f);
		LArmC = new ModelRenderer(this, 48, 101);
		LArmC.addBox(2.0f, 5.0f, 3.0f, 4, 7, 4);
		LArmC.setRotationPoint(4.0f, -4.0f, -2.0f);
		setRotation(LArmC, -0.3490659f, 0.0f, 0.0f);
		LHand = new ModelRenderer(this, 32, 111);
		LHand.addBox(2.0f, 12.5f, -1.5f, 4, 3, 4);
		LHand.setRotationPoint(4.0f, -4.0f, -2.0f);
		RFinger1 = new ModelRenderer(this, 8, 120);
		RFinger1.addBox(-0.5f, 0.0f, -0.5f, 1, 3, 1);
		RFinger1.setRotationPoint(-6.5f, 11.5f, -0.5f);
		RFinger1 = new ModelRenderer(this, 8, 120);
		RFinger1.addBox(-3.0f, 15.5f, 1.0f, 1, 3, 1);
		RFinger1.setRotationPoint(-4.0f, -4.0f, -2.0f);
		RFinger2 = new ModelRenderer(this, 12, 124);
		RFinger2.addBox(-3.5f, 15.5f, -1.5f, 1, 3, 1);
		RFinger2.setRotationPoint(-4.0f, -4.0f, -2.0f);
		RFinger3 = new ModelRenderer(this, 12, 119);
		RFinger3.addBox(-4.8f, 15.5f, -1.5f, 1, 4, 1);
		RFinger3.setRotationPoint(-4.0f, -4.0f, -2.0f);
		RFinger4 = new ModelRenderer(this, 16, 119);
		RFinger4.addBox(-6.0f, 15.5f, -0.5f, 1, 4, 1);
		RFinger4.setRotationPoint(-4.0f, -4.0f, -2.0f);
		RFinger5 = new ModelRenderer(this, 16, 124);
		RFinger5.addBox(-6.0f, 15.5f, 1.0f, 1, 3, 1);
		RFinger5.setRotationPoint(-4.0f, -4.0f, -2.0f);
		LFinger1 = new ModelRenderer(this, 8, 124);
		LFinger1.addBox(2.0f, 15.5f, 1.0f, 1, 3, 1);
		LFinger1.setRotationPoint(4.0f, -4.0f, -2.0f);
		LFinger2 = new ModelRenderer(this, 0, 124);
		LFinger2.addBox(2.5f, 15.5f, -1.5f, 1, 3, 1);
		LFinger2.setRotationPoint(4.0f, -4.0f, -2.0f);
		LFinger3 = new ModelRenderer(this, 0, 119);
		LFinger3.addBox(3.8f, 15.5f, -1.5f, 1, 4, 1);
		LFinger3.setRotationPoint(4.0f, -4.0f, -2.0f);
		LFinger4 = new ModelRenderer(this, 4, 119);
		LFinger4.addBox(5.0f, 15.5f, -0.5f, 1, 4, 1);
		LFinger4.setRotationPoint(4.0f, -4.0f, -2.0f);
		LFinger5 = new ModelRenderer(this, 4, 124);
		LFinger5.addBox(5.0f, 15.5f, 1.0f, 1, 3, 1);
		LFinger5.setRotationPoint(4.0f, -4.0f, -2.0f);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		Head.render(f5);
		Nose.render(f5);
		Snout.render(f5);
		TeethU.render(f5);
		TeethL.render(f5);
		Mouth.render(f5);
		LEar.render(f5);
		REar.render(f5);
		Neck.render(f5);
		Neck2.render(f5);
		SideburnL.render(f5);
		SideburnR.render(f5);
		Chest.render(f5);
		Abdomen.render(f5);
		TailA.render(f5);
		TailC.render(f5);
		TailB.render(f5);
		TailD.render(f5);
		RLegA.render(f5);
		RFoot.render(f5);
		RLegB.render(f5);
		RLegC.render(f5);
		LLegB.render(f5);
		LFoot.render(f5);
		LLegC.render(f5);
		LLegA.render(f5);
		RArmB.render(f5);
		RArmC.render(f5);
		LArmB.render(f5);
		RHand.render(f5);
		RArmA.render(f5);
		LArmA.render(f5);
		LArmC.render(f5);
		LHand.render(f5);
		RFinger1.render(f5);
		RFinger2.render(f5);
		RFinger3.render(f5);
		RFinger4.render(f5);
		RFinger5.render(f5);
		LFinger1.render(f5);
		LFinger2.render(f5);
		LFinger3.render(f5);
		LFinger4.render(f5);
		LFinger5.render(f5);
	}

	public void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		float radianF = 57.29578f;
		float RLegXRot = MathHelper.cos(f * 0.6662f + 3.141593f) * 0.8f * f1;
		float LLegXRot = MathHelper.cos(f * 0.6662f) * 0.8f * f1;
		Head.rotateAngleY = f3 / radianF;
		if (!hunched) {
			Head.rotationPointY = -8.0f;
			Head.rotationPointZ = -6.0f;
			Head.rotateAngleX = f4 / radianF;
			Neck.rotateAngleX = -34.0f / radianF;
			Neck.rotationPointY = -5.0f;
			Neck.rotationPointZ = -2.0f;
			Neck2.rotationPointY = -1.0f;
			Neck2.rotationPointZ = -6.0f;
			Chest.rotationPointY = -6.0f;
			Chest.rotationPointZ = -2.5f;
			Chest.rotateAngleX = 36.0f / radianF;
			Abdomen.rotateAngleX = 15.0f / radianF;
			LLegA.rotationPointZ = 3.0f;
			LArmA.rotationPointY = -4.0f;
			LArmA.rotationPointZ = -2.0f;
			TailA.rotationPointY = 9.5f;
			TailA.rotationPointZ = 6.0f;
		} else {
			Head.rotationPointY = 0.0f;
			Head.rotationPointZ = -11.0f;
			Head.rotateAngleX = (15.0f + f4) / radianF;
			Neck.rotateAngleX = -10.0f / radianF;
			Neck.rotationPointY = 2.0f;
			Neck.rotationPointZ = -6.0f;
			Neck2.rotationPointY = 9.0f;
			Neck2.rotationPointZ = -9.0f;
			Chest.rotationPointY = 1.0f;
			Chest.rotationPointZ = -7.5f;
			Chest.rotateAngleX = 60.0f / radianF;
			Abdomen.rotateAngleX = 75.0f / radianF;
			LLegA.rotationPointZ = 7.0f;
			LArmA.rotationPointY = 4.5f;
			LArmA.rotationPointZ = -6.0f;
			TailA.rotationPointY = 7.5f;
			TailA.rotationPointZ = 10.0f;
		}
		Nose.rotationPointY = Head.rotationPointY;
		Snout.rotationPointY = Head.rotationPointY;
		TeethU.rotationPointY = Head.rotationPointY;
		LEar.rotationPointY = Head.rotationPointY;
		REar.rotationPointY = Head.rotationPointY;
		TeethL.rotationPointY = Head.rotationPointY;
		Mouth.rotationPointY = Head.rotationPointY;
		SideburnL.rotationPointY = Head.rotationPointY;
		SideburnR.rotationPointY = Head.rotationPointY;
		Nose.rotationPointZ = Head.rotationPointZ;
		Snout.rotationPointZ = Head.rotationPointZ;
		TeethU.rotationPointZ = Head.rotationPointZ;
		LEar.rotationPointZ = Head.rotationPointZ;
		REar.rotationPointZ = Head.rotationPointZ;
		TeethL.rotationPointZ = Head.rotationPointZ;
		Mouth.rotationPointZ = Head.rotationPointZ;
		SideburnL.rotationPointZ = Head.rotationPointZ;
		SideburnR.rotationPointZ = Head.rotationPointZ;
		LArmB.rotationPointY = LArmA.rotationPointY;
		LArmC.rotationPointY = LArmA.rotationPointY;
		LHand.rotationPointY = LArmA.rotationPointY;
		LFinger1.rotationPointY = LArmA.rotationPointY;
		LFinger2.rotationPointY = LArmA.rotationPointY;
		LFinger3.rotationPointY = LArmA.rotationPointY;
		LFinger4.rotationPointY = LArmA.rotationPointY;
		LFinger5.rotationPointY = LArmA.rotationPointY;
		RArmA.rotationPointY = LArmA.rotationPointY;
		RArmB.rotationPointY = LArmA.rotationPointY;
		RArmC.rotationPointY = LArmA.rotationPointY;
		RHand.rotationPointY = LArmA.rotationPointY;
		RFinger1.rotationPointY = LArmA.rotationPointY;
		RFinger2.rotationPointY = LArmA.rotationPointY;
		RFinger3.rotationPointY = LArmA.rotationPointY;
		RFinger4.rotationPointY = LArmA.rotationPointY;
		RFinger5.rotationPointY = LArmA.rotationPointY;
		LArmB.rotationPointZ = LArmA.rotationPointZ;
		LArmC.rotationPointZ = LArmA.rotationPointZ;
		LHand.rotationPointZ = LArmA.rotationPointZ;
		LFinger1.rotationPointZ = LArmA.rotationPointZ;
		LFinger2.rotationPointZ = LArmA.rotationPointZ;
		LFinger3.rotationPointZ = LArmA.rotationPointZ;
		LFinger4.rotationPointZ = LArmA.rotationPointZ;
		LFinger5.rotationPointZ = LArmA.rotationPointZ;
		RArmA.rotationPointZ = LArmA.rotationPointZ;
		RArmB.rotationPointZ = LArmA.rotationPointZ;
		RArmC.rotationPointZ = LArmA.rotationPointZ;
		RHand.rotationPointZ = LArmA.rotationPointZ;
		RFinger1.rotationPointZ = LArmA.rotationPointZ;
		RFinger2.rotationPointZ = LArmA.rotationPointZ;
		RFinger3.rotationPointZ = LArmA.rotationPointZ;
		RFinger4.rotationPointZ = LArmA.rotationPointZ;
		RFinger5.rotationPointZ = LArmA.rotationPointZ;
		RLegA.rotationPointZ = LLegA.rotationPointZ;
		RLegB.rotationPointZ = LLegA.rotationPointZ;
		RLegC.rotationPointZ = LLegA.rotationPointZ;
		RFoot.rotationPointZ = LLegA.rotationPointZ;
		LLegB.rotationPointZ = LLegA.rotationPointZ;
		LLegC.rotationPointZ = LLegA.rotationPointZ;
		LFoot.rotationPointZ = LLegA.rotationPointZ;
		TailB.rotationPointY = TailA.rotationPointY;
		TailB.rotationPointZ = TailA.rotationPointZ;
		TailC.rotationPointY = TailA.rotationPointY;
		TailC.rotationPointZ = TailA.rotationPointZ;
		TailD.rotationPointY = TailA.rotationPointY;
		TailD.rotationPointZ = TailA.rotationPointZ;
		Nose.rotateAngleY = Head.rotateAngleY;
		Snout.rotateAngleY = Head.rotateAngleY;
		TeethU.rotateAngleY = Head.rotateAngleY;
		LEar.rotateAngleY = Head.rotateAngleY;
		REar.rotateAngleY = Head.rotateAngleY;
		TeethL.rotateAngleY = Head.rotateAngleY;
		Mouth.rotateAngleY = Head.rotateAngleY;
		TeethL.rotateAngleX = Head.rotateAngleX + 2.530727f;
		Mouth.rotateAngleX = Head.rotateAngleX + 2.530727f;
		SideburnL.rotateAngleX = -0.2094395f + Head.rotateAngleX;
		SideburnL.rotateAngleY = 0.418879f + Head.rotateAngleY;
		SideburnR.rotateAngleX = -0.2094395f + Head.rotateAngleX;
		SideburnR.rotateAngleY = -0.418879f + Head.rotateAngleY;
		Nose.rotateAngleX = 0.2792527f + Head.rotateAngleX;
		Snout.rotateAngleX = Head.rotateAngleX;
		TeethU.rotateAngleX = Head.rotateAngleX;
		LEar.rotateAngleX = Head.rotateAngleX;
		REar.rotateAngleX = Head.rotateAngleX;
		RLegA.rotateAngleX = -0.8126625f + RLegXRot;
		RLegB.rotateAngleX = -0.8445741f + RLegXRot;
		RLegC.rotateAngleX = -0.2860688f + RLegXRot;
		RFoot.rotateAngleX = RLegXRot;
		LLegA.rotateAngleX = -0.8126625f + LLegXRot;
		LLegB.rotateAngleX = -0.8445741f + LLegXRot;
		LLegC.rotateAngleX = -0.2860688f + LLegXRot;
		LFoot.rotateAngleX = LLegXRot;
		RArmA.rotateAngleZ = -(MathHelper.cos(f2 * 0.09f) * 0.05f) + 0.05f;
		LArmA.rotateAngleZ = MathHelper.cos(f2 * 0.09f) * 0.05f - 0.05f;
		RArmA.rotateAngleX = LLegXRot;
		LArmA.rotateAngleX = RLegXRot;
		RArmB.rotateAngleZ = 0.3490659f + RArmA.rotateAngleZ;
		LArmB.rotateAngleZ = -0.3490659f + LArmA.rotateAngleZ;
		RArmB.rotateAngleX = 0.2617994f + RArmA.rotateAngleX;
		LArmB.rotateAngleX = 0.2617994f + LArmA.rotateAngleX;
		RArmC.rotateAngleZ = RArmA.rotateAngleZ;
		LArmC.rotateAngleZ = LArmA.rotateAngleZ;
		RArmC.rotateAngleX = -0.3490659f + RArmA.rotateAngleX;
		LArmC.rotateAngleX = -0.3490659f + LArmA.rotateAngleX;
		RHand.rotateAngleZ = RArmA.rotateAngleZ;
		LHand.rotateAngleZ = LArmA.rotateAngleZ;
		RHand.rotateAngleX = RArmA.rotateAngleX;
		LHand.rotateAngleX = LArmA.rotateAngleX;
		RFinger1.rotateAngleX = RArmA.rotateAngleX;
		RFinger2.rotateAngleX = RArmA.rotateAngleX;
		RFinger3.rotateAngleX = RArmA.rotateAngleX;
		RFinger4.rotateAngleX = RArmA.rotateAngleX;
		RFinger5.rotateAngleX = RArmA.rotateAngleX;
		LFinger1.rotateAngleX = LArmA.rotateAngleX;
		LFinger2.rotateAngleX = LArmA.rotateAngleX;
		LFinger3.rotateAngleX = LArmA.rotateAngleX;
		LFinger4.rotateAngleX = LArmA.rotateAngleX;
		LFinger5.rotateAngleX = LArmA.rotateAngleX;
		RFinger1.rotateAngleZ = RArmA.rotateAngleZ;
		RFinger2.rotateAngleZ = RArmA.rotateAngleZ;
		RFinger3.rotateAngleZ = RArmA.rotateAngleZ;
		RFinger4.rotateAngleZ = RArmA.rotateAngleZ;
		RFinger5.rotateAngleZ = RArmA.rotateAngleZ;
		LFinger1.rotateAngleZ = LArmA.rotateAngleZ;
		LFinger2.rotateAngleZ = LArmA.rotateAngleZ;
		LFinger3.rotateAngleZ = LArmA.rotateAngleZ;
		LFinger4.rotateAngleZ = LArmA.rotateAngleZ;
		LFinger5.rotateAngleZ = LArmA.rotateAngleZ;
	}
}