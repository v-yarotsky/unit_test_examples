class Warehouse
  def initialize
    @items = Hash.new(0)
  end

  def add(name, amount)
    @items[name] = @items[name] + amount
  end

  def remove(name, amount)
    @items[name] -= amount
  end

  def inventory(name)
    @items[name]
  end

  def has_inventory?(name, amount)
    @items[name] >= amount
  end
end

