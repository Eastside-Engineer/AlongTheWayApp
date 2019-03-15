package finalproject.alongtheway.yelpbeans;

import java.io.Serializable;

public class Categories implements Serializable {
	private static final long serialVersionUID = 1L;

	private String alias;
	private String title;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
