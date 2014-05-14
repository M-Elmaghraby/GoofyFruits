import java.io.Serializable;


@SuppressWarnings("serial")
public class  FallingItem extends GameObject implements ReadOnlyGameObjectIF ,Serializable
{
 

	private double angle;

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
