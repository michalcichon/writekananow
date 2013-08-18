package com.cichon.writekananow;

public class Tuple {
	
	private String name;
	private String imgFilename;
	
	Tuple(String name, String imgFilename) {
		this.name = name;
		this.imgFilename = imgFilename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgFilename() {
		return imgFilename;
	}

	public void setImgFilename(String imgFilename) {
		this.imgFilename = imgFilename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((imgFilename == null) ? 0 : imgFilename.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (imgFilename == null) {
			if (other.imgFilename != null)
				return false;
		} else if (!imgFilename.equals(other.imgFilename))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}