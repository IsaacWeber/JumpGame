/*      */ package jumpgame;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.math.BigInteger;
/*      */ import java.util.Random;
/*      */ import javax.sound.sampled.AudioInputStream;
/*      */ import javax.sound.sampled.AudioSystem;
/*      */ import javax.sound.sampled.Clip;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JumpGame
/*      */   extends JPanel
/*      */   implements KeyListener, MouseListener
/*      */ {
/*      */   private static final int UPDATE_SPEED = 80;
/*   34 */   private static final Font GAME_OVER_FONT = new Font("Dialog", 3, 50);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   39 */   private static final Color GAME_OVER_COLOR = new Color(255, 141, 38);
/*      */   
/*      */   private static final String GAME_OVER_PHRASE = "SE FUDEU!";
/*   42 */   private static final Font GAME_PAUSED_STR_FONT = new Font("Dialog", 3, 40);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   47 */   private static final Color GAME_PAUSED_STR_COLOR = new Color(0, 0, 0);
/*      */ 
/*      */   
/*      */   private static final String GAME_PAUSED_STRING = "JOGO PAUSADO";
/*      */ 
/*      */   
/*   53 */   private static final Color SCORE_COLOR = new Color(255, 0, 0);
/*   54 */   private static final Font SCORE_FONT = new Font("Dialog", 3, 25);
/*      */ 
/*      */ 
/*      */   
/*   58 */   private BigInteger score = new BigInteger("0");
/*      */   
/*      */   private Thread verifyScore;
/*   61 */   private Color bgColor = new Color(238, 223, 115);
/*      */   private boolean gameOver;
/*      */   private boolean gameInit;
/*      */   private Random rand;
/*      */   private boolean gamePaused;
/*      */   private static final int FIRST_JUMP_TIME = 450;
/*   67 */   private int jumpTime = 450;
/*      */   
/*      */   private Thread soundJump;
/*      */   
/*      */   private Thread makePanelLoop;
/*      */   
/*      */   private Thread entranceSound;
/*      */   private Thread laughSound;
/*      */   private Thread gameOverSound;
/*      */   private boolean laughGoinOn;
/*      */   private JFrame entranceFrame;
/*      */   private Thread gameTimeRan;
/*      */   private static final int CLOUD_SPEED = 2;
/*      */   private static final int CLOUD_HEIGHT = 100;
/*      */   private static final int CLOUD_LENGTH = 200;
/*   82 */   private Color cloudColor = Color.WHITE;
/*      */ 
/*      */   
/*      */   private int xC1;
/*      */ 
/*      */   
/*      */   private int passC1;
/*      */ 
/*      */   
/*      */   private int xC2;
/*      */   
/*      */   private int passC2;
/*      */   
/*   95 */   private Color bodyColor = new Color(0, 0, 0);
/*   96 */   private Color bigEyeColor = new Color(255, 255, 255);
/*   97 */   private Color smallEyeColor = new Color(0, 0, 0);
/*   98 */   private Color mouthColor = new Color(255, 255, 255);
/*      */   private Polygon body;
/*      */   private int baseX1;
/*      */   private int baseX2;
/*      */   private int headX;
/*      */   private int baseY1;
/*      */   private int baseY2;
/*      */   private int headY;
/*      */   private int bigEyeX;
/*      */   private int bigEyeY;
/*      */   private int beDd;
/*      */   private int beUd;
/*      */   private int smallEyeX;
/*  111 */   private Color dustColor = new Color(235, 228, 112); private int smallEyeY; private int seDd; private int seUd; private int mouthX; private int mouthY; private int mouthDd; private int mouthUd; private boolean playerAssigned; private int xDust; private int yDust; private int dustWidth; private int dustHeight; private int ballsAmount;
/*      */   private static final int DUST_RED = 235;
/*      */   private static final int DUST_GREEN = 228;
/*      */   private static final int DUST_BLUE = 112;
/*  115 */   private int dustCRed = this.dustColor.getRed(); private int dustCGreen = this.dustColor
/*  116 */     .getGreen();
/*  117 */   private int dustCBlue = this.dustColor.getBlue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Thread manageBlinkDust;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   private Color floorColor = new Color(222, 177, 22); private int xf; private int yf; private int wf; private int hf; private int xWave1; private int yWave1; private int wWave1; private int hWave1; private int xWave2;
/*      */   private int yWave2;
/*      */   private int wWave2;
/*      */   private int hWave2;
/*  132 */   private int waveSpeed = 4; private int wavePass1; private int wavePass2;
/*  133 */   private int distWaves = 200;
/*      */ 
/*      */   
/*      */   private boolean floorAssigned;
/*      */ 
/*      */   
/*      */   private boolean isJumping;
/*      */ 
/*      */   
/*      */   private Thread manageJump;
/*      */   
/*      */   private static final int FIRST_OBJECT_SPEED = 5;
/*      */   
/*  146 */   private int objectSpeed = 5;
/*      */   
/*      */   private Thread turnSpeedInc;
/*      */   private boolean speedIncreased;
/*  150 */   private static final Color SPEED_TXT_COLOR = new Color(163, 208, 23);
/*  151 */   private static final Color SPEED_ARROW_COLOR = new Color(181, 230, 29);
/*  152 */   private Color speedIncTxtColor = SPEED_TXT_COLOR;
/*  153 */   private Color speedIncArrowColor = SPEED_ARROW_COLOR; private static int currentObj1; private static int currentObj2; private static int currentObj3; private boolean dimAssigned1; private boolean dimAssigned2; private boolean dimAssigned3; private boolean fTime1; private boolean fTime2;
/*      */   private boolean fTime3;
/*      */   private int dist1;
/*      */   private int dist2;
/*      */   private Thread verifyColide;
/*      */   private Thread waitEnd;
/*      */   private Thread waitOnceMore;
/*      */   private int xObj1;
/*      */   private int passObj1;
/*      */   private int obj1Length;
/*      */   private int obj1Height;
/*      */   private boolean draw1;
/*  165 */   private Color obj1Color = new Color(234, 193, 53); private int xObj2;
/*      */   private int passObj2;
/*      */   private int obj2Length;
/*      */   private int obj2Height;
/*      */   private boolean draw2;
/*  170 */   private Color obj2Color = new Color(234, 193, 53);
/*      */   private int xObj3;
/*      */   private int passObj3;
/*      */   private int obj3Length;
/*      */   private int obj3Height;
/*      */   private boolean draw3;
/*  176 */   private Color obj3Color = new Color(234, 193, 53);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JumpGame() {
/*  183 */     configFrame();
/*  184 */     prepareStyle();
/*  185 */     configClouds();
/*  186 */     configPlayer();
/*  187 */     this.rand = new Random();
/*  188 */     configObjects();
/*      */     
/*  190 */     setLKF("nimbus");
/*  191 */     SwingUtilities.updateComponentTreeUI(this);
/*      */     
/*  193 */     addMouseListener(this);
/*      */     
/*  195 */     this.makePanelLoop = new Thread(new MakePanelLoop());
/*  196 */     this.makePanelLoop.start();
/*      */     
/*  198 */     this.gameTimeRan = new Thread(new GameTimeRan());
/*  199 */     this.gameTimeRan.start();
/*      */     
/*  201 */     this.gameInit = true;
/*      */     
/*  203 */     this.entranceSound = new Thread(new EntranceSound());
/*  204 */     this.entranceSound.start();
/*      */     
/*  206 */     showControls();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareStyle() {
/*  218 */     setBackground(this.bgColor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configClouds() {
/*  225 */     this.xC1 = 50;
/*  226 */     this.xC2 = 600;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configObjects() {
/*  233 */     chooseNextObj(1);
/*  234 */     chooseNextObj(2);
/*  235 */     chooseNextObj(3);
/*      */     
/*  237 */     chooseDist1();
/*  238 */     chooseDist2();
/*      */     
/*  240 */     this.fTime1 = this.fTime2 = this.fTime3 = true;
/*  241 */     this.dimAssigned1 = this.dimAssigned2 = this.dimAssigned3 = false;
/*      */     
/*  243 */     this.draw1 = this.draw2 = this.draw3 = true;
/*  244 */     this.passObj1 = this.passObj2 = this.passObj3 = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void chooseNextObj(int pos) {
/*  252 */     switch (pos) {
/*      */       case 1:
/*  254 */         currentObj1 = 1 + this.rand.nextInt(2);
/*      */         break;
/*      */       case 2:
/*  257 */         currentObj2 = 1 + this.rand.nextInt(2);
/*      */         break;
/*      */       case 3:
/*  260 */         currentObj3 = 1 + this.rand.nextInt(2);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void chooseDist1() {
/*  270 */     if (this.playerAssigned) {
/*  271 */       this.dist1 = 5 * (this.body.getBounds()).width + this.rand.nextInt(601);
/*      */     } else {
/*  273 */       this.dist1 = 200 + this.rand.nextInt(601);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void chooseDist2() {
/*  280 */     if (this.playerAssigned) {
/*  281 */       this.dist2 = 5 * (this.body.getBounds()).width + this.rand.nextInt(601);
/*      */     } else {
/*  283 */       this.dist2 = 200 + this.rand.nextInt(601);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configPlayer() {
/*  291 */     this.body = new Polygon();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void paintComponent(Graphics g) {
/*  298 */     super.paintComponent(g);
/*      */ 
/*      */     
/*  301 */     g.setColor(this.cloudColor);
/*      */     
/*      */     int adv;
/*  304 */     for (adv = 0; adv < 3; adv++) {
/*  305 */       g.fillOval(this.xC1 + this.passC1 + 170 * adv, 20, 200, 100);
/*      */ 
/*      */ 
/*      */       
/*  309 */       if (this.xC1 + this.passC1 + 170 * adv <= -540) {
/*      */ 
/*      */         
/*  312 */         this.passC1 = 0;
/*  313 */         this.xC1 = getWidth();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  320 */     for (adv = 1; adv <= 3; adv++) {
/*  321 */       g.fillOval(this.xC2 + this.passC2 + 170 * adv, 20, 200, 100);
/*      */ 
/*      */ 
/*      */       
/*  325 */       if (this.xC2 + this.passC2 + 170 * adv <= -540) {
/*      */ 
/*      */         
/*  328 */         this.passC2 = 0;
/*  329 */         this.xC2 = getWidth();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  336 */     g.setFont(SCORE_FONT);
/*  337 */     g.setColor(SCORE_COLOR);
/*      */     
/*  339 */     if (!this.gameOver) {
/*      */       
/*  341 */       g.drawString("pontos: " + this.score.toString(), 
/*  342 */           getWidth() - ("pontos: " + this.score.toString()).length() * 15, 50);
/*      */     }
/*      */     else {
/*      */       
/*  346 */       g.drawString("pontos: " + this.score.toString(), 
/*  347 */           getWidth() / 2 - ("pontos: " + this.score.toString()).length() * 10, 
/*  348 */           getHeight() / 2);
/*      */     } 
/*      */ 
/*      */     
/*  352 */     g.setColor(this.floorColor);
/*      */     
/*  354 */     if (!this.floorAssigned) {
/*      */ 
/*      */       
/*  357 */       this.wf = getWidth() + 50;
/*  358 */       this.hf = getHeight() / 10;
/*      */       
/*  360 */       this.xf = -10;
/*  361 */       this.yf = getHeight() - this.hf;
/*      */ 
/*      */       
/*  364 */       this.xWave1 = this.xf + 100;
/*  365 */       this.yWave1 = this.yf - 10;
/*  366 */       this.wWave1 = 500;
/*  367 */       this.hWave1 = this.hf;
/*      */ 
/*      */       
/*  370 */       this.xWave2 = this.xWave1 + this.wWave1 + this.distWaves;
/*  371 */       this.yWave2 = this.yWave1;
/*  372 */       this.wWave2 = this.wWave1;
/*  373 */       this.hWave2 = this.hWave1;
/*      */       
/*  375 */       this.floorAssigned = true;
/*      */     } 
/*      */ 
/*      */     
/*  379 */     g.fillOval(this.xWave1 + this.wavePass1, this.yWave1, this.wWave1, this.hWave1);
/*  380 */     g.fillOval(this.xWave2 + this.wavePass2, this.yWave2, this.wWave2, this.hWave2);
/*  381 */     g.fillRect(this.xf, this.yf, this.wf, this.hf);
/*      */ 
/*      */ 
/*      */     
/*  385 */     if (this.xWave1 + this.wavePass1 + this.wWave1 <= -this.wWave1) {
/*      */       
/*  387 */       this.xWave1 = getWidth();
/*  388 */       this.wavePass1 = 0;
/*      */     } 
/*      */ 
/*      */     
/*  392 */     if (this.xWave2 + this.wavePass2 + this.wWave2 <= -this.wWave2) {
/*      */       
/*  394 */       changeRandDist();
/*  395 */       this.xWave2 = getWidth() + this.distWaves;
/*  396 */       this.wavePass2 = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  450 */     g.setColor(this.bodyColor);
/*      */     
/*  452 */     if (!this.playerAssigned) {
/*      */ 
/*      */       
/*  455 */       this.baseX1 = 200;
/*  456 */       this.baseX2 = 240;
/*  457 */       this.baseY1 = getHeight() - 28;
/*  458 */       this.baseY2 = getHeight() - 28;
/*      */       
/*  460 */       this.headX = this.baseX1 + 30;
/*  461 */       this.headY = getHeight() - 108;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  467 */     this.body = new Polygon();
/*  468 */     this.body.addPoint(this.baseX1, this.baseY1);
/*  469 */     this.body.addPoint(this.headX, this.headY);
/*  470 */     this.body.addPoint(this.baseX2, this.baseY2);
/*      */     
/*  472 */     g.fillPolygon(this.body);
/*      */ 
/*      */     
/*  475 */     g.setColor(this.bigEyeColor);
/*      */     
/*  477 */     if (!this.playerAssigned) {
/*      */       
/*  479 */       this.bigEyeX = this.baseX1 + 22;
/*  480 */       this.bigEyeY = this.headY + 35;
/*  481 */       this.beDd = 10;
/*  482 */       this.beUd = 10;
/*      */     } 
/*      */ 
/*      */     
/*  486 */     g.fillOval(this.bigEyeX, this.bigEyeY, this.beDd, this.beUd);
/*      */ 
/*      */     
/*  489 */     g.setColor(this.smallEyeColor);
/*      */     
/*  491 */     if (!this.playerAssigned) {
/*      */       
/*  493 */       this.smallEyeX = this.baseX1 + 26;
/*  494 */       this.smallEyeY = this.headY + 38;
/*  495 */       this.seDd = 5;
/*  496 */       this.seUd = 5;
/*      */     } 
/*      */ 
/*      */     
/*  500 */     g.fillOval(this.smallEyeX, this.smallEyeY, this.seDd, this.seUd);
/*      */ 
/*      */ 
/*      */     
/*  504 */     g.setColor(this.mouthColor);
/*      */     
/*  506 */     if (!this.playerAssigned) {
/*      */       
/*  508 */       this.mouthX = this.baseX1 + 25;
/*  509 */       this.mouthY = this.headY + 55;
/*  510 */       this.mouthDd = 12;
/*  511 */       this.mouthUd = 3;
/*      */     } 
/*      */ 
/*      */     
/*  515 */     g.fillOval(this.mouthX, this.mouthY, this.mouthDd, this.mouthUd);
/*      */ 
/*      */     
/*  518 */     g.setColor(this.dustColor);
/*      */     
/*  520 */     if (!this.playerAssigned) {
/*      */       
/*  522 */       this.dustWidth = 10;
/*  523 */       this.dustHeight = 10;
/*      */       
/*  525 */       this.xDust = this.baseX1 - this.dustWidth + 2;
/*  526 */       this.yDust = this.baseY1 - this.dustHeight;
/*      */       
/*  528 */       this.playerAssigned = true;
/*      */       
/*  530 */       this.manageBlinkDust = new Thread(new ManageBlinkDust());
/*  531 */       this.manageBlinkDust.start();
/*      */     } 
/*      */ 
/*      */     
/*  535 */     if (!this.isJumping) {
/*      */ 
/*      */       
/*  538 */       g.fillOval(this.xDust, this.yDust, this.dustWidth, this.dustHeight);
/*  539 */       g.fillOval(this.xDust - this.dustWidth + 2, this.yDust, this.dustWidth, this.dustHeight);
/*  540 */       g.fillOval(this.xDust - (this.dustWidth - 2) / 2, this.yDust - this.dustHeight + 2, this.dustWidth, this.dustHeight);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  548 */     if (!this.dimAssigned1) {
/*      */       
/*  550 */       if (this.fTime1) {
/*  551 */         this.xObj1 = getWidth() + 50;
/*  552 */         this.fTime1 = false;
/*      */       } 
/*      */       
/*  555 */       this.dimAssigned1 = true;
/*      */     } 
/*      */ 
/*      */     
/*  559 */     g.setColor(this.obj1Color);
/*      */     
/*  561 */     if (this.draw1)
/*      */     {
/*  563 */       switch (currentObj1) {
/*      */ 
/*      */         
/*      */         case 1:
/*  567 */           this.obj1Length = 50;
/*  568 */           this.obj1Height = 50;
/*      */           
/*  570 */           g.fillRect(this.xObj1 + this.passObj1, getHeight() - this.obj1Height + 30, this.obj1Length, this.obj1Height);
/*      */           
/*  572 */           g.setColor(Color.BLACK);
/*  573 */           g.drawRect(this.xObj1 + this.passObj1, getHeight() - this.obj1Height + 30, this.obj1Length + 1, this.obj1Height + 1);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  579 */           this.obj1Length = 50;
/*  580 */           this.obj1Height = 50;
/*      */           
/*  582 */           g.fillOval(this.xObj1 + this.passObj1, getHeight() - this.obj1Height + 30, this.obj1Length, this.obj1Height);
/*      */           
/*  584 */           g.setColor(Color.BLACK);
/*  585 */           g.drawOval(this.xObj1 + this.passObj1, getHeight() - this.obj1Height + 30, this.obj1Length + 1, this.obj1Height + 1);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  595 */     if (this.xObj1 + this.passObj1 <= -this.obj1Length) {
/*      */       
/*  597 */       this.draw1 = false;
/*      */       
/*  599 */       chooseNextObj(1);
/*      */       
/*  601 */       this.passObj1 = 0;
/*      */       
/*  603 */       if (this.verifyColide == null || !this.verifyColide.isAlive()) {
/*      */         
/*  605 */         this.verifyColide = new Thread(new VerifyColide(1));
/*  606 */         this.verifyColide.start();
/*      */       }
/*  608 */       else if (this.waitEnd == null || !this.waitEnd.isAlive()) {
/*      */ 
/*      */         
/*  611 */         this.waitEnd = new Thread(new WaitColideEnd(1));
/*  612 */         this.waitEnd.start();
/*      */       
/*      */       }
/*  615 */       else if (this.waitOnceMore == null || !this.waitOnceMore.isAlive()) {
/*      */         
/*  617 */         this.waitOnceMore = new Thread(new WaitOnceMore(1));
/*  618 */         this.waitOnceMore.start();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  627 */     if (!this.dimAssigned2) {
/*      */       
/*  629 */       if (this.fTime2) {
/*  630 */         this.xObj2 = this.xObj1 + this.obj1Length + this.dist1;
/*  631 */         this.fTime2 = false;
/*      */       } 
/*      */       
/*  634 */       this.dimAssigned2 = true;
/*      */     } 
/*      */ 
/*      */     
/*  638 */     g.setColor(this.obj2Color);
/*      */     
/*  640 */     if (this.draw2)
/*      */     {
/*  642 */       switch (currentObj2) {
/*      */ 
/*      */         
/*      */         case 1:
/*  646 */           this.obj2Length = 50;
/*  647 */           this.obj2Height = 50;
/*      */           
/*  649 */           g.fillRect(this.dist1 + this.xObj2 + this.passObj2, getHeight() - this.obj2Height + 30, this.obj2Length, this.obj2Height);
/*      */           
/*  651 */           g.setColor(Color.BLACK);
/*  652 */           g.drawRect(this.dist1 + this.xObj2 + this.passObj2, getHeight() - this.obj2Height + 30, this.obj2Length + 1, this.obj2Height + 1);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  658 */           this.obj2Length = 50;
/*  659 */           this.obj2Height = 50;
/*      */           
/*  661 */           g.fillOval(this.dist1 + this.xObj2 + this.passObj2, getHeight() - this.obj2Height + 30, this.obj2Length, this.obj2Height);
/*      */           
/*  663 */           g.setColor(Color.BLACK);
/*  664 */           g.drawOval(this.dist1 + this.xObj2 + this.passObj2, getHeight() - this.obj2Height + 30, this.obj2Length + 1, this.obj2Height + 1);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  673 */     if (this.xObj2 + this.passObj2 + this.dist1 <= -this.obj2Length) {
/*      */       
/*  675 */       this.draw2 = false;
/*      */       
/*  677 */       chooseNextObj(2);
/*  678 */       chooseDist1();
/*  679 */       this.passObj2 = 0;
/*      */       
/*  681 */       if (this.verifyColide == null || !this.verifyColide.isAlive()) {
/*      */         
/*  683 */         this.verifyColide = new Thread(new VerifyColide(2));
/*  684 */         this.verifyColide.start();
/*      */       }
/*  686 */       else if (this.waitEnd == null || !this.waitEnd.isAlive()) {
/*      */         
/*  688 */         this.waitEnd = new Thread(new WaitColideEnd(2));
/*  689 */         this.waitEnd.start();
/*      */       
/*      */       }
/*  692 */       else if (this.waitOnceMore == null || !this.waitOnceMore.isAlive()) {
/*      */         
/*  694 */         this.waitOnceMore = new Thread(new WaitOnceMore(2));
/*  695 */         this.waitOnceMore.start();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  704 */     if (!this.dimAssigned3) {
/*      */       
/*  706 */       if (this.fTime3) {
/*  707 */         this.xObj3 = this.xObj2 + this.obj2Length + this.dist2;
/*  708 */         this.fTime3 = false;
/*      */       } 
/*      */       
/*  711 */       this.dimAssigned3 = true;
/*      */     } 
/*      */ 
/*      */     
/*  715 */     g.setColor(this.obj3Color);
/*      */     
/*  717 */     if (this.draw3)
/*      */     {
/*      */       
/*  720 */       switch (currentObj3) {
/*      */ 
/*      */         
/*      */         case 1:
/*  724 */           this.obj3Length = 50;
/*  725 */           this.obj3Height = 50;
/*      */           
/*  727 */           g.fillRect(this.dist2 + this.xObj3 + this.passObj3, getHeight() - this.obj3Height + 30, this.obj3Length, this.obj3Height);
/*      */           
/*  729 */           g.setColor(Color.BLACK);
/*  730 */           g.drawRect(this.dist2 + this.xObj3 + this.passObj3, getHeight() - this.obj3Height + 30, this.obj3Length + 1, this.obj3Height + 1);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  736 */           this.obj3Length = 50;
/*  737 */           this.obj3Height = 50;
/*      */           
/*  739 */           g.fillOval(this.dist2 + this.xObj3 + this.passObj3, getHeight() - this.obj3Height + 30, this.obj3Length, this.obj3Height);
/*      */           
/*  741 */           g.setColor(Color.BLACK);
/*  742 */           g.drawOval(this.dist2 + this.xObj3 + this.passObj3, getHeight() - this.obj3Height + 30, this.obj3Length + 1, this.obj3Height + 1);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  751 */     if (this.xObj3 + this.passObj3 + this.dist2 <= -this.obj3Length) {
/*      */       
/*  753 */       this.draw3 = false;
/*      */       
/*  755 */       chooseNextObj(3);
/*  756 */       chooseDist2();
/*  757 */       this.passObj3 = 0;
/*      */       
/*  759 */       if (this.verifyColide == null || !this.verifyColide.isAlive()) {
/*      */         
/*  761 */         this.verifyColide = new Thread(new VerifyColide(3));
/*  762 */         this.verifyColide.start();
/*      */       }
/*  764 */       else if (this.waitEnd == null || !this.waitEnd.isAlive()) {
/*      */         
/*  766 */         this.waitEnd = new Thread(new WaitColideEnd(3));
/*  767 */         this.waitEnd.start();
/*      */       }
/*  769 */       else if (this.waitOnceMore == null || !this.waitOnceMore.isAlive()) {
/*      */         
/*  771 */         this.waitOnceMore = new Thread(new WaitOnceMore(3));
/*  772 */         this.waitOnceMore.start();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  781 */     if (isColiding(1) || isColiding(2) || isColiding(3)) {
/*      */       
/*  783 */       if (this.manageJump != null) {
/*  784 */         this.manageJump.interrupt();
/*  785 */         this.isJumping = false;
/*      */       } 
/*      */       
/*  788 */       if (this.manageBlinkDust != null) {
/*  789 */         this.manageBlinkDust.interrupt();
/*      */       }
/*      */       
/*  792 */       if (this.gameTimeRan != null) {
/*  793 */         this.gameTimeRan.interrupt();
/*      */       }
/*      */       
/*  796 */       if (this.turnSpeedInc != null) {
/*  797 */         this.turnSpeedInc.interrupt();
/*      */       }
/*      */       
/*  800 */       this.gameOver = true;
/*      */       
/*  802 */       if (!this.laughGoinOn) {
/*      */         
/*  804 */         if (this.laughSound == null || !this.laughSound.isAlive()) {
/*  805 */           this.laughSound = new Thread(new LaughSound());
/*  806 */           this.laughSound.start();
/*      */         } 
/*      */         
/*  809 */         this.laughGoinOn = true;
/*      */       } 
/*      */ 
/*      */       
/*  813 */       if (this.gameOverSound == null || !this.gameOverSound.isAlive()) {
/*  814 */         this.gameOverSound = new Thread(new GameOverSound());
/*  815 */         this.gameOverSound.start();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  824 */     if (this.gameOver) {
/*      */       
/*  826 */       g.setFont(GAME_OVER_FONT);
/*  827 */       g.setColor(GAME_OVER_COLOR);
/*      */ 
/*      */       
/*  830 */       g.drawString("SE FUDEU!", getWidth() / 2 - 155, getHeight() / 2 - 50);
/*      */ 
/*      */       
/*  833 */       if (!this.cloudColor.equals(Color.BLACK)) {
/*      */         
/*  835 */         this.cloudColor = Color.BLACK;
/*  836 */         this.bgColor = new Color(247, 247, 247);
/*  837 */         setBackground(this.bgColor);
/*  838 */         this.floorColor = new Color(255, 232, 239);
/*  839 */         this.dustColor = this.floorColor;
/*  840 */         this.bigEyeColor = Color.BLACK;
/*  841 */         this.mouthColor = Color.BLACK;
/*  842 */         this.bodyColor = new Color(195, 195, 195);
/*  843 */         this.obj1Color = this.obj2Color = this.obj3Color = new Color(255, 174, 201);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  852 */     if (this.gamePaused) {
/*      */       
/*  854 */       g.setFont(GAME_PAUSED_STR_FONT);
/*  855 */       g.setColor(GAME_PAUSED_STR_COLOR);
/*  856 */       g.drawString("JOGO PAUSADO", getWidth() / 2 - 155, getHeight() / 2 - 50);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  861 */     if (this.speedIncreased) {
/*      */       
/*  863 */       g.setColor(this.speedIncTxtColor);
/*  864 */       g.setFont(new Font("Dialog", 3, 20));
/*  865 */       g.drawString("+velocidade", 
/*  866 */           getWidth() / 2 - 25 - "+velocidade".length() * 10, 
/*  867 */           getHeight() / 2 - 110);
/*      */       
/*  869 */       g.setColor(this.speedIncArrowColor);
/*  870 */       Polygon arrow = new Polygon();
/*      */       
/*  872 */       arrow.addPoint(getWidth() / 2, getHeight() / 2 - 150);
/*  873 */       arrow.addPoint(getWidth() / 2 - 15, getHeight() / 2 - 130);
/*  874 */       arrow.addPoint(getWidth() / 2 - 7, getHeight() / 2 - 130);
/*      */       
/*  876 */       arrow.addPoint(getWidth() / 2 - 7, getHeight() / 2 - 100);
/*  877 */       arrow.addPoint(getWidth() / 2 + 7, getHeight() / 2 - 100);
/*  878 */       arrow.addPoint(getWidth() / 2 + 7, getHeight() / 2 - 130);
/*      */       
/*  880 */       arrow.addPoint(getWidth() / 2 + 15, getHeight() / 2 - 130);
/*      */       
/*  882 */       g.fillPolygon(arrow);
/*      */       
/*  884 */       g.setColor(new Color(0, 0, 0));
/*      */       
/*  886 */       g.drawPolygon(arrow);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showControls() {
/*  896 */     this.gamePaused = true;
/*      */     
/*  898 */     JOptionPane.showMessageDialog(this, "Use a tecla: \n\t\" ^, ESPAÇO ou W\"\npara pular", "Controles", 1);
/*      */ 
/*      */     
/*  901 */     JOptionPane.showMessageDialog(this, "Você pode pausar o jogo presionando a tecla \"ESC\"", "Controles", 1);
/*      */     
/*  903 */     this.gamePaused = false;
/*      */     
/*  905 */     this.entranceSound.interrupt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void restart() {
/*  912 */     this.gameOverSound.interrupt();
/*      */     
/*  914 */     this.cloudColor = new Color(255, 255, 255);
/*      */     
/*  916 */     this.bodyColor = new Color(0, 0, 0);
/*  917 */     this.bigEyeColor = new Color(255, 255, 255);
/*  918 */     this.smallEyeColor = new Color(0, 0, 0);
/*  919 */     this.mouthColor = new Color(255, 255, 255);
/*      */     
/*  921 */     this.obj1Color = this.obj2Color = this.obj3Color = new Color(234, 193, 53);
/*      */     
/*  923 */     this.floorColor = new Color(222, 177, 22);
/*  924 */     this.dustColor = new Color(235, 228, 112);
/*      */     
/*  926 */     this.bgColor = new Color(238, 223, 115);
/*      */     
/*  928 */     setBackground(this.bgColor);
/*      */     
/*  930 */     configObjects();
/*      */     
/*  932 */     this.jumpTime = 450;
/*  933 */     this.objectSpeed = 5;
/*  934 */     this.score = new BigInteger("0");
/*      */     
/*  936 */     this.laughGoinOn = false;
/*  937 */     this.gamePaused = false;
/*  938 */     this.playerAssigned = false;
/*  939 */     this.gameOver = false;
/*      */     
/*  941 */     this.makePanelLoop = new Thread(new MakePanelLoop());
/*  942 */     this.makePanelLoop.start();
/*      */     
/*  944 */     this.gameTimeRan = new Thread(new GameTimeRan());
/*  945 */     this.gameTimeRan.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void confirmRequests() {
/*  954 */     int answer = JOptionPane.showConfirmDialog(this, "Jogar de novo?", "Jogar?", 0, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  959 */     if (answer == 0) {
/*  960 */       restart();
/*      */     }
/*  962 */     else if (answer == 1) {
/*      */       
/*  964 */       int sure = JOptionPane.showConfirmDialog(this, "Tem certeza que quer sair?", "Sair?", 0, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  969 */       if (sure == 0) {
/*      */         
/*  971 */         this.entranceFrame.dispose();
/*  972 */         JOptionPane.showMessageDialog(null, "Flwsss!", "Despedida", -1);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  977 */       else if (sure == 1) {
/*      */         
/*  979 */         restart();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isColiding(int objNumber) {
/*  991 */     boolean coliding = false;
/*      */     
/*  993 */     switch (objNumber) {
/*      */       
/*      */       case 1:
/*  996 */         if (this.xObj1 + this.passObj1 <= this.baseX2 && this.xObj1 + this.passObj1 >= this.baseX1 && 
/*  997 */           getHeight() - this.obj1Height + 30 <= this.baseY1)
/*      */         {
/*  999 */           coliding = true;
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 1007 */         if (this.xObj2 + this.passObj2 + this.dist1 <= this.baseX2 && this.xObj2 + this.passObj2 + this.dist1 >= this.baseX1 && 
/* 1008 */           getHeight() - this.obj2Height + 30 <= this.baseY1)
/*      */         {
/* 1010 */           coliding = true;
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/* 1018 */         if (this.xObj3 + this.passObj3 + this.dist2 <= this.baseX2 && this.xObj3 + this.passObj3 + this.dist2 >= this.baseX1 && 
/* 1019 */           getHeight() - this.obj3Height + 30 <= this.baseY1)
/*      */         {
/* 1021 */           coliding = true;
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1029 */     return coliding;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void changeRandDist() {
/* 1035 */     this.distWaves = 1 + this.rand.nextInt(700);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyTyped(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent e) {
/* 1048 */     if (!this.gameOver) {
/*      */       
/* 1050 */       if (e.getKeyCode() == 27) {
/* 1051 */         this.gamePaused = !this.gamePaused;
/*      */       }
/*      */       
/* 1054 */       if (!this.gamePaused)
/*      */       {
/* 1056 */         if (e.getKeyCode() == 38 || e
/* 1057 */           .getKeyCode() == 87 || e
/* 1058 */           .getKeyCode() == 32)
/*      */         {
/* 1060 */           if (!this.isJumping)
/*      */           {
/* 1062 */             this.isJumping = true;
/*      */             
/* 1064 */             this.soundJump = new Thread(new SoundJump());
/* 1065 */             this.soundJump.start();
/*      */             
/* 1067 */             this.manageBlinkDust.interrupt();
/* 1068 */             this.manageBlinkDust = null;
/*      */ 
/*      */             
/* 1071 */             this.dustCRed = 235;
/* 1072 */             this.dustCGreen = 228;
/* 1073 */             this.dustCBlue = 112;
/*      */             
/* 1075 */             this.manageJump = new Thread(new ManageJump());
/* 1076 */             this.manageJump.start();
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1085 */       confirmRequests();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void keyReleased(KeyEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mousePressed(MouseEvent e) {
/* 1103 */     if (this.gameOver) {
/* 1104 */       confirmRequests();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseEntered(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseExited(MouseEvent e) {}
/*      */ 
/*      */ 
/*      */   
/*      */   private class MakePanelLoop
/*      */     implements Runnable
/*      */   {
/*      */     private MakePanelLoop() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1134 */         while (!JumpGame.this.gameOver)
/*      */         {
/* 1136 */           if (!JumpGame.this.gamePaused) {
/*      */ 
/*      */             
/* 1139 */             if (JumpGame.this.draw1) {
/* 1140 */               JumpGame.this.passObj1 = JumpGame.this.passObj1 - JumpGame.this.objectSpeed;
/*      */             }
/*      */             
/* 1143 */             if (JumpGame.this.draw2) {
/* 1144 */               JumpGame.this.passObj2 = JumpGame.this.passObj2 - JumpGame.this.objectSpeed;
/*      */             }
/*      */             
/* 1147 */             if (JumpGame.this.draw3) {
/* 1148 */               JumpGame.this.passObj3 = JumpGame.this.passObj3 - JumpGame.this.objectSpeed;
/*      */             }
/*      */ 
/*      */             
/* 1152 */             JumpGame.this.passC1 = JumpGame.this.passC1 - 2;
/* 1153 */             JumpGame.this.passC2 = JumpGame.this.passC2 - 2;
/*      */ 
/*      */             
/* 1156 */             JumpGame.this.wavePass1 = JumpGame.this.wavePass1 - JumpGame.this.waveSpeed;
/* 1157 */             JumpGame.this.wavePass2 = JumpGame.this.wavePass2 - JumpGame.this.waveSpeed;
/*      */           } 
/*      */           
/* 1160 */           JumpGame.this.entranceFrame.repaint();
/* 1161 */           Thread.sleep(12L);
/*      */         }
/*      */       
/*      */       }
/* 1165 */       catch (InterruptedException ie) {
/*      */         
/* 1167 */         JOptionPane.showMessageDialog(null, "There was any problem with main loop", "MAIN LOOP ERROR", 0);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class ManageJump
/*      */     implements Runnable
/*      */   {
/*      */     private ManageJump() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1188 */         assignVars(110);
/* 1189 */         Thread.sleep((4 * JumpGame.this.jumpTime / 100));
/*      */         int i;
/* 1191 */         for (i = 0; i < 10; i++) {
/*      */           
/* 1193 */           assignVars(1);
/* 1194 */           Thread.sleep((56 * JumpGame.this.jumpTime / 100 / 10));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1200 */         for (i = 0; i < 60; i++) {
/*      */           
/* 1202 */           assignVars(-2);
/*      */           
/* 1204 */           if (i < 49) {
/* 1205 */             Thread.sleep((40 * JumpGame.this.jumpTime / 100 / 60));
/*      */           }
/*      */         } 
/*      */         
/* 1209 */         JumpGame.this.isJumping = false;
/*      */         
/* 1211 */         if (!JumpGame.this.gameOver)
/*      */         {
/* 1213 */           if (JumpGame.this.verifyScore == null || !JumpGame.this.verifyScore.isAlive()) {
/* 1214 */             JumpGame.this.verifyScore = new Thread(new JumpGame.VerifyScore());
/* 1215 */             JumpGame.this.verifyScore.start();
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1220 */         JumpGame.this.manageBlinkDust = new Thread(new JumpGame.ManageBlinkDust());
/* 1221 */         JumpGame.this.manageBlinkDust.start();
/*      */       
/*      */       }
/* 1224 */       catch (InterruptedException interruptedException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void assignVars(int jumpPx) {
/* 1242 */       JumpGame.this.baseY1 = JumpGame.this.baseY1 - jumpPx;
/* 1243 */       JumpGame.this.baseY2 = JumpGame.this.baseY2 - jumpPx;
/* 1244 */       JumpGame.this.headY = JumpGame.this.headY - jumpPx;
/* 1245 */       JumpGame.this.bigEyeY = JumpGame.this.bigEyeY - jumpPx;
/* 1246 */       JumpGame.this.smallEyeY = JumpGame.this.smallEyeY - jumpPx;
/* 1247 */       JumpGame.this.mouthY = JumpGame.this.mouthY - jumpPx;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class ManageBlinkDust
/*      */     implements Runnable
/*      */   {
/*      */     private ManageBlinkDust() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1262 */         label31: while (JumpGame.this.dustCRed < 255 || JumpGame.this
/* 1263 */           .dustCGreen < 255 || JumpGame.this
/* 1264 */           .dustCBlue < 150) {
/*      */           
/* 1266 */           if (JumpGame.this.dustCRed < 255) {
/* 1267 */             ++JumpGame.this.dustCRed;
/*      */           }
/*      */           
/* 1270 */           if (JumpGame.this.dustCGreen < 255) {
/* 1271 */             ++JumpGame.this.dustCGreen;
/*      */           }
/*      */           
/* 1274 */           if (JumpGame.this.dustCBlue < 150) {
/* 1275 */             ++JumpGame.this.dustCBlue;
/*      */           }
/*      */ 
/*      */           
/* 1279 */           JumpGame.this.dustColor = new Color(JumpGame.this.dustCRed, JumpGame.this.dustCGreen, JumpGame.this.dustCBlue);
/*      */ 
/*      */           
/* 1282 */           Thread.sleep(50L);
/*      */ 
/*      */           
/* 1285 */           if (JumpGame.this.dustCRed == 255 && JumpGame.this
/* 1286 */             .dustCGreen == 255 && JumpGame.this
/* 1287 */             .dustCBlue == 150) {
/*      */             while (true) {
/* 1289 */               if (JumpGame.this.dustCRed > 235 || JumpGame.this
/* 1290 */                 .dustCGreen > 228 || JumpGame.this
/* 1291 */                 .dustCBlue > 112) {
/*      */                 
/* 1293 */                 if (JumpGame.this.dustCRed > 235) {
/* 1294 */                   --JumpGame.this.dustCRed;
/*      */                 }
/*      */                 
/* 1297 */                 if (JumpGame.this.dustCGreen > 228) {
/* 1298 */                   --JumpGame.this.dustCGreen;
/*      */                 }
/*      */                 
/* 1301 */                 if (JumpGame.this.dustCBlue > 112) {
/* 1302 */                   --JumpGame.this.dustCBlue;
/*      */                 }
/*      */                 
/* 1305 */                 Thread.sleep(50L);
/*      */                 
/* 1307 */                 JumpGame.this.dustColor = new Color(JumpGame.this.dustCRed, JumpGame.this.dustCGreen, JumpGame.this.dustCBlue);
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*      */               continue label31;
/*      */             } 
/*      */           }
/*      */         } 
/* 1317 */       } catch (InterruptedException interruptedException) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class VerifyColide
/*      */     implements Runnable
/*      */   {
/*      */     private int objectNumber;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public VerifyColide(int number) {
/* 1339 */       this.objectNumber = number;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1350 */         if (this.objectNumber == 1)
/*      */         {
/* 1352 */           JumpGame.this.xObj1 = JumpGame.this.getWidth() + JumpGame.this.obj1Length;
/*      */           
/* 1354 */           while (calcDist21() < 5 * (JumpGame.this.body.getBounds()).width || 
/* 1355 */             calcDist31() < 5 * (JumpGame.this.body.getBounds()).width)
/*      */           {
/* 1357 */             Thread.sleep(40L);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1362 */           JumpGame.this.dimAssigned1 = false;
/*      */           
/* 1364 */           JumpGame.this.draw1 = true;
/*      */         }
/* 1366 */         else if (this.objectNumber == 2)
/*      */         {
/* 1368 */           JumpGame.this.xObj2 = JumpGame.this.getWidth() + JumpGame.this.obj2Length;
/*      */ 
/*      */           
/* 1371 */           while (calcDist21() < 5 * (JumpGame.this.body.getBounds()).width || 
/* 1372 */             calcDist23() < 5 * (JumpGame.this.body.getBounds()).width) {
/*      */             
/* 1374 */             JumpGame.this.chooseDist1();
/*      */             
/* 1376 */             Thread.sleep(20L);
/*      */           } 
/*      */ 
/*      */           
/* 1380 */           JumpGame.this.dimAssigned2 = false;
/*      */           
/* 1382 */           JumpGame.this.draw2 = true;
/*      */         }
/* 1384 */         else if (this.objectNumber == 3)
/*      */         {
/* 1386 */           JumpGame.this.xObj3 = JumpGame.this.getWidth() + JumpGame.this.obj3Length;
/*      */ 
/*      */           
/* 1389 */           while (calcDist31() < 5 * (JumpGame.this.body.getBounds()).width || 
/* 1390 */             calcDist23() < 5 * (JumpGame.this.body.getBounds()).width) {
/*      */             
/* 1392 */             JumpGame.this.chooseDist2();
/* 1393 */             Thread.sleep(20L);
/*      */           } 
/*      */ 
/*      */           
/* 1397 */           JumpGame.this.dimAssigned3 = false;
/*      */           
/* 1399 */           JumpGame.this.draw3 = true;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1404 */       catch (InterruptedException ie) {
/*      */         
/* 1406 */         JOptionPane.showMessageDialog(null, "There was any problem with verify colide", "VERIFY COLIDE ERROR", 0);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int calcDist21() {
/* 1419 */       if (JumpGame.this.xObj1 + JumpGame.this.passObj1 > JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1) {
/* 1420 */         return JumpGame.this.xObj1 + JumpGame.this.passObj1 - JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1 + JumpGame.this.obj2Length;
/*      */       }
/* 1422 */       return JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1 - JumpGame.this.xObj1 + JumpGame.this.passObj1 + JumpGame.this.obj1Length;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int calcDist23() {
/* 1430 */       if (JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 > JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1) {
/* 1431 */         return JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 - JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1 + JumpGame.this.obj2Length;
/*      */       }
/* 1433 */       return JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1 - JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 + JumpGame.this.obj3Length;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int calcDist31() {
/* 1441 */       if (JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 > JumpGame.this.xObj1 + JumpGame.this.passObj1) {
/* 1442 */         return JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 - JumpGame.this.xObj1 + JumpGame.this.passObj1 + JumpGame.this.obj1Length;
/*      */       }
/* 1444 */       return JumpGame.this.xObj1 + JumpGame.this.passObj1 - JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 + JumpGame.this.obj3Length;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class WaitColideEnd
/*      */     implements Runnable
/*      */   {
/*      */     private int objectNumber;
/*      */ 
/*      */ 
/*      */     
/*      */     public WaitColideEnd(int number) {
/* 1459 */       this.objectNumber = number;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1468 */         while (JumpGame.this.verifyColide.isAlive())
/*      */         {
/* 1470 */           Thread.sleep(20L);
/*      */         }
/*      */ 
/*      */         
/* 1474 */         JumpGame.this.verifyColide = new Thread(new JumpGame.VerifyColide(this.objectNumber));
/* 1475 */         JumpGame.this.verifyColide.start();
/*      */       }
/* 1477 */       catch (InterruptedException ie) {
/*      */         
/* 1479 */         JOptionPane.showMessageDialog(null, "There was any problem with wait colide end", "WAIT COLIDE ERROR", 0);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class WaitOnceMore
/*      */     implements Runnable
/*      */   {
/*      */     private int objectNumber;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WaitOnceMore(int objN) {
/* 1499 */       this.objectNumber = objN;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1508 */         while (JumpGame.this.waitEnd.isAlive())
/*      */         {
/* 1510 */           Thread.sleep(20L);
/*      */         }
/*      */ 
/*      */         
/* 1514 */         JumpGame.this.waitEnd = new Thread(new JumpGame.WaitColideEnd(this.objectNumber));
/* 1515 */         JumpGame.this.waitEnd.start();
/*      */       }
/* 1517 */       catch (InterruptedException ie) {
/*      */         
/* 1519 */         JOptionPane.showMessageDialog(null, "There was any problem wait colide once more", "WAIT ONCE MORE ERROR", 0);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configFrame() {
/* 1603 */     this.entranceFrame = new JFrame("Pule, se conseguir");
/* 1604 */     this.entranceFrame.setIconImage((new ImageIcon(JumpGame.class
/*      */           
/* 1606 */           .getResource("jump.png"))).getImage());
/*      */     
/* 1608 */     this.entranceFrame.setDefaultCloseOperation(3);
/*      */ 
/*      */     
/* 1611 */     this.entranceFrame.add(this);
/* 1612 */     this.entranceFrame.addKeyListener(this);
/*      */     
/* 1614 */     this.entranceFrame.setExtendedState(6);
/* 1615 */     this.entranceFrame.setSize(800, 600);
/* 1616 */     this.entranceFrame.setLocationRelativeTo((Component)null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1622 */     this.entranceFrame.setVisible(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setLKF(String lkfName) {
/*      */     try {
/* 1631 */       for (UIManager.LookAndFeelInfo inf : UIManager.getInstalledLookAndFeels())
/*      */       {
/* 1633 */         if (inf.getName().equalsIgnoreCase(lkfName))
/*      */         {
/* 1635 */           UIManager.setLookAndFeel(inf.getClassName());
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1641 */     catch (Exception e) {
/*      */       
/* 1643 */       JOptionPane.showMessageDialog(null, "Look and Feel ERROR");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private class GameTimeRan
/*      */     implements Runnable
/*      */   {
/*      */     private GameTimeRan() {}
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1656 */         int secondCounter = 0;
/*      */         
/* 1658 */         while (!JumpGame.this.gameOver)
/*      */         {
/* 1660 */           Thread.sleep(1000L);
/*      */           
/* 1662 */           if (!JumpGame.this.gamePaused)
/*      */           {
/* 1664 */             secondCounter++;
/*      */             
/* 1666 */             if (secondCounter % 30 == 0) {
/* 1667 */               JumpGame.this.objectSpeed = JumpGame.this.objectSpeed + 1;
/* 1668 */               JumpGame.this.jumpTime = 2250 / JumpGame.this.objectSpeed;
/*      */               
/* 1670 */               JumpGame.this.speedIncreased = true;
/* 1671 */               JumpGame.this.turnSpeedInc = new Thread(new JumpGame.TurnSpeedIncreased());
/* 1672 */               JumpGame.this.turnSpeedInc.start();
/*      */             } 
/*      */             
/* 1675 */             if (secondCounter == 3600) {
/* 1676 */               secondCounter = 0;
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1682 */       } catch (InterruptedException interruptedException) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class VerifyScore
/*      */     implements Runnable
/*      */   {
/*      */     private VerifyScore() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 1702 */       if (JumpGame.this.xObj1 + JumpGame.this.passObj1 < JumpGame.this.baseX1)
/*      */       {
/* 1704 */         JumpGame.this.score = JumpGame.this.score.add(BigInteger.valueOf(JumpGame.this.objectSpeed));
/*      */       }
/*      */       
/* 1707 */       if (JumpGame.this.xObj2 + JumpGame.this.passObj2 + JumpGame.this.dist1 < JumpGame.this.baseX1) {
/* 1708 */         JumpGame.this.score = JumpGame.this.score.add(BigInteger.valueOf(JumpGame.this.objectSpeed));
/*      */       }
/*      */       
/* 1711 */       if (JumpGame.this.xObj3 + JumpGame.this.passObj3 + JumpGame.this.dist2 < JumpGame.this.baseX1) {
/* 1712 */         JumpGame.this.score = JumpGame.this.score.add(BigInteger.valueOf(JumpGame.this.objectSpeed));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class TurnSpeedIncreased
/*      */     implements Runnable
/*      */   {
/*      */     private TurnSpeedIncreased() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1728 */         int gTxt = JumpGame.this.speedIncTxtColor.getGreen();
/* 1729 */         int rTxt = JumpGame.this.speedIncTxtColor.getRed();
/* 1730 */         int bTxt = JumpGame.this.speedIncTxtColor.getBlue();
/*      */         
/* 1732 */         int gA = JumpGame.this.speedIncArrowColor.getGreen();
/* 1733 */         int rA = JumpGame.this.speedIncArrowColor.getRed();
/* 1734 */         int bA = JumpGame.this.speedIncArrowColor.getBlue();
/*      */         
/* 1736 */         while (!JumpGame.this.speedIncArrowColor.equals(JumpGame.this.bgColor) && 
/* 1737 */           !JumpGame.this.speedIncTxtColor.equals(JumpGame.this.bgColor)) {
/*      */           
/* 1739 */           if (JumpGame.this.speedIncTxtColor.getRed() < 238) {
/* 1740 */             rTxt++;
/*      */           }
/*      */           
/* 1743 */           if (JumpGame.this.speedIncTxtColor.getGreen() < 223) {
/* 1744 */             gTxt++;
/*      */           }
/* 1746 */           if (JumpGame.this.speedIncTxtColor.getBlue() < 115) {
/* 1747 */             bTxt++;
/*      */           }
/*      */ 
/*      */           
/* 1751 */           if (JumpGame.this.speedIncArrowColor.getRed() < 238) {
/* 1752 */             rA++;
/*      */           }
/*      */           
/* 1755 */           if (JumpGame.this.speedIncArrowColor.getGreen() > 223) {
/* 1756 */             gA--;
/*      */           }
/*      */           
/* 1759 */           if (JumpGame.this.speedIncArrowColor.getBlue() < 115) {
/* 1760 */             bA++;
/*      */           }
/*      */           
/* 1763 */           JumpGame.this.speedIncTxtColor = new Color(rTxt, gTxt, bTxt);
/* 1764 */           JumpGame.this.speedIncArrowColor = new Color(rA, gA, bA);
/*      */           
/* 1766 */           Thread.sleep(30L);
/*      */         } 
/*      */ 
/*      */         
/* 1770 */         JumpGame.this.speedIncreased = false;
/*      */         
/* 1772 */         JumpGame.this.speedIncTxtColor = JumpGame.SPEED_TXT_COLOR;
/* 1773 */         JumpGame.this.speedIncArrowColor = JumpGame.SPEED_ARROW_COLOR;
/*      */       }
/* 1775 */       catch (InterruptedException ie) {
/*      */         
/* 1777 */         JumpGame.this.speedIncreased = false;
/*      */         
/* 1779 */         JumpGame.this.speedIncTxtColor = JumpGame.SPEED_TXT_COLOR;
/* 1780 */         JumpGame.this.speedIncArrowColor = JumpGame.SPEED_ARROW_COLOR;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class SoundJump
/*      */     implements Runnable
/*      */   {
/*      */     private SoundJump() {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1798 */         AudioInputStream jumpSound = AudioSystem.getAudioInputStream(Test.class.getResource("jump-sound.wav"));
/*      */ 
/*      */         
/* 1801 */         Clip clip = AudioSystem.getClip();
/* 1802 */         clip.open(jumpSound);
/* 1803 */         clip.loop(0);
/*      */       }
/* 1805 */       catch (Exception e) {
/*      */         
/* 1807 */         System.err.println("Error: " + e.getCause());
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class EntranceSound
/*      */     implements Runnable
/*      */   {
/*      */     private EntranceSound() {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 1824 */       Clip clip = null;
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1829 */         AudioInputStream gmSound = AudioSystem.getAudioInputStream(Test.class.getResource("entrance-sound.wav"));
/*      */         
/* 1831 */         clip = AudioSystem.getClip();
/* 1832 */         clip.open(gmSound);
/*      */         
/* 1834 */         clip.start();
/*      */         
/* 1836 */         while (JumpGame.this.gameInit) {
/* 1837 */           Thread.sleep(500L);
/*      */         }
/*      */         
/* 1840 */         clip.stop();
/*      */       }
/* 1842 */       catch (Exception e) {
/*      */         
/* 1844 */         clip.stop();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class LaughSound
/*      */     implements Runnable
/*      */   {
/*      */     private LaughSound() {}
/*      */ 
/*      */     
/*      */     public void run() {
/* 1858 */       Clip clip = null;
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1863 */         AudioInputStream lgSound = AudioSystem.getAudioInputStream(Test.class.getResource("laugh-sound.wav"));
/*      */         
/* 1865 */         clip = AudioSystem.getClip();
/* 1866 */         clip.open(lgSound);
/*      */         
/* 1868 */         clip.start();
/*      */         
/* 1870 */         Thread.sleep(2000L);
/*      */       }
/* 1872 */       catch (Exception e) {
/*      */         
/* 1874 */         clip.stop();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class GameOverSound
/*      */     implements Runnable
/*      */   {
/*      */     private GameOverSound() {}
/*      */ 
/*      */     
/*      */     public void run() {
/* 1888 */       Clip clip = null;
/*      */ 
/*      */       
/*      */       try {
/* 1892 */         if (JumpGame.this.gameOver)
/*      */         {
/* 1894 */           Thread.sleep(2000L);
/*      */ 
/*      */           
/* 1897 */           AudioInputStream gmOverSound = AudioSystem.getAudioInputStream(Test.class.getResource("game-over-sound.wav"));
/*      */           
/* 1899 */           clip = AudioSystem.getClip();
/* 1900 */           clip.open(gmOverSound);
/*      */           
/* 1902 */           clip.start();
/*      */           
/* 1904 */           while (JumpGame.this.gameOver) {
/* 1905 */             Thread.sleep(300L);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1912 */       catch (Exception e) {
/*      */         
/* 1914 */         if (clip != null) {
/* 1915 */           clip.stop();
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1925 */     new JumpGame();
/*      */   }
/*      */ }


/* Location:              C:\Users\Isaac\Downloads\JumpGame.jar!\jumpgame\JumpGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */