package day04;

public class Point <X,Y>{
		private X x;
		private Y y;
		public Point(X x,Y y){
			this.x=x;
			this.y=y;
		}
		public String toString(){
			return "x="+x+",y="+y;
		}
		
		public X getX() {
			return x;
		}
		public void setX(X x) {
			this.x = x;
		}
		public Y getY() {
			return y;
		}
		public void setY(Y y) {
			this.y = y;
		}
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + x;
//			result = prime * result + y;
//			return result;
//		}
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Point other = (Point) obj;
//			if (x != other.x)
//				return false;
//			if (y != other.y)
//				return false;
//			return true;
//		}
		
}
