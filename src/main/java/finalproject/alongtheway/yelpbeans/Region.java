package finalproject.alongtheway.yelpbeans;

import java.io.Serializable;

public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	private Center center;

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

}
