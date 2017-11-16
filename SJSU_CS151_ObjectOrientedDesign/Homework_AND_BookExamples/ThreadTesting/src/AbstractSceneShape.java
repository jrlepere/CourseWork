import java.util.Random;

public abstract class AbstractSceneShape implements SceneShape {

	protected static final int SIZE = 40;
	private static final int DX = 5;
	private static final int SLEEP = 100;
	private static int objectCount;
	private static Random gen = new Random();
	
	protected int x,y;
	private SceneComponent sceneComponent;
	
	public AbstractSceneShape(SceneComponent theSceneComponent) {
		objectCount++;
		x = 0;
		y = (objectCount-1)*SIZE;
		sceneComponent = theSceneComponent;
	}
	
	public void run() {
		try {
			while (true) {
				slide(gen.nextInt(DX));
				sceneComponent.repaint();
				Thread.sleep(gen.nextInt(SLEEP));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void slide(int dx) {
		x += dx;
	}
	
}
