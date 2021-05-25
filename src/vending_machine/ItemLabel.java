package vending_machine;

// 商品のラベル
public class ItemLabel {

	// 次の商品ラベルid
	private static int nextId = 0;

	// ラベルにある情報
	private int id;
	private String name;
	private int price;
	private String priceUnit;

	// コンストラクタ
	public ItemLabel(String name, int price, String priceUnit) {
		this.id = nextId;
		this.name = name;
		this.price = price;
		this.priceUnit = priceUnit;

		nextId++;
	}

	// 値段の単位なしコンストラクタ
	public ItemLabel(String name, int price) {
		this(name, price, "円");
	}

	// idのゲッター
	public int getId() {
		return this.id;
	}

	// 商品名のゲッター
	public String getName() {
		return this.name;
	}

	// 値段のゲッター
	public int getPrice() {
		return this.price;
	}

	// 値段の単位のゲッター
	public String getPriceUnit() {
		return this.priceUnit;
	}
}

// 商品ラベルのテンプレート
class ItemLabelTemplate {

	// ドリンクテンプレート
	public static final ItemLabel COLA = new ItemLabel("コーラ", 120);
	public static final ItemLabel SODA = new ItemLabel("サイダー", 130);
	public static final ItemLabel COFFEE = new ItemLabel("コーヒー", 100);
	public static final ItemLabel TEA = new ItemLabel("紅茶", 150);
	public static final ItemLabel WATER = new ItemLabel("水", 90);
	public static final ItemLabel[] DRINKS = {
			COLA,
			SODA,
			COFFEE,
			TEA,
			WATER,
	};
}
