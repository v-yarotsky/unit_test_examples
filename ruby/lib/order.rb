class Order
  def initialize(item_name, amount)
    @item_name, @amount = item_name.to_s, amount.to_i
    @filled = false
  end

  def filled?
    !!@filled
  end

  def fill(warehouse)
    if warehouse.has_inventory?(@item_name, @amount)
      warehouse.remove(@item_name, @amount)
      @filled = true
    end
  end
end
