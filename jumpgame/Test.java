/*    */ package jumpgame;
/*    */ 
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.KeyListener;
/*    */ import javax.sound.sampled.AudioInputStream;
/*    */ import javax.sound.sampled.AudioSystem;
/*    */ import javax.sound.sampled.Clip;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Test
/*    */   extends JFrame
/*    */   implements KeyListener
/*    */ {
/*    */   public void configAudio() {
/*    */     try {
/* 40 */       AudioInputStream audioStream = AudioSystem.getAudioInputStream(Test.class
/* 41 */           .getResource("entrance-sound.wav"));
/*    */       
/* 43 */       Clip clip = AudioSystem.getClip();
/* 44 */       clip.open(audioStream);
/*    */       
/* 46 */       clip.start();
/* 47 */       Thread.sleep(100000L);
/*    */     }
/* 49 */     catch (Exception e) {
/*    */       
/* 51 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 59 */     Test tst = new Test();
/* 60 */     tst.configAudio();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void keyTyped(KeyEvent e) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void keyPressed(KeyEvent e) {
/* 71 */     System.out.println("jump");
/*    */   }
/*    */   
/*    */   public void keyReleased(KeyEvent e) {}
/*    */ }


/* Location:              C:\Users\Isaac\Downloads\JumpGame.jar!\jumpgame\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */