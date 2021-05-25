package vending_machine;

// コインの種類
public class CoinType {

	// メンバ変数
	private int value;
	private String unit;

	// コンストラクタ
	public CoinType(int value, String unit) {
		this.value = value;
		this.unit = unit;
	}

	// コンストラクタ、単位指定なし
	public CoinType(int value) {
		this(value, "円");
	}

	// 価値のゲッター
	public int getValue() {
		return value;
	}

	// 単位のゲッター
	public String getUnit() {
		return unit;
	}

	// コインの種類の等価関係
	@Override
	public boolean equals(Object obj) {
		// 同じインスタンスなら等価
		if (this == obj) {
			return true;
		}

		// コインの種類かつ単位と価値が同じなら等価
		if (obj instanceof CoinType) {
			CoinType coinType = (CoinType) obj;
			if (this.unit.equals(coinType.getUnit()) && this.value == coinType.getValue()) {
				return true;
			}
		}
		return false;
	}

}

// コインのテンプレート
class CoinTypeTemplate {

	// 日本円のテンプレート
	public static final CoinType FIVE_HUNDRED_YEN = new CoinType(500);
	public static final CoinType HUNDRED_YEN = new CoinType(100);
	public static final CoinType FIFTY_YEN = new CoinType(50);
	public static final CoinType TEN_YEN = new CoinType(10);
	public static final CoinType[] YEN_TYPES = {
			FIVE_HUNDRED_YEN,
			HUNDRED_YEN,
			FIFTY_YEN,
			TEN_YEN
	};
}
