package tp.pr4.logic;

public interface Observable<T> {
	
	/**
	 * Añade observador
	 * @param o
	 */
	public void addObserver(T o);

	/**
	 * Elimina observador
	 * @param o
	 */
	public void removeObserver(T o);
}
