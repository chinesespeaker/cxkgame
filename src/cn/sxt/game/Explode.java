package cn.sxt.game;

import cn.sxt.game.GameUtil;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Stone E-mail:330079598@qq.com
 * @date:2018年12月16日 下午6:45:39
 *	飞机的爆炸动画实现过程
 */
public class Explode {
    double x,y;
    int count;

     //Image[] imgs = new Image[16];//16张爆炸图片
     static Image[] imgs = new Image[16];
     static  {

        for(int i = 0;i < 16;i++) {
            imgs[i] = GameUtil.getImage("images/e" + (i+1) + ".gif");
            imgs[i].getWidth(null);

        }

    }


    public void draw(Graphics g) {
        if(count <= 15) {
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x,double y) {
        this.x = x;
        this.y = y;
    }


}