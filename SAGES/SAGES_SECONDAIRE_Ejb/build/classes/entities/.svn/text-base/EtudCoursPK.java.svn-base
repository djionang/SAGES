/**
 * 
 */
package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author ISESTMA
 *
 */

@Embeddable
public class EtudCoursPK implements Serializable {
	
	//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;
		private int ideleve;
		private int codecours;
		
		
		
		public int getIdeleve() {
			return ideleve;
		}
		
		
		public void setIdeleve(int ideleve) {
			this.ideleve = ideleve;
		}
		
		
		public int getCodecours() {
			return codecours;
		}
		
		
		public void setCodecours(int codecours) {
			this.codecours = codecours;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + codecours;
			result = prime * result + ideleve;
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
			EtudCoursPK other = (EtudCoursPK) obj;
			if (codecours != other.codecours)
				return false;
			if (ideleve != other.ideleve)
				return false;
			return true;
		}
		
		
		

}
