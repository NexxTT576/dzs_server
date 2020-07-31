package com.mdy.dzs.game.exception;


public class TournamentException extends BaseException {
	/**等级未开启*/
	public static final ExceptionEntry EXCE_TOURNAMENT_LEVEL_LIMIT = createExceptionEntry(
			3300001, "level limit: {0}");
	/**免费次数还没有用完*/
	public static final ExceptionEntry EXCE_TOURNAMENT_HAVE_FREETIMES = createExceptionEntry(
			3300002, "have free times: {0}");
	/**购买次数已经用完*/
	public static final ExceptionEntry EXCE_TOURNAMENT_BUYTIMES_OVER = createExceptionEntry(
			3300003, "buy times over");
	/**活动未开启*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NOT_OPEN = createExceptionEntry(
			3300004, "not open");
	/**刷新时间未到*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NOT_REFRESH = createExceptionEntry(
			3300005, "not refresh");
	/**没有匹配的玩家*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NO_PLAYER = createExceptionEntry(
			3300006, "no player");
	/**挑战次数已经用完*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NO_TIMES = createExceptionEntry(
			3300007, "no times");
	/**超出购买次数*/
	public static final ExceptionEntry EXCE_TOURNAMENT_OUT_TIMES = createExceptionEntry(
			3300008, "out times");
	/**你的排名发生变化，请重新挑战*/
	public static final ExceptionEntry EXCE_TOURNAMENT_RANK_CHANGE = createExceptionEntry(
			3300009, "rank change");
	/**等级不足 不能兑换*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NOT_LEVEL = createExceptionEntry(
			33000010, "not level");
	/**不是对手*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NOT_OPP = createExceptionEntry(
			33000011, "not opponent");
	/**不是仇人*/
	public static final ExceptionEntry EXCE_TOURNAMENT_NOT_ENEMY = createExceptionEntry(
			33000012, "not enemy");
	/**只能攻打比自己排名高的*/
	public static final ExceptionEntry EXCE_TOURNAMENT_RANK_ERROR = createExceptionEntry(
			33000013, "rank error");
	/**不能攻打自己*/
	public static final ExceptionEntry EXCE_TOURNAMENT_IS_SELF = createExceptionEntry(
			33000014, "is self");
	/**仇人分数低*/
	public static final ExceptionEntry EXCE_TOURNAMENT_SCORE_LOW = createExceptionEntry(
			33000015, "score low");
}
