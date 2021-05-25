package vending_machine;

// 割引商品のラベル
public class SaleItemLabel extends ItemLabel {

	// 割引率 〇〇％
	private int discountRate;

	// コンストラクタ
	public SaleItemLabel(String name, int price, String priceUnit, int discountRate) {
		super(name, price, priceUnit);
		this.discountRate = discountRate;
	}

	// コンストラクタ、単位なし
	public SaleItemLabel(String name, int price, int discountRate) {
		super(name, price);
		this.discountRate = discountRate;
	}

	// 割引率のゲッター
	public int getDiscountRate() {
		return this.discountRate;
	}

	// 割引率のセッター
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	// 割引価格のゲッター、1の位で切り上げ
	@Override
	public int getPrice() {
		double salePriceDouble = (double) super.getPrice() * (100 - this.discountRate) / 100;
		int salePrice = (int) Math.ceil(salePriceDouble / 10) * 10;
		return salePrice;
	}
}

// 割引商品ラベルのテンプレート
class SaleItemLabelTemplate {

	// ドリンクテンプレート
	public static final SaleItemLabel COLA = new SaleItemLabel("コーラ", 120, 30);
	public static final SaleItemLabel CARBONATED_WATER = new SaleItemLabel("炭酸水", 110, 30);
	public static final SaleItemLabel GREEN_TEA = new SaleItemLabel("緑茶", 100, 20);
	public static final SaleItemLabel[] DRINKS = {
			COLA,
			CARBONATED_WATER,
			GREEN_TEA,
	};

}
