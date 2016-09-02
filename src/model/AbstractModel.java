package model;

import java.util.UUID;
public abstract class AbstractModel {
	private String uuid;
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUuid () == null) ? 0 : getUuid ().hashCode());
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
		AbstractModel other = (AbstractModel) obj;
		if (getUuid () == null) {
			if (other.getUuid () != null)
				return false;
		} else if (!getUuid ().equals(other.getUuid ()))
			return false;
		return true;
	}

	public String getUuid () {
    	if (uuid == null) {
    		uuid = UUID.randomUUID ().toString();
    	}
    	return uuid; 
    }
}
