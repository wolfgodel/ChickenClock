
import java.awt.Font;
import java.time.Instant;
import stddraw.StdDraw;

public class Clock{
	
	
	private int width;
	private int height;

	private int halfWidht;
	private int halfHeight;
	
	private final double PI2 = Math.PI*2;
	private final double PI60 = Math.PI/30;
	private final double PI12 = Math.PI/6;
	
	private double r;
	
	public Clock(int width, int height) {
		this.width = width;
		this.height = height;
		
		halfWidht = width / 2;
		halfHeight = height / 2;
		
		r= halfWidht*0.8;
		
		
	}
	
	private void drawDial() {
		double x,y;
		int i=0;
		
		StdDraw.setPenRadius(0.01);
		
		StdDraw.setFont(new Font("Bembo", Font.BOLD, halfWidht/10));
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.text(-4, 48, "WOLFGODEL");
		
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.text(-5, 50, "WOLFGODEL");
		
		StdDraw.setFont(new Font("Bembo", Font.BOLD, halfWidht/12));
		for(double a=0; a<PI2; a+=PI60) {
			x=Math.cos(a)*r;
			y=Math.sin(a)*r;
			
			if(i%5==0) {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.circle(x, y,halfWidht/50);
				
				StdDraw.setPenColor(StdDraw.BLACK);
				int saatSayilar = (15-i/5)%12;
				if(saatSayilar==0)
					saatSayilar=12;
				StdDraw.text(x*0.85, y*0.85, String.valueOf(saatSayilar));
			
			}
			else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.point(x, y);
			}
			i++;
		}
		
	
		
	}
	
	private void drawHourAndMinuteHand(int HOUR5, int minute, int second) {
		double alpha;
		double x,y;
		
		
		alpha = Math.PI/2-HOUR5*PI60;
		
		x = r*0.5*Math.cos(alpha);
		y = r*0.5*Math.sin(alpha);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.line(0, 0, x, y);
		
		alpha = Math.PI/2-minute*PI60;
		
		x = r*0.75*Math.cos(alpha);
		y = r*0.75*Math.sin(alpha);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.008);
		StdDraw.line(0, 0, x, y);
		
		alpha = Math.PI/2-second*PI60;
		
		x = r*Math.cos(alpha);
		y = r*Math.sin(alpha);
	
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.line(0, 0, x, y);
			
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0,0);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.01);
		StdDraw.circle(0, 0, r*1.15);
		
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
	
		StdDraw.setPenRadius((double) halfWidht/10000);
		StdDraw.point(0, 0);
		StdDraw.setPenRadius();
		
	}
	
	
	 public void animation(double x, double y, int saniye) {
	 
		if(saniye%2==0)
			StdDraw.picture(x, y,"chicken1.png", halfWidht/2,halfHeight/2);
		else
			StdDraw.picture(x, y,"chicken2.png", halfWidht/2,halfHeight/2);
		
	}

	
	public void start() {


		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(-halfWidht, halfWidht);
		StdDraw.setYscale(-halfHeight, halfHeight);
		StdDraw.enableDoubleBuffering();
				
		
		StdDraw.setFont(new Font("Arial",Font.BOLD, 30));
		
		Instant instantTry = Instant.now();
		String timeString = instantTry.toString();
		
		String hourString, minString, secString;
		hourString=timeString.substring(11, 13);
		minString=timeString.substring(14, 16);
		secString=timeString.substring(17, 19);
		
		int hour = 3+Integer.parseInt(hourString), minute = Integer.parseInt(minString), second = 1+Integer.parseInt(secString);
		int extraHour5=minute/12;
		int HOUR5 = hour*5+extraHour5, minuteControl;
		hour=hour%24;
		
		while(true) {
			StdDraw.clear();
			
			animation(0, -halfHeight/4, second);
			drawDial();
			
			drawHourAndMinuteHand(HOUR5, minute, second);

			StdDraw.show();
			
			
			try {
				Thread.sleep(999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			second++;
			second = second%60;
			if(second==0) {
				minute++;
				minute=minute%60;
				minuteControl=minute%12;
					if(minuteControl==0) {
						HOUR5++;
						HOUR5=HOUR5%60;
					}
			}
			
		}
	}
	
}
