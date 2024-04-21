package got.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class GOTModelWyvern extends ModelBase {
	private static final float RADIAN_F = 57.29578f;

	private final ModelRenderer back1;
	private final ModelRenderer tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer chest;
	private final ModelRenderer neckPlate3;
	private final ModelRenderer neck3;
	private final ModelRenderer neck2;
	private final ModelRenderer neck1;
	private final ModelRenderer rightUpLeg;
	private final ModelRenderer rightMidLeg;
	private final ModelRenderer rightLowLeg;
	private final ModelRenderer rightFoot;
	private final ModelRenderer leftUpLeg;
	private final ModelRenderer leftMidLeg;
	private final ModelRenderer leftLowLeg;
	private final ModelRenderer torso;
	private final ModelRenderer rightShoulder;
	private final ModelRenderer leftShoulder;
	private final ModelRenderer leftFoot;
	private final ModelRenderer rightToe3;
	private final ModelRenderer rightToe2;
	private final ModelRenderer rightToe1;
	private final ModelRenderer leftToe3;
	private final ModelRenderer leftToe2;
	private final ModelRenderer leftToe1;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer mouthrod;
	private final ModelRenderer helmetStrap1;
	private final ModelRenderer helmetStrap2;
	private final ModelRenderer leftEarSkin;
	private final ModelRenderer rightEarSkin;
	private final ModelRenderer rightFing1a;
	private final ModelRenderer rightFing2a;
	private final ModelRenderer rightFing3a;
	private final ModelRenderer leftFing3a;
	private final ModelRenderer leftFing2a;
	private final ModelRenderer leftFing1a;
	private final ModelRenderer leftLowArm;
	private final ModelRenderer rightLowArm;
	private final ModelRenderer rightUpArm;
	private final ModelRenderer leftUpArm;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWing;
	private final ModelRenderer mainHead;

	public GOTModelWyvern() {
		textureWidth = 128;
		textureHeight = 256;
		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0f, 0.0f, 0.0f);
		back1 = new ModelRenderer(this, 92, 0);
		back1.addBox(-3.0f, -2.0f, -12.0f, 6, 2, 12);
		back1.setRotationPoint(0.0f, 0.0f, 0.0f);
		tail1 = new ModelRenderer(this, 0, 22);
		tail1.addBox(-4.0f, 0.0f, 0.0f, 8, 8, 10);
		tail1.setRotationPoint(0.0f, 0.0f, 0.0f);
		tail.addChild(tail1);
		ModelRenderer back2 = new ModelRenderer(this, 100, 14);
		back2.addBox(-2.0f, -2.0f, 0.0f, 4, 2, 10);
		back2.setRotationPoint(0.0f, 0.0f, 0.0f);
		tail1.addChild(back2);
		tail2 = new ModelRenderer(this, 0, 40);
		tail2.addBox(-3.0f, 0.0f, 0.0f, 6, 6, 9);
		tail2.setRotationPoint(0.0f, 0.0f, 10.0f);
		tail1.addChild(tail2);
		ModelRenderer back3 = new ModelRenderer(this, 104, 26);
		back3.addBox(-1.5f, -2.0f, 0.0f, 3, 2, 9);
		back3.setRotationPoint(0.0f, 0.0f, 0.0f);
		tail2.addChild(back3);
		tail3 = new ModelRenderer(this, 0, 55);
		tail3.addBox(-2.0f, 0.0f, 0.0f, 4, 5, 8);
		tail3.setRotationPoint(0.0f, 0.0f, 8.0f);
		tail2.addChild(tail3);
		ModelRenderer back4 = new ModelRenderer(this, 108, 37);
		back4.addBox(-1.0f, -2.0f, 0.0f, 2, 2, 8);
		back4.setRotationPoint(0.0f, 0.0f, 0.0f);
		tail3.addChild(back4);
		tail4 = new ModelRenderer(this, 0, 68);
		tail4.addBox(-1.0f, 0.0f, 0.0f, 2, 5, 7);
		tail4.setRotationPoint(0.0f, -1.0f, 7.0f);
		tail3.addChild(tail4);
		tail5 = new ModelRenderer(this, 0, 80);
		tail5.addBox(-0.5f, 0.0f, 0.0f, 1, 3, 7);
		tail5.setRotationPoint(0.0f, 1.0f, 6.0f);
		tail4.addChild(tail5);
		chest = new ModelRenderer(this, 44, 0);
		chest.addBox(-4.5f, 2.7f, -13.0f, 9, 10, 4);
		chest.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(chest, -0.2602503f, 0.0f, 0.0f);
		neckPlate3 = new ModelRenderer(this, 112, 64);
		neckPlate3.addBox(-2.0f, -2.0f, -2.0f, 4, 2, 4);
		neckPlate3.setRotationPoint(0.0f, 0.0f, -12.0f);
		setRotation(neckPlate3, -0.669215f, 0.0f, 0.0f);
		neck3 = new ModelRenderer(this, 100, 113);
		neck3.addBox(-3.0f, 0.0f, -2.0f, 6, 7, 8);
		neck3.setRotationPoint(0.0f, 0.0f, -12.0f);
		setRotation(neck3, -0.669215f, 0.0f, 0.0f);
		mainHead = new ModelRenderer(this);
		mainHead.setRotationPoint(0.0f, 3.0f, -15.0f);
		neck2 = new ModelRenderer(this, 102, 99);
		neck2.addBox(-2.5f, -3.0f, -8.0f, 5, 6, 8);
		neck2.setRotationPoint(0.0f, 0.0f, 0.0f);
		mainHead.addChild(neck2);
		ModelRenderer neckplate2 = new ModelRenderer(this, 106, 54);
		neckplate2.addBox(-1.5f, -2.0f, -8.0f, 3, 2, 8);
		neckplate2.setRotationPoint(0.0f, -3.0f, 0.0f);
		neck2.addChild(neckplate2);
		neck1 = new ModelRenderer(this, 104, 85);
		neck1.addBox(-2.0f, -3.0f, -8.0f, 4, 6, 8);
		neck1.setRotationPoint(0.0f, -0.5f, -5.5f);
		neck2.addChild(neck1);
		ModelRenderer neckplate1 = new ModelRenderer(this, 80, 108);
		neckplate1.addBox(-1.0f, -2.0f, -8.0f, 2, 2, 8);
		neckplate1.setRotationPoint(0.0f, -3.0f, 0.0f);
		neck1.addChild(neckplate1);
		head = new ModelRenderer(this, 98, 70);
		head.addBox(-3.5f, -3.5f, -8.0f, 7, 7, 8);
		head.setRotationPoint(0.0f, 0.0f, -7.0f);
		neck1.addChild(head);
		ModelRenderer snout = new ModelRenderer(this, 72, 70);
		snout.addBox(-2.0f, -1.5f, -9.0f, 4, 3, 9);
		snout.setRotationPoint(0.0f, -1.5f, -8.0f);
		setRotation(snout, 2.0f / RADIAN_F, 0.0f, 0.0f);
		head.addChild(snout);
		ModelRenderer headplate = new ModelRenderer(this, 80, 118);
		headplate.addBox(-1.0f, -1.0f, -4.0f, 2, 2, 8);
		headplate.setRotationPoint(0.0f, -3.0f, -1.0f);
		setRotation(headplate, 10.0f / RADIAN_F, 0.0f, 0.0f);
		head.addChild(headplate);
		ModelRenderer beak = new ModelRenderer(this, 60, 85);
		beak.addBox(-1.5f, -2.5f, -1.5f, 3, 5, 3);
		beak.setRotationPoint(0.0f, 0.8f, -8.0f);
		setRotation(beak, -6.0f / RADIAN_F, 45.0f / RADIAN_F, -6.0f / RADIAN_F);
		snout.addChild(beak);
		ModelRenderer righteyesock = new ModelRenderer(this, 70, 108);
		righteyesock.addBox(0.0f, 0.0f, 0.0f, 1, 2, 4);
		righteyesock.setRotationPoint(-3.5f, -2.5f, -8.0f);
		head.addChild(righteyesock);
		ModelRenderer lefteyesock = new ModelRenderer(this, 70, 114);
		lefteyesock.addBox(0.0f, 0.0f, 0.0f, 1, 2, 4);
		lefteyesock.setRotationPoint(2.5f, -2.5f, -8.0f);
		head.addChild(lefteyesock);
		jaw = new ModelRenderer(this, 72, 82);
		jaw.addBox(-2.0f, -1.0f, -9.0f, 4, 2, 9);
		jaw.setRotationPoint(0.0f, 2.5f, -7.5f);
		setRotation(jaw, -10.0f / RADIAN_F, 0.0f, 0.0f);
		head.addChild(jaw);
		ModelRenderer leftupjaw = new ModelRenderer(this, 42, 93);
		leftupjaw.addBox(-1.0f, -1.0f, -6.5f, 2, 2, 13);
		leftupjaw.setRotationPoint(2.0f, 0.0f, -10.5f);
		setRotation(leftupjaw, -10.0f / RADIAN_F, 10.0f / RADIAN_F, 0.0f);
		head.addChild(leftupjaw);
		ModelRenderer rightupjaw = new ModelRenderer(this, 72, 93);
		rightupjaw.addBox(-1.0f, -1.0f, -6.5f, 2, 2, 13);
		rightupjaw.setRotationPoint(-2.0f, 0.0f, -10.5f);
		setRotation(rightupjaw, -10.0f / RADIAN_F, -10.0f / RADIAN_F, 0.0f);
		head.addChild(rightupjaw);
		mouthrod = new ModelRenderer(this, 104, 50);
		mouthrod.addBox(-5.0f, -1.0f, -1.0f, 10, 2, 2);
		mouthrod.setRotationPoint(0.0f, 1.0f, -8.0f);
		head.addChild(mouthrod);
		helmetStrap1 = new ModelRenderer(this, 32, 146);
		helmetStrap1.addBox(-4.0f, -2.0f, 0.0f, 8, 4, 1);
		helmetStrap1.setRotationPoint(0.0f, 2.0f, -7.5f);
		head.addChild(helmetStrap1);
		helmetStrap2 = new ModelRenderer(this, 32, 141);
		helmetStrap2.addBox(-4.0f, -2.0f, 0.0f, 8, 4, 1);
		helmetStrap2.setRotationPoint(0.0f, 2.0f, -3.5f);
		head.addChild(helmetStrap2);
		ModelRenderer controlrope1 = new ModelRenderer(this, 66, 43);
		controlrope1.addBox(0.0f, -2.0f, 0.0f, 0, 4, 23);
		controlrope1.setRotationPoint(4.5f, 1.0f, 0.0f);
		mouthrod.addChild(controlrope1);
		ModelRenderer controlrope2 = new ModelRenderer(this, 66, 43);
		controlrope2.addBox(0.0f, -2.0f, 0.0f, 0, 4, 23);
		controlrope2.setRotationPoint(-4.5f, 1.0f, 0.0f);
		mouthrod.addChild(controlrope2);
		rightEarSkin = new ModelRenderer(this, 112, 201);
		rightEarSkin.addBox(0.0f, -4.0f, 0.0f, 0, 8, 8);
		rightEarSkin.setRotationPoint(-3.0f, -0.5f, 0.0f);
		head.addChild(rightEarSkin);
		leftEarSkin = new ModelRenderer(this, 96, 201);
		leftEarSkin.addBox(0.0f, -4.0f, 0.0f, 0, 8, 8);
		leftEarSkin.setRotationPoint(3.0f, -0.5f, 0.0f);
		head.addChild(leftEarSkin);
		ModelRenderer rightspine1 = new ModelRenderer(this, 50, 141);
		rightspine1.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		rightspine1.setRotationPoint(0.0f, -2.0f, 0.0f);
		setRotation(rightspine1, 15.0f / RADIAN_F, 0.0f, 0.0f);
		rightEarSkin.addChild(rightspine1);
		ModelRenderer rightspine2 = new ModelRenderer(this, 50, 141);
		rightspine2.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		rightspine2.setRotationPoint(0.0f, 0.0f, 0.0f);
		rightEarSkin.addChild(rightspine2);
		ModelRenderer rightspine3 = new ModelRenderer(this, 50, 141);
		rightspine3.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		rightspine3.setRotationPoint(0.0f, 2.0f, 0.0f);
		setRotation(rightspine3, -15.0f / RADIAN_F, 0.0f, 0.0f);
		rightEarSkin.addChild(rightspine3);
		ModelRenderer leftspine1 = new ModelRenderer(this, 68, 141);
		leftspine1.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		leftspine1.setRotationPoint(0.0f, -2.0f, 0.0f);
		setRotation(leftspine1, 15.0f / RADIAN_F, 0.0f, 0.0f);
		leftEarSkin.addChild(leftspine1);
		ModelRenderer leftspine2 = new ModelRenderer(this, 68, 141);
		leftspine2.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		leftspine2.setRotationPoint(0.0f, 0.0f, 0.0f);
		leftEarSkin.addChild(leftspine2);
		ModelRenderer leftspine3 = new ModelRenderer(this, 68, 141);
		leftspine3.addBox(-0.5f, -1.0f, 0.0f, 1, 2, 8);
		leftspine3.setRotationPoint(0.0f, 2.0f, 0.0f);
		setRotation(leftspine3, -15.0f / RADIAN_F, 0.0f, 0.0f);
		leftEarSkin.addChild(leftspine3);
		torso = new ModelRenderer(this, 0, 0);
		torso.addBox(-5.0f, 0.0f, -12.0f, 10, 10, 12);
		torso.setRotationPoint(0.0f, 0.0f, 0.0f);
		ModelRenderer saddle = new ModelRenderer(this, 38, 70);
		saddle.addBox(-3.5f, -2.5f, -8.0f, 7, 3, 10);
		saddle.setRotationPoint(0.0f, 0.0f, 0.0f);
		rightShoulder = new ModelRenderer(this, 42, 83);
		rightShoulder.addBox(-6.0f, 1.0f, -12.5f, 4, 5, 5);
		rightShoulder.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(rightShoulder, -0.2617994f, 0.0f, 0.0f);
		leftShoulder = new ModelRenderer(this, 24, 83);
		leftShoulder.addBox(2.0f, 1.0f, -12.5f, 4, 5, 5);
		leftShoulder.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(leftShoulder, -0.2617994f, 0.0f, 0.0f);
		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(4.0f, 1.0f, -11.0f);
		leftUpArm = new ModelRenderer(this, 44, 14);
		leftUpArm.addBox(0.0f, -2.0f, -2.0f, 10, 4, 4);
		leftUpArm.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(leftUpArm, 0.0f, -10.0f / RADIAN_F, 0.0f);
		leftWing.addChild(leftUpArm);
		leftLowArm = new ModelRenderer(this, 72, 14);
		leftLowArm.addBox(0.0f, -2.0f, -2.0f, 10, 4, 4);
		leftLowArm.setRotationPoint(9.0f, 0.0f, 0.0f);
		setRotation(leftLowArm, 0.0f, 10.0f / RADIAN_F, 0.0f);
		leftUpArm.addChild(leftLowArm);
		leftFing1a = new ModelRenderer(this, 52, 30);
		leftFing1a.addBox(0.0f, 0.0f, -1.0f, 2, 15, 2);
		leftFing1a.setRotationPoint(9.0f, 1.0f, 0.0f);
		setRotation(leftFing1a, 90.0f / RADIAN_F, 70.0f / RADIAN_F, 0.0f);
		leftLowArm.addChild(leftFing1a);
		ModelRenderer leftfing1b = new ModelRenderer(this, 52, 47);
		leftfing1b.addBox(0.0f, 0.0f, -1.0f, 2, 10, 2);
		leftfing1b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(leftfing1b, 0.0f, 0.0f, 35.0f / RADIAN_F);
		leftFing1a.addChild(leftfing1b);
		leftFing2a = new ModelRenderer(this, 44, 30);
		leftFing2a.addBox(-1.0f, 0.0f, 0.0f, 2, 15, 2);
		leftFing2a.setRotationPoint(9.0f, 1.0f, 0.0f);
		setRotation(leftFing2a, 90.0f / RADIAN_F, 35.0f / RADIAN_F, 0.0f);
		leftLowArm.addChild(leftFing2a);
		ModelRenderer leftfing2b = new ModelRenderer(this, 44, 47);
		leftfing2b.addBox(-1.0f, 0.0f, 0.0f, 2, 10, 2);
		leftfing2b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(leftfing2b, 0.0f, 0.0f, 30.0f / RADIAN_F);
		leftFing2a.addChild(leftfing2b);
		leftFing3a = new ModelRenderer(this, 36, 30);
		leftFing3a.addBox(-1.0f, 0.0f, 1.0f, 2, 15, 2);
		leftFing3a.setRotationPoint(9.0f, 1.0f, 0.0f);
		setRotation(leftFing3a, 90.0f / RADIAN_F, -5.0f / RADIAN_F, 0.0f);
		leftLowArm.addChild(leftFing3a);
		ModelRenderer leftfing3b = new ModelRenderer(this, 36, 47);
		leftfing3b.addBox(-1.0f, 0.0f, 1.0f, 2, 10, 2);
		leftfing3b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(leftfing3b, 0.0f, 0.0f, 30.0f / RADIAN_F);
		leftFing3a.addChild(leftfing3b);
		ModelRenderer leftwingflap1 = new ModelRenderer(this, 74, 153);
		leftwingflap1.addBox(3.5f, -3.0f, 0.95f, 14, 24, 0);
		leftwingflap1.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(leftwingflap1, 0.0f, 0.0f, 70.0f / RADIAN_F);
		leftFing1a.addChild(leftwingflap1);
		ModelRenderer leftwingflap2 = new ModelRenderer(this, 36, 153);
		leftwingflap2.addBox(-7.0f, 1.05f, 1.05f, 19, 24, 0);
		leftwingflap2.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(leftwingflap2, 0.0f, 0.0f, 40.0f / RADIAN_F);
		leftFing2a.addChild(leftwingflap2);
		ModelRenderer leftwingflap3 = new ModelRenderer(this, 0, 153);
		leftwingflap3.addBox(-17.5f, 1.0f, 1.1f, 18, 24, 0);
		leftwingflap3.setRotationPoint(0.0f, 0.0f, 0.0f);
		leftFing3a.addChild(leftwingflap3);
		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-4.0f, 1.0f, -11.0f);
		rightUpArm = new ModelRenderer(this, 44, 22);
		rightUpArm.addBox(-10.0f, -2.0f, -2.0f, 10, 4, 4);
		rightUpArm.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(rightUpArm, 0.0f, 10.0f / RADIAN_F, 0.0f);
		rightWing.addChild(rightUpArm);
		rightLowArm = new ModelRenderer(this, 72, 22);
		rightLowArm.addBox(-10.0f, -2.0f, -2.0f, 10, 4, 4);
		rightLowArm.setRotationPoint(-9.0f, 0.0f, 0.0f);
		setRotation(rightLowArm, 0.0f, -10.0f / RADIAN_F, 0.0f);
		rightUpArm.addChild(rightLowArm);
		rightFing1a = new ModelRenderer(this, 36, 30);
		rightFing1a.addBox(-1.0f, 0.0f, -1.0f, 2, 15, 2);
		rightFing1a.setRotationPoint(-9.0f, 1.0f, -1.0f);
		setRotation(rightFing1a, 90.0f / RADIAN_F, -70.0f / RADIAN_F, 0.0f);
		rightLowArm.addChild(rightFing1a);
		ModelRenderer rightfing1b = new ModelRenderer(this, 36, 47);
		rightfing1b.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2);
		rightfing1b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(rightfing1b, 0.0f, 0.0f, -35.0f / RADIAN_F);
		rightFing1a.addChild(rightfing1b);
		ModelRenderer rightwingflap1 = new ModelRenderer(this, 74, 177);
		rightwingflap1.addBox(-17.5f, -3.0f, 0.95f, 14, 24, 0);
		rightwingflap1.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(rightwingflap1, 0.0f, 0.0f, -70.0f / RADIAN_F);
		rightFing1a.addChild(rightwingflap1);
		rightFing2a = new ModelRenderer(this, 44, 30);
		rightFing2a.addBox(-1.0f, 0.0f, 0.0f, 2, 15, 2);
		rightFing2a.setRotationPoint(-9.0f, 1.0f, 0.0f);
		setRotation(rightFing2a, 90.0f / RADIAN_F, -35.0f / RADIAN_F, 0.0f);
		rightLowArm.addChild(rightFing2a);
		ModelRenderer rightfing2b = new ModelRenderer(this, 44, 47);
		rightfing2b.addBox(-1.0f, 0.0f, 0.0f, 2, 10, 2);
		rightfing2b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(rightfing2b, 0.0f, 0.0f, -30.0f / RADIAN_F);
		rightFing2a.addChild(rightfing2b);
		ModelRenderer rightwingflap2 = new ModelRenderer(this, 36, 177);
		rightwingflap2.addBox(-19.0f, 1.05f, 1.05f, 19, 24, 0);
		rightwingflap2.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(rightwingflap2, 0.0f, 0.0f, -40.0f / RADIAN_F);
		rightFing2a.addChild(rightwingflap2);
		rightFing3a = new ModelRenderer(this, 52, 30);
		rightFing3a.addBox(-1.0f, 0.0f, 1.0f, 2, 15, 2);
		rightFing3a.setRotationPoint(-9.0f, 1.0f, 0.0f);
		setRotation(rightFing3a, 90.0f / RADIAN_F, 5.0f / RADIAN_F, 0.0f);
		rightLowArm.addChild(rightFing3a);
		ModelRenderer rightfing3b = new ModelRenderer(this, 52, 47);
		rightfing3b.addBox(-1.0f, 0.0f, 1.0f, 2, 10, 2);
		rightfing3b.setRotationPoint(0.0f, 14.0f, 0.0f);
		setRotation(rightfing3b, 0.0f, 0.0f, -30.0f / RADIAN_F);
		rightFing3a.addChild(rightfing3b);
		ModelRenderer rightwingflap3 = new ModelRenderer(this, 0, 177);
		rightwingflap3.addBox(-0.5f, 1.0f, 1.1f, 18, 24, 0);
		rightwingflap3.setRotationPoint(0.0f, 0.0f, 0.0f);
		rightFing3a.addChild(rightwingflap3);
		leftUpLeg = new ModelRenderer(this, 0, 111);
		leftUpLeg.addBox(-2.0f, -3.0f, -3.0f, 4, 10, 7);
		leftUpLeg.setRotationPoint(5.0f, 6.0f, -5.0f);
		setRotation(leftUpLeg, -25.0f / RADIAN_F, 0.0f, 0.0f);
		leftMidLeg = new ModelRenderer(this, 0, 102);
		leftMidLeg.addBox(-1.5f, -2.0f, 0.0f, 3, 4, 5);
		leftMidLeg.setRotationPoint(0.0f, 5.0f, 4.0f);
		leftUpLeg.addChild(leftMidLeg);
		leftLowLeg = new ModelRenderer(this, 0, 91);
		leftLowLeg.addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3);
		leftLowLeg.setRotationPoint(0.0f, 2.0f, 3.5f);
		leftMidLeg.addChild(leftLowLeg);
		leftFoot = new ModelRenderer(this, 44, 121);
		leftFoot.addBox(-2.0f, -1.0f, -3.0f, 4, 3, 4);
		leftFoot.setRotationPoint(0.0f, 7.0f, 0.5f);
		setRotation(leftFoot, 25.0f / RADIAN_F, 0.0f, 0.0f);
		leftLowLeg.addChild(leftFoot);
		leftToe1 = new ModelRenderer(this, 96, 35);
		leftToe1.addBox(-0.5f, -1.0f, -3.0f, 1, 2, 3);
		leftToe1.setRotationPoint(-1.5f, 1.0f, -3.0f);
		leftFoot.addChild(leftToe1);
		leftToe3 = new ModelRenderer(this, 96, 30);
		leftToe3.addBox(-0.5f, -1.0f, -3.0f, 1, 2, 3);
		leftToe3.setRotationPoint(1.5f, 1.0f, -3.0f);
		leftFoot.addChild(leftToe3);
		leftToe2 = new ModelRenderer(this, 84, 30);
		leftToe2.addBox(-1.0f, -1.5f, -4.0f, 2, 3, 4);
		leftToe2.setRotationPoint(0.0f, 0.5f, -3.0f);
		leftFoot.addChild(leftToe2);
		ModelRenderer leftclaw1 = new ModelRenderer(this, 100, 26);
		leftclaw1.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1);
		leftclaw1.setRotationPoint(0.5f, -0.5f, -2.5f);
		setRotation(leftclaw1, -25.0f / RADIAN_F, 0.0f, 0.0f);
		leftToe1.addChild(leftclaw1);
		ModelRenderer leftclaw2 = new ModelRenderer(this, 100, 26);
		leftclaw2.addBox(-0.5f, 0.0f, -0.5f, 1, 3, 1);
		leftclaw2.setRotationPoint(0.0f, -1.0f, -3.5f);
		setRotation(leftclaw2, -25.0f / RADIAN_F, 0.0f, 0.0f);
		leftToe2.addChild(leftclaw2);
		ModelRenderer leftclaw3 = new ModelRenderer(this, 100, 26);
		leftclaw3.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1);
		leftclaw3.setRotationPoint(-0.5f, -0.5f, -2.5f);
		setRotation(leftclaw3, -25.0f / RADIAN_F, 0.0f, 0.0f);
		leftToe3.addChild(leftclaw3);
		rightUpLeg = new ModelRenderer(this, 0, 111);
		rightUpLeg.addBox(-2.0f, -3.0f, -3.0f, 4, 10, 7);
		rightUpLeg.setRotationPoint(-5.0f, 6.0f, -5.0f);
		setRotation(rightUpLeg, -25.0f / RADIAN_F, 0.0f, 0.0f);
		rightMidLeg = new ModelRenderer(this, 0, 102);
		rightMidLeg.addBox(-1.5f, -2.0f, 0.0f, 3, 4, 5);
		rightMidLeg.setRotationPoint(0.0f, 5.0f, 4.0f);
		rightUpLeg.addChild(rightMidLeg);
		rightLowLeg = new ModelRenderer(this, 0, 91);
		rightLowLeg.addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3);
		rightLowLeg.setRotationPoint(0.0f, 2.0f, 3.5f);
		rightMidLeg.addChild(rightLowLeg);
		rightFoot = new ModelRenderer(this, 44, 121);
		rightFoot.addBox(-2.0f, -1.0f, -3.0f, 4, 3, 4);
		rightFoot.setRotationPoint(0.0f, 7.0f, 0.5f);
		setRotation(rightFoot, 25.0f / RADIAN_F, 0.0f, 0.0f);
		rightLowLeg.addChild(rightFoot);
		rightToe1 = new ModelRenderer(this, 96, 35);
		rightToe1.addBox(-0.5f, -1.0f, -3.0f, 1, 2, 3);
		rightToe1.setRotationPoint(-1.5f, 1.0f, -3.0f);
		rightFoot.addChild(rightToe1);
		rightToe3 = new ModelRenderer(this, 96, 30);
		rightToe3.addBox(-0.5f, -1.0f, -3.0f, 1, 2, 3);
		rightToe3.setRotationPoint(1.5f, 1.0f, -3.0f);
		rightFoot.addChild(rightToe3);
		rightToe2 = new ModelRenderer(this, 84, 30);
		rightToe2.addBox(-1.0f, -1.5f, -4.0f, 2, 3, 4);
		rightToe2.setRotationPoint(0.0f, 0.5f, -3.0f);
		rightFoot.addChild(rightToe2);
		ModelRenderer rightclaw1 = new ModelRenderer(this, 100, 26);
		rightclaw1.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1);
		rightclaw1.setRotationPoint(0.5f, -0.5f, -2.5f);
		setRotation(rightclaw1, -25.0f / RADIAN_F, 0.0f, 0.0f);
		rightToe1.addChild(rightclaw1);
		ModelRenderer rightclaw2 = new ModelRenderer(this, 100, 26);
		rightclaw2.addBox(-0.5f, 0.0f, -0.5f, 1, 3, 1);
		rightclaw2.setRotationPoint(0.0f, -1.0f, -3.5f);
		setRotation(rightclaw2, -25.0f / RADIAN_F, 0.0f, 0.0f);
		rightToe2.addChild(rightclaw2);
		ModelRenderer rightclaw3 = new ModelRenderer(this, 100, 26);
		rightclaw3.addBox(-0.5f, 0.0f, -0.5f, 1, 2, 1);
		rightclaw3.setRotationPoint(-0.5f, -0.5f, -2.5f);
		setRotation(rightclaw3, -25.0f / RADIAN_F, 0.0f, 0.0f);
		rightToe3.addChild(rightclaw3);
		ModelRenderer storage = new ModelRenderer(this, 28, 59);
		storage.addBox(-5.0f, -4.5f, 1.5f, 10, 5, 6);
		storage.setRotationPoint(0.0f, 0.0f, 0.0f);
		setRotation(storage, -0.2268928f, 0.0f, 0.0f);
		ModelRenderer chestbelt = new ModelRenderer(this, 0, 201);
		chestbelt.addBox(-5.5f, -0.5f, -9.0f, 11, 11, 2);
		chestbelt.setRotationPoint(0.0f, 0.0f, 0.0f);
		ModelRenderer stomachbelt = new ModelRenderer(this, 0, 201);
		stomachbelt.addBox(-5.5f, -0.5f, -3.0f, 11, 11, 2);
		stomachbelt.setRotationPoint(0.0f, 0.0f, 0.0f);
	}

	private static float realAngle(float origAngle) {
		return origAngle % 360.0f;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f3, f4);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0f, 0.0f, 0.0f);
		back1.render(f5);
		tail.render(f5);
		chest.render(f5);
		leftWing.render(f5);
		rightWing.render(f5);
		rightShoulder.render(f5);
		leftShoulder.render(f5);
		neckPlate3.render(f5);
		neck3.render(f5);
		torso.render(f5);
		mouthrod.isHidden = true;
		helmetStrap1.isHidden = true;
		helmetStrap2.isHidden = true;
		mainHead.render(f5);
		leftUpLeg.render(f5);
		rightUpLeg.render(f5);
		GL11.glPopMatrix();
	}

	private static void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	private void setRotationAngles(float f, float f1, float f3, float f4) {
		float f31 = f3;
		float RLegXRot = MathHelper.cos(f * 0.6662f + 3.141593f) * 0.8f * f1;
		float LLegXRot = MathHelper.cos(f * 0.6662f) * 0.8f * f1;
		f31 = realAngle(f31);
		float f10 = 60.0f;
		if (f31 > f10) {
			f31 = f10;
		}
		if (f31 < -f10) {
			f31 = -f10;
		}
		neck2.rotateAngleX = -66.0f / RADIAN_F + f4 / 3.0f / RADIAN_F;
		neck1.rotateAngleX = 30.0f / RADIAN_F + f4 * 2.0f / 3.0f / RADIAN_F;
		head.rotateAngleX = 45.0f / RADIAN_F;
		neck2.rotateAngleY = f31 * 2.0f / 3.0f / RADIAN_F;
		neck1.rotateAngleY = f31 / 3.0f / RADIAN_F;
		head.rotateAngleY = 0.0f;
		head.rotateAngleZ = 0.0f;
		tail1.rotateAngleX = -19.0f / RADIAN_F;
		tail2.rotateAngleX = -16.0f / RADIAN_F;
		tail3.rotateAngleX = 7.0f / RADIAN_F;
		tail4.rotateAngleX = 11.0f / RADIAN_F;
		tail5.rotateAngleX = 8.0f / RADIAN_F;
		float t = f / 2.0f;
		float A = 0.15f;
		float w = 0.9f;
		float k = 0.6f;
		int i = 0;
		tail1.rotateAngleY = A * MathHelper.sin(w * t - k * i);
		i++;
		tail2.rotateAngleY = A * MathHelper.sin(w * t - k * i);
		i++;
		tail3.rotateAngleY = A * MathHelper.sin(w * t - k * i);
		i++;
		tail4.rotateAngleY = A * MathHelper.sin(w * t - k * i);
		i++;
		tail5.rotateAngleY = A * MathHelper.sin(w * t - k * i);
		leftLowArm.rotateAngleZ = 0.0f;
		leftFing1a.rotateAngleZ = 0.0f;
		leftFing2a.rotateAngleZ = 0.0f;
		rightLowArm.rotateAngleZ = 0.0f;
		rightFing1a.rotateAngleZ = 0.0f;
		rightFing2a.rotateAngleZ = 0.0f;
		leftUpArm.rotateAngleZ = 30.0f / RADIAN_F;
		leftUpArm.rotateAngleY = -60.0f / RADIAN_F + LLegXRot / 5.0f;
		leftLowArm.rotateAngleY = 105.0f / RADIAN_F;
		leftFing1a.rotateAngleY = -20.0f / RADIAN_F;
		leftFing2a.rotateAngleY = -26.0f / RADIAN_F;
		leftFing3a.rotateAngleY = -32.0f / RADIAN_F;
		rightUpArm.rotateAngleY = 60.0f / RADIAN_F - RLegXRot / 5.0f;
		rightUpArm.rotateAngleZ = -30.0f / RADIAN_F;
		rightLowArm.rotateAngleY = -105.0f / RADIAN_F;
		rightFing1a.rotateAngleY = 16.0f / RADIAN_F;
		rightFing2a.rotateAngleY = 26.0f / RADIAN_F;
		rightFing3a.rotateAngleY = 32.0f / RADIAN_F;
		leftUpLeg.rotateAngleX = -25.0f / RADIAN_F + LLegXRot;
		rightUpLeg.rotateAngleX = -25.0f / RADIAN_F + RLegXRot;
		leftMidLeg.rotateAngleX = 0.0f;
		leftLowLeg.rotateAngleX = 0.0f;
		leftFoot.rotateAngleX = 25.0f / RADIAN_F - LLegXRot;
		leftToe1.rotateAngleX = LLegXRot;
		leftToe2.rotateAngleX = LLegXRot;
		leftToe3.rotateAngleX = LLegXRot;
		rightMidLeg.rotateAngleX = 0.0f;
		rightLowLeg.rotateAngleX = 0.0f;
		rightFoot.rotateAngleX = 25.0f / RADIAN_F - RLegXRot;
		rightToe1.rotateAngleX = RLegXRot;
		rightToe2.rotateAngleX = RLegXRot;
		rightToe3.rotateAngleX = RLegXRot;
		jaw.rotateAngleX = -10.0f / RADIAN_F;
		leftEarSkin.rotateAngleY = 0.0f;
		rightEarSkin.rotateAngleY = 0.0f;
	}
}