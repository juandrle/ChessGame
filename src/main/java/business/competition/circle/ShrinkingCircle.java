package business.competition.circle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;

public class ShrinkingCircle extends Circle implements Sprite {

	public ShrinkingCircle(double radius, Color color) {
		setRadius(radius);
		setFill(color);
	}

	@Override
	public void render() {
		// Animation, die dafür sorgt das die Kreise Kleiner werden
		ScaleTransition shrinkAnimation = new ScaleTransition(Duration.millis(1600), this);
		shrinkAnimation.setToX(0);
		shrinkAnimation.setToY(0);
		shrinkAnimation.play();
	}

}
