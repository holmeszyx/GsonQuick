package z.hol.gq;

/**
 * 日志
 * @author holmes
 *
 */
public interface GsonQuickLogger {

	/**
	 * JsonQuick的异常日志
	 * @param json
	 * @param e
	 */
	void e(String json, Exception e);
}
