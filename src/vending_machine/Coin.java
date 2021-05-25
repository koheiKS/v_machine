package vending_machine;

// コインそのもの
public class Coin {

	// 種類
	CoinType type;

	// コンストラクタ
	public Coin(CoinType type) {
		this.type = type;
	}

	// 種類のゲッター
	public CoinType getType() {
		return this.type;
	}

	// コインの等価関係
	@Override
	public boolean equals(Object obj) {
		// 同じインスタンスなら等価
		if (this == obj) {
			return true;
		}

		// コインかつ種類が一致していれば等価
		if (obj instanceof Coin) {
			Coin coin = (Coin) obj;
			if (this.type.equals(coin.type)) {
				return true;
			}
		}
		return false;
	}

}
