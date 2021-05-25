package vending_machine;

// 商品そのもの
public class Item {

	// 商品名
	private String name;

	// コンストラクタ
	public Item(String name) {
		this.name = name;
	}

	// 商品ラベルを使ったコンストラクタ
	public Item(ItemLabel itemLabel) {
		this.name = itemLabel.getName();
	}

	// 商品名のゲッター
	public String getName() {
		return this.name;
	}

	// 商品の等価関係
	@Override
	public boolean equals(Object obj) {
		// 同じインスタンスなら等価
		if (this == obj) {
			return true;
		}

		// 商品かつ商品名が一致していれば等価
		if (obj instanceof Item) {
			Item item = (Item) obj;
			if (this.name.equals(item.name)) {
				return true;
			}
		}
		return false;
	}

}
