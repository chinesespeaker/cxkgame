package cn.sxt.game;

import java.awt.*;

/**
 * 篮球类
 */

//public class ball extends GameObject {
  //  double degree;


    /**
     * public ball(){
        x = 200;
        y = 200;
        speed = 3;


    }


        //}

    public void drawSelf (Graphics g){
        degree = Math.random() * Math.PI * 2;
        speed =3;
        g.drawImage(img, (int) x, (int) y, null);
        System.out.println(degree);

        x += speed * Math.cos(degree);
        System.out.println(x);
        y += speed * Math.sin(degree);
    }


    public ball(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }
}
     */
public class ball extends GameObject {

    double degree;
    public ball(){
        x=200;
        y=200;
        width=10;
        height=10;
        speed=3;
        degree = Math.random() * Math.PI * 2;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.orange);

        g.fillOval((int)x,(int)y,width,height);
        //炮弹沿着任意角度飞
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);

        if(x<0||x>Constant.GAME_WIDTH-width){
            degree = Math.PI-degree;
        }
        if(y<30||y>Constant.GAME_HEIGHT-height){
            degree = -degree;
        }


        g.setColor(c);
    }
}
