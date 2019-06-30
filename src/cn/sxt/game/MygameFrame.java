package cn.sxt.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
//import cn.sxt.game.Explode;

/**
 * 飞机游戏的主窗口
 * @author 陆嘉伟
 *
 */
public class MygameFrame extends Frame {   //双缓冲用Frame

    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT); //这里写窗口的宽度和高度

        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }
    Image ikunimg = GameUtil.getImage("images/ikun.png"); //下面的ikunimg取自这里
    Image bg = GameUtil.getImage("images/bg.png");
    //Image ballimg = GameUtil.getImage("images/ball.png");

    //int ballx =250,bally = 250;
    ikun cxk=new ikun(ikunimg ,250,250);
    //ball basketball=new ball();
    ball[] balls = new ball[50];
    //Explode bao;
    Date startTime = new Date();
    Date endTime;
    int period; //游戏持续时间
    //long startTime = System.currentTimeMillis();
    //long endTime;
    //int playTime;
    //ikun cxk2=new ikun(ikunimg ,150,250);
    //ikun cxk3=new ikun(ikunimg ,50,250);

    @Override
    public void paint(Graphics g) {   //自动被调用。 g相当于一个画笔
        Color c = g.getColor();
        //Font f = g.getFont();
        /**Color c = g.getColor();  //获取目前的 用好要还原回去
        Font f = g.getFont();
        g.setColor(Color.RED);

        g.drawLine(100,100,300,300);
        g.drawRect(100,100,300,300);    //矩形
        g.drawOval(100,100,300,300);    //椭圆
        g.fillRect(100,100,40,40);      //填充矩形
        g.setColor(Color.CYAN);
        g.setFont(new Font("黑体",Font.BOLD,50)); //注意大小写
        g.drawString("陆嘉伟",200,200);
        g.setColor(c);
        g.setFont(f);
        */
        g.drawImage(bg,0,0,null);
        //g.drawImage(ball,ballx,bally,null);
        //ballx++;
        cxk.drawSelf(g);//画坤坤
        //basketball.draw(g);//画篮球
        for(int i=0;i<balls.length;i++){
            balls[i].draw(g); //画出所有炮弹
            boolean peng =balls[i].getRect().intersects(cxk.getRect());

            if(peng){
                //System.out.println("相撞了！！！");
                cxk.live=false;

                endTime = new Date();
                period = (int)((endTime.getTime() -startTime.getTime())/1000);


            if(!cxk.live){
                g.setColor(Color.RED);
                Font f = new Font("黑体", Font.BOLD, 45);
                g.setFont(f);
                g.drawString("时间："+period+"秒",(int)cxk.x,(int)cxk.y);  //需要整数
            }


                //if(bao == null){
                   //bao = new Explode(cxk.x,cxk.y);
                    //endTime = System.currentTimeMillis();
                    //playTime = (int) ((endTime - startTime) / 1000);


                //bao.draw(g);
            }
            /**
             * if (!cxk.live) {
             *                 g.setColor(Color.YELLOW);
             *                 Font f = new Font("黑体", Font.BOLD, 45);
             *                 g.setFont(f);
             *                 g.drawString("playTime:" + playTime + "秒", 100, 200);
             *             }
             *             g.setColor(c);
             */


            g.setColor(c);// 颜色用完一定还要设置成原来的
            //g.setFont(f);
        }


        //cxk2.drawSelf(g);
        //cxk3.drawSelf(g);


    }
    //帮助我们反复重画窗口
    class PaintThread extends Thread {
        @Override
        public void run() {
            while(true){
                //System.out.println("窗口被重画了一次");
                repaint(); //重画

                try {
                    Thread.sleep(40);  //1s=1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //键盘监听
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("按下："+e.getKeyCode());
            cxk.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("抬起："+e.getKeyCode());
            cxk.minusDirection(e);
        }
    }
    /**
     * 初始化窗口
     */
    public void launchFrame(){
        this.setTitle("jiawlu作品");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(300,300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start(); //启动窗口重画的线程
        addKeyListener(new KeyMonitor());

        //初始化50个炮弹
        for(int i=0;i<balls.length;i++){
            balls[i]=new ball();
        }

    }

    public static void main(String[] args){
        MygameFrame f =new MygameFrame();
        f.launchFrame();
    }
}
